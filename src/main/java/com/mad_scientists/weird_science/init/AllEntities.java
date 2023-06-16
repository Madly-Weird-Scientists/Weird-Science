package com.mad_scientists.weird_science.init;

import com.mad_scientists.weird_science.block.gel.types.acidic.AcidicBombEntity;
import com.mad_scientists.weird_science.block.gel.types.propulsion.PropulsionCapsuleEntity;
import com.mad_scientists.weird_science.block.gel.types.repulsion.RepulsionCapsuleEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AllEntities {
    private final static DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES.getRegistryKey(), "weird_science");

    public static RegistryObject<EntityType<RepulsionCapsuleEntity>> REPULSION_CAPSULE_ENTITY = ENTITIES.register("repulsion_gel_bomb_entity", () -> EntityType.Builder.<RepulsionCapsuleEntity>of(RepulsionCapsuleEntity::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).build("repulsion_gel_bomb"));
    public static RegistryObject<EntityType<PropulsionCapsuleEntity>> PROPULSION_CAPSULE_ENTITY = ENTITIES.register("propulsion_gel_bomb_entity", () -> EntityType.Builder.<PropulsionCapsuleEntity>of(PropulsionCapsuleEntity::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).build("propulsion_gel_bomb"));
    public static RegistryObject<EntityType<AcidicBombEntity>> ACIDIC_BOMB_ENTITY = ENTITIES.register("acidic_gel_bomb_entity", () -> EntityType.Builder.<AcidicBombEntity>of(AcidicBombEntity::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).build("acidic_gel_bomb"));
    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }
}
