package com.Baran.MineProtocol.item.fish;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CookedTuna extends Item {
    public CookedTuna() {
        super(new Properties()
                .stacksTo(64)
                .rarity(Rarity.RARE)
                .food(new FoodProperties
                        .Builder()
                        .nutrition(18)
                        .saturationMod(3.0F)
                        .build())
        );
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level p_41422_, List<Component> list, TooltipFlag flag){
        list.add(Component.translatable(this.getDescriptionId() + ".text1").withStyle(ChatFormatting.AQUA));
    }
}
