package com.Baran.MineProtocol.item;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

import java.util.List;


@Mod.EventBusSubscriber
public class ItemYadotyou extends Item {
    public ItemYadotyou() {
        super(new Properties().stacksTo(1));
    }

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        ServerPlayer player = (ServerPlayer) event.getEntity();

        if (player instanceof FakePlayer) return;

        CompoundTag persistentData = player.getPersistentData();
        CompoundTag forgeData = persistentData.getCompound(Player.PERSISTED_NBT_TAG);

        if (!forgeData.getBoolean("hasReceivedBook")) {
            giveBookToPlayer(player);
            forgeData.putBoolean("hasReceivedBook", true);
            persistentData.put(Player.PERSISTED_NBT_TAG, forgeData);
        }
    }

    private static void giveBookToPlayer(Player player) {
        ItemStack book = new ItemStack(Items.WRITTEN_BOOK);
        CompoundTag tag = book.getOrCreateTag();
        ListTag pages = new ListTag();

        pages.add(StringTag.valueOf("\n\n\n\n\n\n" + "{" + player.getName().getString() + "}"));
        pages.add(StringTag.valueOf("{\"text\":\"フハハハハ、よっしゃあ！\n宿帳とは仮の姿、これはワシとの下僕契約書じゃ！\"}"));
        pages.add(StringTag.valueOf("{\"text\":\"これで、お主は今日からワシの下僕じゃ！\nキリキリ働いて、ワシの借金を減らしてもらうぞ!\"}"));
        pages.add(StringTag.valueOf("{\"text\":\"※この宿帳は今のところ入手手段がありません。\"}"));

        tag.put("pages", pages);
        tag.putString("author", "フェステ");
        tag.putString("title", "宿帳?");

        book.setTag(tag);

        if (!player.getInventory().add(book)) {
            player.drop(book, false);
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level p_41422_, List<Component> list, TooltipFlag flag){
        list.add(Component.translatable(this.getDescriptionId() + ".text1").withStyle(ChatFormatting.AQUA));
    }
}
