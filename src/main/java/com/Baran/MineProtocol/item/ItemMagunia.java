package com.Baran.MineProtocol.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
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

public class ItemMagunia extends Item {
    public ItemMagunia() {
        super(new Properties()
                .rarity(Rarity.COMMON)
                .food(new FoodProperties.Builder()
                        .alwaysEat()
                        .nutrition(0)
                        .saturationMod(0.0F)
                        .build())
        );
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (!level.isClientSide) {
            if (entity instanceof Player player) {

                entity.addEffect(new MobEffectInstance(MobEffects.SATURATION, 6000, 0));


                level.playSound(null, player.getX(), player.getY(), player.getZ(),
                        SoundEvents.GENERIC_DRINK, SoundSource.PLAYERS, 1.0F, 1.0F);
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
