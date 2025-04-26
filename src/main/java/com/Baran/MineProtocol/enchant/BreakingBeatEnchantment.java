package com.Baran.MineProtocol.enchant;

import com.Baran.MineProtocol.regi.ModEnchantments;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class BreakingBeatEnchantment extends Enchantment {

    public BreakingBeatEnchantment() {
        super(Rarity.RARE, EnchantmentCategory.BREAKABLE,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean isTreasureOnly() {
        return true;
    }

    @Override
    public boolean isAllowedOnBooks() {
        return true;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return false;
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        return stack.is(Items.GOAT_HORN);
    }

    @Override
    public boolean checkCompatibility(Enchantment other) {
        return super.checkCompatibility(other)
                && other != ModEnchantments.BRAVE_NOTE.get()
                && other != ModEnchantments.HALCYON_NOTE.get()
                && other != ModEnchantments.SOLID_GAIN.get();
    }
}
