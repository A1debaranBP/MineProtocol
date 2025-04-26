package com.Baran.MineProtocol.item;

import com.Baran.MineProtocol.regi.MineProtocolItems;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public enum DragonToolTiers implements Tier {
    DRAGON(500,5.0F, 1.0F,3,14,Ingredient.of(MineProtocolItems.ASTERIAINGOT.get()));


    private final int uses;
    private final float speed;
    private final float attackDamage;
    private final int level;
    private final int enchantmentValue;
    private final Ingredient repairIngredient;



    DragonToolTiers(int uses, float speed, float attackDamage, int level, int enchantmentValue, Ingredient repairIngredient) {
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
