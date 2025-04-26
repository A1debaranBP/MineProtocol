package com.Baran.MineProtocol.villager;

import com.Baran.MineProtocol.main.MineProtocol;
import com.Baran.MineProtocol.regi.MineProtocolBlocks;
import com.google.common.collect.ImmutableSet;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModVillagers {
    public static final DeferredRegister<PoiType> POI_TYPES =
            DeferredRegister.create(ForgeRegistries.POI_TYPES, MineProtocol.MOD_ID);

    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS =
            DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, MineProtocol.MOD_ID);

    public static final RegistryObject<PoiType> KINENHIN_POI = POI_TYPES.register("kinenhin_poi",
            () -> new PoiType(ImmutableSet.copyOf(MineProtocolBlocks.Blocks.ITYAN_BLOCK.get().getStateDefinition().getPossibleStates()),
                    1, 1));

    public static final RegistryObject<VillagerProfession> KINENHIN =
            VILLAGER_PROFESSIONS.register("kinenhin", () -> new VillagerProfession("kinenhin",
                    holder -> holder.get() == KINENHIN_POI.get(), holder -> holder.get() == KINENHIN_POI.get(),
                    ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_ARMORER));

    public static void register(IEventBus eventBus) {
        POI_TYPES.register(eventBus);
        VILLAGER_PROFESSIONS.register(eventBus);
    }
}
