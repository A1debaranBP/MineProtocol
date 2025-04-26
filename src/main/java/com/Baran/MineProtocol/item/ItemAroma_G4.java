package com.Baran.MineProtocol.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ItemAroma_G4 extends Item {

    private static final double RADIUS = 3.0;

    public ItemAroma_G4() {
        super(new Properties().stacksTo(64));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        if (!level.isClientSide && level instanceof ServerLevel serverLevel) {
            healEntities(serverLevel, player);
            spawnParticles(serverLevel, player);
            playSound(serverLevel, player);
            itemstack.shrink(1);
        }

        return InteractionResultHolder.success(itemstack);
    }

    private void healEntities(ServerLevel level, Player player) {
        List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, new AABB(
                player.getX() - RADIUS, player.getY() - RADIUS, player.getZ() - RADIUS,
                player.getX() + RADIUS, player.getY() + RADIUS, player.getZ() + RADIUS));

        for (LivingEntity entity : entities) {
            entity.heal(16.0F);
        }
    }

    private void spawnParticles(ServerLevel level, Player player) {
        for (double x = -RADIUS; x <= RADIUS; x += 1.0) {
            for (double z = -RADIUS; z <= RADIUS; z += 1.0) {
                if (x * x + z * z <= RADIUS * RADIUS) { // 円形の範囲を作る
                    level.sendParticles(ParticleTypes.HEART,
                            player.getX() + x, player.getY() + 1.0, player.getZ() + z,
                            3, 0, 0, 0, 0);
                }
            }
        }
    }

    private void playSound(ServerLevel level, Player player) {
        level.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.BEACON_ACTIVATE, SoundSource.PLAYERS, 1.0F, 1.0F);
        level.gameEvent(player, GameEvent.ENTITY_INTERACT, player.position());
    }


    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level p_41422_, List<Component> list, TooltipFlag flag){
        list.add(Component.translatable(this.getDescriptionId() + ".text1").withStyle(ChatFormatting.AQUA));
        list.add(Component.translatable(this.getDescriptionId() + ".text2").withStyle(ChatFormatting.GREEN));
    }
}
