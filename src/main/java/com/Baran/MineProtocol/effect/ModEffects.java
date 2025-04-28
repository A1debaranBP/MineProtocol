package com.Baran.MineProtocol.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraft.world.effect.MobEffect;

import static com.Baran.MineProtocol.main.MineProtocol.MOD_ID;

public class ModEffects {

    public static final DeferredRegister<MobEffect> EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, MOD_ID);

    // ドロドロ
    public static final RegistryObject<MobEffect> DOUBLE_DROPS =
            EFFECTS.register("double_drops", () -> new DoubleDropEffect());

    //耐雷
    public static final RegistryObject<MobEffect> THUNDER_IMMUNITY =
            EFFECTS.register("thunder_immunity", ThunderImmunityEffect::new);

    //耐氷
    public static final RegistryObject<MobEffect> NO_SLIP =
            EFFECTS.register("no_slip", NoSlipEffect::new);

    public static void register() {
        EFFECTS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
