package com.Baran.MineProtocol.item;

import com.Baran.MineProtocol.regi.ModEnchantments;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ItemToranomakiG3 extends Item {
    public ItemToranomakiG3(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide && player instanceof ServerPlayer) {

            List<ItemToranomakiG3.GachaEntry> entries = List.of(
                    new ItemToranomakiG3.GachaEntry(createEnchantedBook(ModEnchantments.CRESCENT_LIGHT.get(), 4), 10),
                    new ItemToranomakiG3.GachaEntry(createEnchantedBook(ModEnchantments.BIND_SLASH.get(), 4), 10),
                    new ItemToranomakiG3.GachaEntry(createEnchantedBook(ModEnchantments.DRAIN_SPIRAL.get(), 4), 10),
                    new ItemToranomakiG3.GachaEntry(createEnchantedBook(ModEnchantments.DESPERADO.get(), 4), 10),
                    new ItemToranomakiG3.GachaEntry(createEnchantedBook(ModEnchantments.HEALING_ARROW.get(), 4), 10),
                    new ItemToranomakiG3.GachaEntry(createEnchantedBook(ModEnchantments.REFRESH_AREA.get(), 1), 10),
                    new ItemToranomakiG3.GachaEntry(createEnchantedBook(ModEnchantments.SOLID_GAIN.get(), 4), 10),
                    new ItemToranomakiG3.GachaEntry(createEnchantedBook(ModEnchantments.HALCYON_NOTE.get(), 4), 5),
                    new ItemToranomakiG3.GachaEntry(createEnchantedBook(ModEnchantments.BRAVE_NOTE.get(), 4), 10),
                    new ItemToranomakiG3.GachaEntry(createEnchantedBook(ModEnchantments.SHIELD_DASH.get(), 4), 10),
                    new ItemToranomakiG3.GachaEntry(createEnchantedBook(ModEnchantments.HATE_COLLECT.get(), 1), 10),

                    new ItemToranomakiG3.GachaEntry(createEnchantedBook(ModEnchantments.CRESCENT_LIGHT.get(), 5), 1),
                    new ItemToranomakiG3.GachaEntry(createEnchantedBook(ModEnchantments.BIND_SLASH.get(), 5), 1),
                    new ItemToranomakiG3.GachaEntry(createEnchantedBook(ModEnchantments.DRAIN_SPIRAL.get(), 5), 1),
                    new ItemToranomakiG3.GachaEntry(createEnchantedBook(ModEnchantments.DESPERADO.get(), 5), 1),
                    new ItemToranomakiG3.GachaEntry(createEnchantedBook(ModEnchantments.HEALING_ARROW.get(), 5), 1),
                    new ItemToranomakiG3.GachaEntry(createEnchantedBook(ModEnchantments.SOLID_GAIN.get(), 5), 1),
                    new ItemToranomakiG3.GachaEntry(createEnchantedBook(ModEnchantments.BRAVE_NOTE.get(), 5), 1),
                    new ItemToranomakiG3.GachaEntry(createEnchantedBook(ModEnchantments.SHIELD_DASH.get(), 5), 1)
            );

            ItemStack result = getRandomItem(entries, level.random);
            player.addItem(result);

            level.playSound(
                    null,
                    player.getX(), player.getY(), player.getZ(),
                    SoundEvents.EXPERIENCE_ORB_PICKUP,
                    SoundSource.PLAYERS,
                    1.0f,
                    1.0f
            );

            if (!player.isCreative()) {
                stack.shrink(1);
            }
        }

        return InteractionResultHolder.success(stack);
    }

    private ItemStack getRandomItem(List<ItemToranomakiG3.GachaEntry> entries, net.minecraft.util.RandomSource random) {
        int totalWeight = entries.stream().mapToInt(e -> e.weight).sum();
        int roll = random.nextInt(totalWeight);
        int cumulative = 0;

        for (ItemToranomakiG3.GachaEntry entry : entries) {
            cumulative += entry.weight;
            if (roll < cumulative) {
                return entry.stack.copy();
            }
        }

        return new ItemStack(Items.STONE);
    }

    private static ItemStack createEnchantedBook(Enchantment enchantment, int level) {
        return EnchantedBookItem.createForEnchantment(new EnchantmentInstance(enchantment, level));
    }

    private static class GachaEntry {
        public final ItemStack stack;
        public final int weight;

        public GachaEntry(ItemStack stack, int weight) {
            this.stack = stack;
            this.weight = weight;
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level p_41422_, List<Component> list, TooltipFlag flag){
        list.add(Component.translatable(this.getDescriptionId() + ".text1").withStyle(ChatFormatting.AQUA));
    }
}
