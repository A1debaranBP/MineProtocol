package com.Baran.MineProtocol.item.fish;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

import java.util.List;
@Mod.EventBusSubscriber


public class FishTunaEdge extends SwordItem {

    private static final int BOOST = 1200;
    private static final int LEVEL = 3;
    private static final int COOLDOWN = 3600;

    public FishTunaEdge() {
        super(FishToolTiers.FISH,7,-1.0F
                ,new Properties()
                        .rarity(Rarity.EPIC)
        );

    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!attacker.level().isClientSide) {
            attacker.level().playSound(null, attacker.getX(), attacker.getY(), attacker.getZ(),
                    SoundEvents.FISH_SWIM, SoundSource.PLAYERS, 1.0F, 1.0F);
        }
        return super.hurtEnemy(stack, target, attacker);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide) {
            if (player.getCooldowns().isOnCooldown(this)) {
                return InteractionResultHolder.fail(stack);
            }

            player.addEffect(new MobEffectInstance(MobEffects.JUMP, BOOST, LEVEL));
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, BOOST, LEVEL));
            player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, BOOST, LEVEL));
            player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, BOOST, LEVEL));

            player.getCooldowns().addCooldown(this, COOLDOWN);

            level.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.PLAYERS, 1.0F, 1.0F);

            return InteractionResultHolder.success(stack);
        }

        return InteractionResultHolder.pass(stack);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level p_41422_, List<Component> list, TooltipFlag flag){
        list.add(Component.translatable(this.getDescriptionId() + ".text1").withStyle(ChatFormatting.AQUA));
        list.add(Component.translatable(this.getDescriptionId() + ".text2").withStyle(ChatFormatting.GREEN));
    }
}
