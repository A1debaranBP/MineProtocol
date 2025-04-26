package com.Baran.MineProtocol.regi;

import com.Baran.MineProtocol.block.*;
import com.Baran.MineProtocol.main.MineProtocol;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MineProtocolBlocks {

    public static class Blocks{

        public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MineProtocol.MOD_ID);

        public static final RegistryObject<Block>ASTERIA_BLOCK = BLOCKS.register("asteria_block", BlockAsteriaBlock::new);
        public static final RegistryObject<Block>ASTERIA_ORE = BLOCKS.register("asteria_ore", BlockAsteriaOre::new);
        public static final RegistryObject<Block>KAIHUKU_BLOCK = BLOCKS.register("kaihuku_block", BlockKaihukuBlock::new);
        public static final RegistryObject<Block>KAIHUKU_ORE = BLOCKS.register("kaihuku_ore", BlockKaihukuOre::new);
        public static final RegistryObject<Block>ITYAN_BLOCK = BLOCKS.register("ityan_block", BlockItyann::new);
        public static final RegistryObject<Block>GOBU_BLOCK = BLOCKS.register("gobu_block", BlockGobu::new);
        public static final RegistryObject<Block>RED_GOBU_BLOCK = BLOCKS.register("red_gobu_block", BlockRedGobu::new);
        public static final RegistryObject<Block>YELLOW_GOBU_BLOCK = BLOCKS.register("yellow_gobu_block", BlockYellowGobu::new);
        public static final RegistryObject<Block>NAPPO_BLOCK = BLOCKS.register("nappo_block", BlockNappo::new);
        public static final RegistryObject<Block>RED_NAPPO_BLOCK = BLOCKS.register("red_nappo_block", BlockRedNappo::new);
        public static final RegistryObject<Block>GREEN_NAPPO_BLOCK = BLOCKS.register("green_nappo_block", BlockGreenNappo::new);
        public static final RegistryObject<Block>GOLD_NAPPO_BLOCK = BLOCKS.register("gold_nappo_block", BlockGoldNappo::new);
        public static final RegistryObject<Block>SILVER_NAPPO_BLOCK = BLOCKS.register("silver_nappo_block", BlockSilverNappo::new);


    }
    public static class BlockItems{

        public static final DeferredRegister<Item> BLOCK_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MineProtocol.MOD_ID);

        public static final RegistryObject<Item> ASTERIA_BLOCK_ITEM = BLOCK_ITEMS.register("asteria_block"
                , () -> new BlockItem(Blocks.ASTERIA_BLOCK.get(), new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> ASTERIA_ORE_ITEM = BLOCK_ITEMS.register("asteria_ore"
                , () -> new BlockItem(Blocks.ASTERIA_ORE.get(), new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> KAIHUKU_BLOCK_ITEM = BLOCK_ITEMS.register("kaihuku_block"
                , () -> new BlockItem(Blocks.KAIHUKU_BLOCK.get(), new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> KAIHUKU_ORE_ITEM = BLOCK_ITEMS.register("kaihuku_ore"
                , () -> new BlockItem(Blocks.KAIHUKU_ORE.get(), new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> ITYAN_BLOCK_ITEM = BLOCK_ITEMS.register("ityan_block"
                , () -> new BlockItem(Blocks.ITYAN_BLOCK.get(), new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> GOBU_BLOCK_ITEM = BLOCK_ITEMS.register("gobu_block"
                , () -> new BlockItem(Blocks.GOBU_BLOCK.get(), new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> RED_GOBU_BLOCK_ITEM = BLOCK_ITEMS.register("red_gobu_block"
                , () -> new BlockItem(Blocks.RED_GOBU_BLOCK.get(), new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> YELLOW_GOBU_BLOCK_ITEM = BLOCK_ITEMS.register("yellow_gobu_block"
                , () -> new BlockItem(Blocks.YELLOW_GOBU_BLOCK.get(), new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> NAPPO_BLOCK_ITEM = BLOCK_ITEMS.register("nappo_block"
                , () -> new BlockItem(Blocks.NAPPO_BLOCK.get(), new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> RED_NAPPO_BLOCK_ITEM = BLOCK_ITEMS.register("red_nappo_block"
                , () -> new BlockItem(Blocks.RED_NAPPO_BLOCK.get(), new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> GREEN_NAPPO_BLOCK_ITEM = BLOCK_ITEMS.register("green_nappo_block"
                , () -> new BlockItem(Blocks.GREEN_NAPPO_BLOCK.get(), new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> GOLD_NAPPO_BLOCK_ITEM = BLOCK_ITEMS.register("gold_nappo_block"
                , () -> new BlockItem(Blocks.GOLD_NAPPO_BLOCK.get(), new Item.Properties().fireResistant()));
        public static final RegistryObject<Item> SILVER_NAPPO_BLOCK_ITEM = BLOCK_ITEMS.register("silver_nappo_block"
                , () -> new BlockItem(Blocks.SILVER_NAPPO_BLOCK.get(), new Item.Properties().fireResistant()));

    }
}
