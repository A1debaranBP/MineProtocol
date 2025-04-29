package com.Baran.MineProtocol.item;

import com.Baran.MineProtocol.effect.ModEffects;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ItemTaian extends Item {
    public ItemTaian() {
        super(new Properties()
                .stacksTo(64)
                .rarity(Rarity.COMMON)
                .food(new FoodProperties.Builder()
                        .nutrition(1)
                        .saturationMod(0.8F)
                        .alwaysEat()
                        .build())
        );
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (!level.isClientSide) {
            if (entity instanceof Player player) {

                entity.addEffect(new MobEffectInstance(ModEffects.DRAGON_BREATH_IMMUNITY.get(), 3600, 0));

            }
        }

        if (entity instanceof Player player && player.getAbilities().instabuild) {
            return stack;
        }


        stack.shrink(1);
        return stack;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level p_41422_, List<Component> list, TooltipFlag flag){
        list.add(Component.translatable(this.getDescriptionId() + ".text1").withStyle(ChatFormatting.AQUA));
        list.add(Component.translatable(this.getDescriptionId() + ".text2").withStyle(ChatFormatting.GREEN));
    }
}
