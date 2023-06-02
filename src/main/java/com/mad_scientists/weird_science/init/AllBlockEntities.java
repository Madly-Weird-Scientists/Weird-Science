package com.mad_scientists.weird_science.init;

import com.mad_scientists.weird_science.WeirdScience;
import com.mad_scientists.weird_science.block.tinkers_table.TinkersTableBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AllBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, WeirdScience.ID);

    public static final RegistryObject<BlockEntityType<TinkersTableBlockEntity>> TINKERS_TABLE =
            BLOCK_ENTITIES.register("animated_block_entity", () ->
                    BlockEntityType.Builder.of(TinkersTableBlockEntity::new,
                            AllBlocks.TINKERS_TABLE.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
