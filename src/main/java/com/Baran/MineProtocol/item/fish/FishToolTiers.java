package com.Baran.MineProtocol.item.fish;

import com.Baran.MineProtocol.regi.MineProtocolItems;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public enum FishToolTiers implements Tier {
    FISH(1000,10.0F, 3.0F,4,25,Ingredient.of(MineProtocolItems.GC.get()));

    private final int uses;
    private final float speed;
    private final float attackDamage;
    private final int level;
    private final int enchantmentValue;
    private final Ingredient repairIngredient;

    FishToolTiers(int uses, float speed, float attackDamage, int level, int enchantmentValue, Ingredient repairIngredient) {
        this.uses = uses;
        this.speed = speed;
        this.attackDamage = attackDamage;
        this.level = level;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getUses() {
        return this.uses;
    }

    @Override
    public float getSpeed() {
        return this.speed;
    }

    @Override
    public float getAttackDamageBonus() {
        return this.attackDamage;
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient;
    }
}
