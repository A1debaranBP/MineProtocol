package com.Baran.MineProtocol.enchant;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.ShieldItem;

public class ShieldDashEnchantment extends Enchantment {

    public static final EnchantmentCategory SHIELD_ONLY = EnchantmentCategory.create("shield_only", (item) -> item instanceof ShieldItem);

    public ShieldDashEnchantment() {
        super(Rarity.RARE, SHIELD_ONLY, new EquipmentSlot[]{EquipmentSlot.OFFHAND, EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMaxLevel() {
        return 5;
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
        return stack.getItem() instanceof ShieldItem;
    }

    @Override
    protected boolean checkCompatibility(Enchantment other) {
        if (other == Enchantments.UNBREAKING || other == Enchantments.MENDING) {
            return true;
        }
        return super.checkCompatibility(other);
    }
}
