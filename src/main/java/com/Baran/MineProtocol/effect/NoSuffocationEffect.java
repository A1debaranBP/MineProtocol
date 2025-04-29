package com.Baran.MineProtocol.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class NoSuffocationEffect extends MobEffect {

    public NoSuffocationEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x7FFFD4); // 色は適当やけど薄緑っぽい
    }

}
