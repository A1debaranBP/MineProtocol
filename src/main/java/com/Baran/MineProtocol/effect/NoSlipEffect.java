package com.Baran.MineProtocol.effect;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.state.BlockState;

public class NoSlipEffect extends MobEffect {

    public NoSlipEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xADD8E6);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (!entity.onGround()) return;

        BlockPos below = entity.blockPosition().below();
        BlockState blockBelow = entity.level().getBlockState(below);

        if (blockBelow.is(BlockTags.ICE)) {
            entity.setDeltaMovement(entity.getDeltaMovement().multiply(0.9, 1.0, 0.9));
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
