package com.Baran.MineProtocol.regi;

import com.Baran.MineProtocol.enchant.*;
import com.Baran.MineProtocol.main.MineProtocol;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEnchantments {

    public static final DeferredRegister<Enchantment> ENCHANTMENTS =
            DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, MineProtocol.MOD_ID);

    public static final RegistryObject<Enchantment> DRAIN_SPIRAL =
            ENCHANTMENTS.register("drain_spiral",DrainSpiralEnchantment::new);

    public static final RegistryObject<Enchantment> DESPERADO =
            ENCHANTMENTS.register("desperado",DesperadoEnchantment::new);

    public static final RegistryObject<Enchantment> BRUTAL_BLOW =
            ENCHANTMENTS.register("brutal_blow", BrutalBlowEnchantment::new);

    public static final RegistryObject<Enchantment> BIND_SLASH =
            ENCHANTMENTS.register("bind_slash", BindSlashEnchantment::new);

    public static final RegistryObject<Enchantment> CRESCENT_LIGHT =
            ENCHANTMENTS.register("crescent_light", CrescentLightEnchantment::new);

    public static final RegistryObject<Enchantment> HEALING_ARROW =
            ENCHANTMENTS.register("healing_arrow", HealingArrowEnchantment::new);

    public static final RegistryObject<Enchantment> REFRESH_AREA =
            ENCHANTMENTS.register("refresh_area", RefreshAreaEnchantment::new);

    public static final RegistryObject<Enchantment> TWIN_FLASH =
            ENCHANTMENTS.register("twin_flash", TwinFlashEnchantment::new);

    public static final RegistryObject<Enchantment> BRAVE_NOTE =
            ENCHANTMENTS.register("brave_note", BraveNoteEnchantment::new);

    public static final RegistryObject<Enchantment> HALCYON_NOTE =
            ENCHANTMENTS.register("halcyon_note", HalcyonNoteEnchantment::new);

    public static final RegistryObject<Enchantment> BREAKING_BEAT =
            ENCHANTMENTS.register("breaking_beat", BreakingBeatEnchantment::new);

    public static final RegistryObject<Enchantment> SOLID_GAIN =
            ENCHANTMENTS.register("solid_gain", SolidGainEnchantment::new);

    public static final RegistryObject<Enchantment> SHIELD_DASH =
            ENCHANTMENTS.register("shield_dash", SolidGainEnchantment::new);

    public static final RegistryObject<Enchantment> HATE_COLLECT =
            ENCHANTMENTS.register("hate_collect", HateCollectEnchantment::new);


}
