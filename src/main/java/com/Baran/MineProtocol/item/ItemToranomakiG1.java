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

public class ItemToranomakiG1 extends Item {

    public ItemToranomakiG1(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide && player instanceof ServerPlayer) {

            List<GachaEntry> entries = List.of(
                    new GachaEntry(createEnchantedBook(ModEnchantments.CRESCENT_LIGHT.get(), 1), 20),
                    new GachaEntry(createEnchantedBook(ModEnchantments.BIND_SLASH.get(), 1), 30),
                    new GachaEntry(createEnchantedBook(ModEnchantments.DESPERADO.get(), 1), 30),
                    new GachaEntry(createEnchantedBook(ModEnchantments.DRAIN_SPIRAL.get(), 1), 30),
                    new GachaEntry(createEnchantedBook(ModEnchantments.BRUTAL_BLOW.get(), 1), 30),
                    new GachaEntry(createEnchantedBook(ModEnchantments.HEALING_ARROW.get(), 1), 30),
                    new GachaEntry(createEnchantedBook(ModEnchantments.TWIN_FLASH.get(), 1), 30),
                    new GachaEntry(createEnchantedBook(ModEnchantments.BREAKING_BEAT.get(), 1), 30),
                    new GachaEntry(createEnchantedBook(ModEnchantments.SOLID_GAIN.get(), 1), 30),
                    new GachaEntry(createEnchantedBook(ModEnchantments.HALCYON_NOTE.get(), 1), 25),
                    new GachaEntry(createEnchantedBook(ModEnchantments.BRAVE_NOTE.get(), 1), 30),
                    new GachaEntry(createEnchantedBook(ModEnchantments.SHIELD_DASH.get(), 1), 30),

                    new GachaEntry(createEnchantedBook(ModEnchantments.BIND_SLASH.get(), 2), 10),
                    new GachaEntry(createEnchantedBook(ModEnchantments.DESPERADO.get(), 2), 10),
                    new GachaEntry(createEnchantedBook(ModEnchantments.DRAIN_SPIRAL.get(), 2), 10),
                    new GachaEntry(createEnchantedBook(ModEnchantments.HEALING_ARROW.get(), 2), 10),
                    new GachaEntry(createEnchantedBook(ModEnchantments.TWIN_FLASH.get(), 2), 10),
                    new GachaEntry(createEnchantedBook(ModEnchantments.BREAKING_BEAT.get(), 2), 10),
                    new GachaEntry(createEnchantedBook(ModEnchantments.SOLID_GAIN.get(), 2), 10),
                    new GachaEntry(createEnchantedBook(ModEnchantments.HALCYON_NOTE.get(), 2), 5),
                    new GachaEntry(createEnchantedBook(ModEnchantments.BRAVE_NOTE.get(), 2), 10),
                    new GachaEntry(createEnchantedBook(ModEnchantments.SHIELD_DASH.get(), 2), 10),

                    new GachaEntry(createEnchantedBook(ModEnchantments.BIND_SLASH.get(), 3), 3),
                    new GachaEntry(createEnchantedBook(ModEnchantments.DESPERADO.get(), 3), 3),
                    new GachaEntry(createEnchantedBook(ModEnchantments.DRAIN_SPIRAL.get(), 3), 3),
                    new GachaEntry(createEnchantedBook(ModEnchantments.HEALING_ARROW.get(), 3), 3),
                    new GachaEntry(createEnchantedBook(ModEnchantments.TWIN_FLASH.get(), 3), 3),
                    new GachaEntry(createEnchantedBook(ModEnchantments.BREAKING_BEAT.get(), 3), 3),
                    new GachaEntry(createEnchantedBook(ModEnchantments.SOLID_GAIN.get(), 3), 3),
                    new GachaEntry(createEnchantedBook(ModEnchantments.HALCYON_NOTE.get(), 3), 2),
                    new GachaEntry(createEnchantedBook(ModEnchantments.BRAVE_NOTE.get(), 3), 3),
                    new GachaEntry(createEnchantedBook(ModEnchantments.SHIELD_DASH.get(), 3), 3)

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

    private ItemStack getRandomItem(List<GachaEntry> entries, net.minecraft.util.RandomSource random) {
        int totalWeight = entries.stream().mapToInt(e -> e.weight).sum();
        int roll = random.nextInt(totalWeight);
        int cumulative = 0;

        for (GachaEntry entry : entries) {
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
