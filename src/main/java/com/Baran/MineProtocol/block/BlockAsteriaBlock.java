package com.Baran.MineProtocol.block;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BlockAsteriaBlock extends Block {

    public BlockAsteriaBlock() {
        super(Properties.of()
                .strength(5.0F,1500)
                .sound(SoundType.METAL)
                .lightLevel(state -> 5)
                .requiresCorrectToolForDrops()
        );
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter p_49817_, List<Component> list, TooltipFlag flag){
        list.add(Component.translatable(this.getDescriptionId() + ".text1").withStyle(ChatFormatting.AQUA));
    }
}
