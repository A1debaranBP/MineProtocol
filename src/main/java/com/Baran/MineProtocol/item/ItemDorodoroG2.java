package com.Baran.MineProtocol.item;

import com.Baran.MineProtocol.effect.ModEffects;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LootingLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class ItemDorodoroG2 extends Item {

    private static final int LOOTING_DURATION = 2400;
    private static final int LOOTING_LEVEL = 0;

    public ItemDorodoroG2 (){
        super(new Properties()
                .stacksTo(64)
                .rarity(Rarity.COMMON)
                .food(new FoodProperties.Builder()
                        .nutrition(1) // 空腹度回復量
                        .saturationMod(0.8F) // 満腹度の回復効率
                        .alwaysEat()
                        .build())
        );
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level p_41422_, List<Component> list, TooltipFlag flag){
        list.add(Component.translatable(this.getDescriptionId() + ".text1").withStyle(ChatFormatting.AQUA));
        list.add(Component.translatable(this.getDescriptionId() + ".text2").withStyle(ChatFormatting.GREEN));
    }

    @SubscribeEvent
    public void onItemEaten(LivingEntityUseItemEvent.Finish event) {
        if (event.getEntity() instanceof Player player) {
            ItemStack item = event.getItem();
            if (item.getItem() == this) {
                player.addEffect(new MobEffectInstance(ModEffects.DOUBLE_DROPS.get(), LOOTING_DURATION, LOOTING_LEVEL));
            }
        }
    }

    @SubscribeEvent
    public void onLooting(LootingLevelEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (player.hasEffect(ModEffects.DOUBLE_DROPS.get())) {
                event.setLootingLevel(event.getLootingLevel() + LOOTING_LEVEL);
            }
        }
    }


}
