package com.Baran.MineProtocol.main;

import com.Baran.MineProtocol.effect.ModEffects;
import com.Baran.MineProtocol.regi.MineProtocolBlocks;
import com.Baran.MineProtocol.regi.MineProtocolItems;
import com.Baran.MineProtocol.regi.ModEnchantments;
import com.Baran.MineProtocol.regi.tab.MineProTabs;
import com.Baran.MineProtocol.villager.ModVillagers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("mineprotocol")
public class MineProtocol {

    public static final String MOD_ID = "mineprotocol";

    public MineProtocol(){

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        MineProtocolItems.ITEMS.register(bus);
        MineProtocolBlocks.Blocks.BLOCKS.register(bus);
        MineProtocolBlocks.BlockItems.BLOCK_ITEMS.register(bus);
        MineProTabs.MOD_TABS.register(bus);
        ModVillagers.register(bus);
        ModEnchantments.ENCHANTMENTS.register(bus);
        ModEffects.register();
    }
}
