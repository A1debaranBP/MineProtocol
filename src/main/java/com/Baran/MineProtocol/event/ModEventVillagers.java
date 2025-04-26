package com.Baran.MineProtocol.event;
import com.Baran.MineProtocol.main.MineProtocol;
import com.Baran.MineProtocol.regi.MineProtocolBlocks;
import com.Baran.MineProtocol.regi.MineProtocolItems;
import com.Baran.MineProtocol.regi.ModEnchantments;
import com.Baran.MineProtocol.villager.ModVillagers;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = MineProtocol.MOD_ID)
public class ModEventVillagers {

    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {

        if(event.getType() == ModVillagers.KINENHIN.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(MineProtocolItems.GC.get(), 15),
                    new ItemStack(MineProtocolItems.TOKOYO.get(), 15),
                    Integer.MAX_VALUE, 5, 0.02f));

            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(MineProtocolItems.GC.get(), 10),
                    new ItemStack(MineProtocolItems.SAZANAMI.get(), 10),
                    Integer.MAX_VALUE, 5, 0.02f));

            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(MineProtocolItems.GC.get(), 10),
                    new ItemStack(MineProtocolItems.BASECLAY.get(), 10),
                    Integer.MAX_VALUE, 5, 0.02f));

            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(MineProtocolItems.GC.get(), 10),
                    new ItemStack(MineProtocolItems.KANJYOUSEKI.get(), 10),
                    Integer.MAX_VALUE, 5, 0.02f));

            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(MineProtocolItems.GC.get(), 64),
                    new ItemStack(MineProtocolItems.ROSE.get(), 1),
                    Integer.MAX_VALUE, 5, 0.02f));



            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(MineProtocolItems.GC.get(), 20),
                    new ItemStack(MineProtocolItems.DORODORO_G1.get(), 3),
                    Integer.MAX_VALUE, 6, 0.02f));

            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(MineProtocolItems.GC.get(), 10),
                    new ItemStack(MineProtocolItems.KOUGEKI_SEIYU.get(), 3),
                    Integer.MAX_VALUE, 6, 0.02f));

            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(MineProtocolItems.GC.get(), 10),
                    new ItemStack(MineProtocolItems.MAMORI_SEIYU.get(), 3),
                    Integer.MAX_VALUE, 6, 0.02f));

            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(MineProtocolItems.GC.get(), 20),
                    new ItemStack(MineProtocolBlocks.BlockItems.GOBU_BLOCK_ITEM.get(), 10),
                    Integer.MAX_VALUE, 6, 0.02f));

            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(MineProtocolItems.GC.get(), 20),
                    new ItemStack(MineProtocolBlocks.BlockItems.GREEN_NAPPO_BLOCK_ITEM.get(), 10),
                    Integer.MAX_VALUE, 6, 0.02f));

            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(MineProtocolItems.GC.get(), 20),
                    new ItemStack(MineProtocolBlocks.BlockItems.NAPPO_BLOCK_ITEM.get(), 10),
                    Integer.MAX_VALUE, 6, 0.02f));

            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(MineProtocolItems.ROSE.get(), 6),
                    EnchantedBookItem.createForEnchantment(
                            new EnchantmentInstance(ModEnchantments.DRAIN_SPIRAL.get(), 2)
                    ),
                    Integer.MAX_VALUE, 12, 0.02f));



            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(MineProtocolItems.ROSE.get(), 1),
                    new ItemStack(MineProtocolItems.TORANOMAKI_G1.get(), 1),
                    Integer.MAX_VALUE, 7, 0.02f));

            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(MineProtocolItems.GC.get(), 10),
                    new ItemStack(MineProtocolItems.ASTERIA_CHOCOLATE.get(), 2),
                    Integer.MAX_VALUE, 7, 0.02f));

            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(MineProtocolItems.GC.get(), 10),
                    new ItemStack(MineProtocolItems.ASTERIA_CHOCOLATE_B.get(), 2),
                    Integer.MAX_VALUE, 7, 0.02f));

            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(MineProtocolItems.GC.get(), 10),
                    new ItemStack(MineProtocolItems.ASTERIA_CHOCOLATE_ULT.get(), 2),
                    Integer.MAX_VALUE, 7, 0.02f));

            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(MineProtocolItems.ROSE.get(), 64),
                    new ItemStack(MineProtocolItems.BPP.get(), 1),
                    Integer.MAX_VALUE, 7, 0.02f));



            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(MineProtocolItems.ROSE.get(), 1),
                    new ItemStack(MineProtocolItems.KAIHUKU_G4.get(), 1),
                    Integer.MAX_VALUE, 8, 0.02f));

            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(MineProtocolItems.ROSE.get(), 1),
                    new ItemStack(MineProtocolItems.AROMA_G4.get(), 1),
                    Integer.MAX_VALUE, 8, 0.02f));

            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(MineProtocolItems.GC.get(), 30),
                    new ItemStack(MineProtocolItems.DORODORO_G2.get(), 1),
                    Integer.MAX_VALUE, 8, 0.02f));

            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(MineProtocolItems.ROSE.get(), 1),
                    new ItemStack(MineProtocolItems.GC.get(), 20),
                    new ItemStack(MineProtocolBlocks.BlockItems.YELLOW_GOBU_BLOCK_ITEM.get(), 10),
                    Integer.MAX_VALUE, 8, 0.02f));

            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(MineProtocolItems.ROSE.get(), 1),
                    new ItemStack(MineProtocolItems.GC.get(), 20),
                    new ItemStack(MineProtocolBlocks.BlockItems.RED_GOBU_BLOCK_ITEM.get(), 10),
                    Integer.MAX_VALUE, 8, 0.02f));

            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(MineProtocolItems.ROSE.get(), 1),
                    new ItemStack(MineProtocolItems.GC.get(), 20),
                    new ItemStack(MineProtocolBlocks.BlockItems.RED_NAPPO_BLOCK_ITEM.get(), 10),
                    Integer.MAX_VALUE, 8, 0.02f));



            trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(MineProtocolItems.BPP.get(), 1),
                    new ItemStack(MineProtocolItems.TORANOMAKI_G3.get(), 1),
                    Integer.MAX_VALUE, 9, 0.02f));

            trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(MineProtocolItems.BPP.get(), 1),
                    new ItemStack(MineProtocolItems.FISH_STICK.get(), 1),
                    Integer.MAX_VALUE, 9, 0.02f));

            trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(MineProtocolItems.BPP.get(), 1),
                    new ItemStack(MineProtocolBlocks.BlockItems.SILVER_NAPPO_BLOCK_ITEM.get(), 5),
                    Integer.MAX_VALUE, 9, 0.02f));
        }

    }
}
