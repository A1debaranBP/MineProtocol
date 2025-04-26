package com.Baran.MineProtocol.item.armor;

import com.Baran.MineProtocol.regi.MineProtocolItems;
import net.minecraft.Util;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;

public class KaihukuArmorMaterials {

    public static final KaihukuArmorMaterial KAIHUKU = new KaihukuArmorMaterial("kaihuku",20,
            Util.make(new EnumMap<>(ArmorItem.Type.class),
                    (type) -> {
                type.put(ArmorItem.Type.BOOTS,3);
                type.put(ArmorItem.Type.LEGGINGS,5);
                type.put(ArmorItem.Type.CHESTPLATE,6);
                type.put(ArmorItem.Type.HELMET,3);
                    }),10, SoundEvents.ARMOR_EQUIP_IRON,1.0F,0.0F,
            () -> {return Ingredient.of(MineProtocolItems.KAIHUKUINGOT.get());}
            );

}
