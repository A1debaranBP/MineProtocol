package com.Baran.MineProtocol.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)

public class ItemDragonBow extends BowItem {

    private static final int SKILL_DURATION_TICKS = 600;
    private static final int SKILL_COOLDOWN_TICKS = 1800;
    private static final HashMap<UUID, Integer> cooldowns = new HashMap<>();


    public ItemDragonBow() {
        super(new Properties().durability(500));
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (player.isShiftKeyDown()) {
            UUID playerUUID = player.getUUID();

            if (cooldowns.getOrDefault(playerUUID, 0) > 0) {
                return InteractionResultHolder.fail(stack);
            }

            if (!level.isClientSide) {

                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, SKILL_DURATION_TICKS, 0));

                level.playSound(null, player.getX(), player.getY(), player.getZ(),
                        SoundEvents.BEACON_ACTIVATE, SoundSource.PLAYERS, 1.0F, 1.0F);

                cooldowns.put(playerUUID, SKILL_COOLDOWN_TICKS);
            }
            return InteractionResultHolder.success(stack);
        }

        return super.use(level, player, hand);
    }


    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 72000;
    }

    public static float getPowerForTime(int useTicks) {
        float f = (float) useTicks / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        return Math.min(f, 1.0F);
    }



    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            UUID playerUUID = event.player.getUUID();
            cooldowns.put(playerUUID, Math.max(0, cooldowns.getOrDefault(playerUUID, 0) - 1));
        }
    }



    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level p_41422_, List<Component> list, TooltipFlag flag){
        list.add(Component.translatable(this.getDescriptionId() + ".text1").withStyle(ChatFormatting.AQUA));
        list.add(Component.translatable(this.getDescriptionId() + ".text2").withStyle(ChatFormatting.GREEN));
    }

}
