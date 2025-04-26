package com.Baran.MineProtocol.item.armor;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class ArmorKaihukuChestPlate extends ArmorItem {

    private static final UUID HEALTH_BONUS_UUID = UUID.fromString("6d35b116-58f8-4f89-9203-2b7281e4d76d");
    private static final AttributeModifier HEALTH_BONUS = new AttributeModifier(HEALTH_BONUS_UUID, "Chestplate Health Boost", 6.0, AttributeModifier.Operation.ADDITION);

    public ArmorKaihukuChestPlate() {
        super(KaihukuArmorMaterials.KAIHUKU,Type.CHESTPLATE,new Properties().fireResistant());
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onEquipmentChange(LivingEquipmentChangeEvent event) {
        if (event.getEntity() instanceof Player player) {
            ItemStack newArmor = event.getTo();
            ItemStack oldArmor = event.getFrom();

            boolean isEquipping = newArmor.getItem() instanceof ArmorKaihukuChestPlate;
            boolean isUnequipping = oldArmor.getItem() instanceof ArmorKaihukuChestPlate;

            if (isEquipping) {
                if (!player.getAttribute(Attributes.MAX_HEALTH).hasModifier(HEALTH_BONUS)) {
                    player.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(HEALTH_BONUS);
                }
            } else if (isUnequipping) {
                player.getAttribute(Attributes.MAX_HEALTH).removeModifier(HEALTH_BONUS);
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level p_41422_, List<Component> list, TooltipFlag flag){
        list.add(Component.translatable(this.getDescriptionId() + ".text1").withStyle(ChatFormatting.AQUA));
        list.add(Component.translatable(this.getDescriptionId() + ".text2").withStyle(ChatFormatting.GREEN));
    }
}
