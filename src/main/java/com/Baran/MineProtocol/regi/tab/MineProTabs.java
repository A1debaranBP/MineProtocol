package com.Baran.MineProtocol.regi.tab;

import com.Baran.MineProtocol.main.MineProtocol;
import com.Baran.MineProtocol.regi.MineProtocolItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class MineProTabs {

    public static final DeferredRegister<CreativeModeTab> MOD_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MineProtocol.MOD_ID);

    public static final RegistryObject<CreativeModeTab> MinePro_Main = MOD_TABS.register("minepro_main",
            ()-> {return CreativeModeTab.builder()
                    .icon(()->new ItemStack(MineProtocolItems.TOKOYO.get()))
                    .title(Component.translatable("itemGroup.minepro_main"))
                    .displayItems((param,output)->{
                        for(Item item:MineProMain.items){
                            output.accept(item);
                        }
                    })
                    .build();
    });

}
