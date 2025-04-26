package com.Baran.MineProtocol.block;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BlockKaihukuOre extends DropExperienceBlock {


    public BlockKaihukuOre() {
        super(Properties.of()
                        .strength(3.0F,3.0F)
                        .sound(SoundType.STONE)
                        .lightLevel(state -> 5)
                        .mapColor(MapColor.STONE)
                        .requiresCorrectToolForDrops()
                ,UniformInt.of(7,12)
        );
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter p_49817_, List<Component> list, TooltipFlag flag){
        list.add(Component.translatable(this.getDescriptionId() + ".text1").withStyle(ChatFormatting.AQUA));
    }
}



