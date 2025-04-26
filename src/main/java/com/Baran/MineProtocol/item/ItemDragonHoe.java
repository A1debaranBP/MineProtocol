package com.Baran.MineProtocol.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ItemDragonHoe extends HoeItem {
    public ItemDragonHoe() {
        super(DragonToolTiers.DRAGON,0, -2.0F, new Properties());
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();

        if (!level.isClientSide && level instanceof ServerLevel serverLevel) {

            LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(serverLevel);
            if (lightning != null) {
                lightning.moveTo(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
                serverLevel.addFreshEntity(lightning);
            }

            level.playSound(null, pos, SoundEvents.LIGHTNING_BOLT_IMPACT, net.minecraft.sounds.SoundSource.WEATHER, 2.0F, 1.0F);
        }

        return super.useOn(context);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level p_41422_, List<Component> list, TooltipFlag flag){
        list.add(Component.translatable(this.getDescriptionId() + ".text1").withStyle(ChatFormatting.AQUA));
        list.add(Component.translatable(this.getDescriptionId() + ".text2").withStyle(ChatFormatting.GREEN));
    }
}

