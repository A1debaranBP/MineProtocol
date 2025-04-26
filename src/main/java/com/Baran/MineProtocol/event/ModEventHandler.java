package com.Baran.MineProtocol.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import com.Baran.MineProtocol.effect.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = "mineprotocol")
public class ModEventHandler {

    @SubscribeEvent
    public static void onLivingDrops(LivingDropsEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;

        if (player.hasEffect(ModEffects.DOUBLE_DROPS.get())) {

            List<ItemEntity> extraDrops = new java.util.ArrayList<>();

            for (ItemEntity item : event.getDrops()) {

                ItemEntity extraDrop = new ItemEntity(
                        item.level(), item.getX(), item.getY(), item.getZ(),
                        item.getItem().copy()
                );
                extraDrops.add(extraDrop);
            }
            event.getDrops().addAll(extraDrops);
        }
    }
}
