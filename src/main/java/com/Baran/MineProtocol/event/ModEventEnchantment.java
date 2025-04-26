package com.Baran.MineProtocol.event;

import com.Baran.MineProtocol.regi.ModEnchantments;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingTickEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "mineprotocol")
public class ModEventEnchantment {

    @SubscribeEvent//ドレインスパイラル
    public static void onLivingHurt(LivingHurtEvent event) {
        Entity source = event.getSource().getEntity();
        Entity target = event.getEntity();

        if (!(source instanceof LivingEntity) || !(target instanceof LivingEntity)) return;

        LivingEntity attacker = (LivingEntity) source;
        LivingEntity victim = (LivingEntity) target;

        for (ItemStack stack : attacker.getHandSlots()) {
            int level = stack.getEnchantmentLevel(ModEnchantments.DRAIN_SPIRAL.get());
            if (level > 0) {
                attacker.heal(level * 2.0f);
                break;
            }
        }
    }

    @SubscribeEvent//デスペラード
    public static void onDesperadoEffect(LivingHurtEvent event) {
        Entity source = event.getSource().getEntity();
        Entity target = event.getEntity();

        if (!(source instanceof LivingEntity) || !(target instanceof LivingEntity)) return;

        LivingEntity attacker = (LivingEntity) source;

        for (ItemStack stack : attacker.getHandSlots()) {
            int level = stack.getEnchantmentLevel(ModEnchantments.DESPERADO.get());
            if (level > 0) {

                float boostMultiplier = 1.0f + 0.2f * level;
                event.setAmount(event.getAmount() * boostMultiplier);


                float selfDamage = level * 2.0f;
                if (attacker.getHealth() > selfDamage) {
                    attacker.hurt(attacker.level().damageSources().magic(), selfDamage);
                } else {
                    attacker.setHealth(1.0f);
                }

                break;
            }
        }
    }

    @SubscribeEvent//バインドスラッシュ
    public static void onBindSlashEffect(LivingHurtEvent event) {
        Entity source = event.getSource().getEntity();
        Entity rawTarget = event.getEntity();

        if (!(source instanceof LivingEntity)) return;
        if (!(rawTarget instanceof LivingEntity)) return;

        LivingEntity attacker = (LivingEntity) source;
        LivingEntity target = (LivingEntity) rawTarget;

        for (ItemStack stack : attacker.getHandSlots()) {
            int level = stack.getEnchantmentLevel(ModEnchantments.BIND_SLASH.get());
            if (level > 0) {
                int durationTicks = 20 * (3 + (level - 1) * 2);
                target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, durationTicks, 4)); // Lv5 Slowness（0始まりなので4）
                break;
            }
        }
    }

    @SubscribeEvent//クリセントライト
    public static void onAttackEntity(AttackEntityEvent event) {
        if (!(event.getEntity() instanceof LivingEntity)) return;

        LivingEntity attacker = (LivingEntity) event.getEntity();

        Entity target = event.getTarget();
        if (!(target instanceof LivingEntity)) return;

        LivingEntity victim = (LivingEntity) target;

        int level = 0;
        for (ItemStack stack : attacker.getHandSlots()) {
            int enchLevel = stack.getEnchantmentLevel(ModEnchantments.CRESCENT_LIGHT.get());
            if (enchLevel > 0) {
                level = enchLevel;
                break;
            }
        }


        double reach = level > 0 ? 3.0 + level * 1.0 : 3.0;
        double distanceSq = attacker.distanceToSqr(victim);

        if (distanceSq > reach * reach) {
            event.setCanceled(true);
        }
    }
    @SubscribeEvent//ヒーリングアロー
    public static void onArrowImpact(ProjectileImpactEvent event) {
        if (!(event.getEntity() instanceof AbstractArrow arrow)) return;
        if (!(arrow.getOwner() instanceof LivingEntity shooter)) return;
        if (!(event.getRayTraceResult() instanceof EntityHitResult hitResult)) return;
        if (!(hitResult.getEntity() instanceof LivingEntity target)) return;


        ItemStack bow = shooter.getMainHandItem();
        int level = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.HEALING_ARROW.get(), bow);
        if (level <= 0) return;

        float healAmount = level * 2.0f;
        target.heal(healAmount);

        target.level().playSound(null, target.getX(), target.getY(), target.getZ(),
                SoundEvents.GENERIC_DRINK, SoundSource.PLAYERS, 1.0f, 1.0f);

        arrow.discard();

        event.setCanceled(true);
    }

    @SubscribeEvent//ツインフラッシュ
    public static void onTwinFlash(ProjectileImpactEvent event) {
        if (!(event.getEntity() instanceof AbstractArrow arrow)) return;
        if (!(arrow.getOwner() instanceof LivingEntity shooter)) return;
        if (!(event.getRayTraceResult() instanceof EntityHitResult hitResult)) return;
        if (!(hitResult.getEntity() instanceof LivingEntity target)) return;

        ItemStack bow = shooter.getMainHandItem();
        int level = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.TWIN_FLASH.get(), bow);
        if (level <= 0) return;


        int durationTicks = 20 * (30 + (level - 1) * 5);
        target.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, durationTicks, 0)); // 0 = Lv1のStrength


        target.level().playSound(null, target.getX(), target.getY(), target.getZ(),
                SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.PLAYERS, 1.0f, 1.0f);

        arrow.discard();


        event.setCanceled(true);
    }

    @SubscribeEvent//ブレイブノート
    public static void onUseHorn(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();
        ItemStack stack = event.getItemStack();

        if (!stack.is(Items.GOAT_HORN)) return;

        int level = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.BRAVE_NOTE.get(), stack);
        if (level <= 0) return;

        int duration = 20 * (20 + (level - 1) * 5);
        AABB area = new AABB(player.blockPosition()).inflate(16);

        for (LivingEntity entity : player.level().getEntitiesOfClass(LivingEntity.class, area)) {

            if (!(entity instanceof net.minecraft.world.entity.monster.Monster)) {
                entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, duration, 0));
            }
        }

        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, duration, 1));


        player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.PLAYER_LEVELUP, SoundSource.PLAYERS, 1.5f, 1.2f);
    }

    @SubscribeEvent//ハルシオンノート
    public static void HalcyonNote(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();
        ItemStack stack = event.getItemStack();

        if (!stack.is(Items.GOAT_HORN)) return;

        int level = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.HALCYON_NOTE.get(), stack);
        if (level <= 0) return;

        int duration = 20 * 3;
        int amplifier = level - 1;

        AABB area = new AABB(player.blockPosition()).inflate(16);

        for (LivingEntity entity : player.level().getEntitiesOfClass(LivingEntity.class, area)) {
            if (!(entity instanceof net.minecraft.world.entity.monster.Monster)) {
                entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, duration, amplifier));
            }
        }

        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, duration, amplifier));

        player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.PLAYER_LEVELUP, SoundSource.PLAYERS, 1.5f, 1.1f);
    }

    @SubscribeEvent//ブレイキングビート
    public static void BreakingBeat(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();
        ItemStack stack = event.getItemStack();

        if (!stack.is(Items.GOAT_HORN)) return;

        int level = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.BREAKING_BEAT.get(), stack);
        if (level <= 0) return;

        float damage = level * 2.0f;
        AABB area = new AABB(player.blockPosition()).inflate(16); // 半径16ブロック

        for (LivingEntity entity : player.level().getEntitiesOfClass(LivingEntity.class, area)) {
            if (entity instanceof net.minecraft.world.entity.Mob mob && mob.getTarget() != null) {

                entity.hurt(player.level().damageSources().magic(), damage);
            }
        }

        player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.PLAYER_LEVELUP, SoundSource.PLAYERS, 1.2f, 0.8f);
    }

    @SubscribeEvent//ソリットゲイン
    public static void SolidGain(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();
        ItemStack stack = event.getItemStack();

        if (!stack.is(Items.GOAT_HORN)) return;

        int level = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.SOLID_GAIN.get(), stack);
        if (level <= 0) return;

        int duration = 20 * (10 + (level - 1) * 10);
        AABB area = new AABB(player.blockPosition()).inflate(16);

        for (LivingEntity entity : player.level().getEntitiesOfClass(LivingEntity.class, area)) {

            if (!(entity instanceof Monster)) {
                entity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, duration, 0));
            }
        }

        player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, duration, 0));

        player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.PLAYER_LEVELUP, SoundSource.PLAYERS, 1.2f, 0.9f);
    }

    @SubscribeEvent//ブルータルブロウ
    public static void BrutalBlow(AttackEntityEvent event) {
        Entity attackerEntity = event.getEntity();
        Entity target = event.getTarget();

        if (!(attackerEntity instanceof LivingEntity attacker)) return;
        if (!(target instanceof LivingEntity)) return;

        ItemStack weapon = attacker.getMainHandItem();
        int level = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.BRUTAL_BLOW.get(), weapon);
        if (level <= 0) return;

        // 範囲の倍率（例：1.5倍, 2倍）
        float rangeMultiplier = 1.0f + 0.5f * level;
        float baseRange = 3.0f; // 通常攻撃範囲
        float totalRange = baseRange * rangeMultiplier;

        Level levelObj = attacker.level();
        AABB area = attacker.getBoundingBox().inflate(totalRange, 1.0, totalRange);

        for (LivingEntity entity : levelObj.getEntitiesOfClass(LivingEntity.class, area)) {
            if (entity == attacker || entity == target) continue;
            if (!attacker.hasLineOfSight(entity)) continue;

            Vec3 attackerVec = attacker.getLookAngle().normalize();
            Vec3 vecToEntity = entity.position().subtract(attacker.position()).normalize();
            double dot = attackerVec.dot(vecToEntity);
            if (dot < 0.5) continue;
            entity.hurt(levelObj.damageSources().playerAttack((Player) attacker), 2.0f + level);

            levelObj.playSound(null, entity.getX(), entity.getY(), entity.getZ(),
                    SoundEvents.PLAYER_ATTACK_SWEEP, SoundSource.PLAYERS, 1.0f, 1.0f);
        }
    }

    @SubscribeEvent//リフレッシュエリア
    public static void onArrowHit(ProjectileImpactEvent event) {
        if (!(event.getEntity() instanceof AbstractArrow arrow)) return;
        if (!(arrow.getOwner() instanceof LivingEntity shooter)) return;
        if (!(event.getRayTraceResult() instanceof EntityHitResult hitResult)) return;
        if (!(hitResult.getEntity() instanceof LivingEntity target)) return;

        ItemStack bow = shooter.getMainHandItem();
        int level = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.REFRESH_AREA.get(), bow);
        if (level <= 0) return;

        target.removeAllEffects();

        target.level().playSound(null, target.getX(), target.getY(), target.getZ(),
                SoundEvents.BOTTLE_FILL, SoundSource.PLAYERS, 1.0f, 1.0f);

        arrow.discard();


        event.setCanceled(true);
    }

    @SubscribeEvent//シールドダッシュ
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;

        if (player.isBlocking()) {
            ItemStack shield = player.getUseItem();
            if (shield.getItem() instanceof ShieldItem) {
                int level = shield.getEnchantmentLevel(ModEnchantments.SHIELD_DASH.get());
                if (level > 0) {

                    float bonusSpeed = 0.1f;
                    var movement = player.getDeltaMovement();
                    player.setDeltaMovement(
                            movement.x * (1.0 + bonusSpeed),
                            movement.y,
                            movement.z * (1.0 + bonusSpeed)
                    );

                    var lookVec = player.getLookAngle();
                    double reach = 1.5D;

                    var start = player.getEyePosition();
                    var end = start.add(lookVec.scale(reach));

                    AABB attackBox = new AABB(start, end).inflate(0.3D);

                    for (Entity entity : player.level().getEntities(player, attackBox)) {
                        if (entity instanceof LivingEntity target && target != player) {
                            double dot = lookVec.dot(target.position().subtract(player.position()).normalize());
                            if (dot > 0.85) {

                                target.hurt(player.damageSources().playerAttack(player), 2.0f + level);
                            }
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent//ヘイトコレクト
    public static void HateCollect(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Player player = event.player;
        ItemStack offhand = player.getOffhandItem();

        if (offhand.isEmpty()) return;
        if (EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.HATE_COLLECT.get(), offhand) <= 0) return;
        if (!player.isUsingItem()) return;


        for (LivingEntity entity : player.level().getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(8))) {
            if (entity instanceof Mob mob) {
                if (mob.getTarget() != player) {
                    mob.setTarget(player);
                }
            }
        }
    }


}
