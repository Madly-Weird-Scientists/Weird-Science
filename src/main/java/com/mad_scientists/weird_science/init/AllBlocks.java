package com.mad_scientists.weird_science.init;

import com.mad_scientists.weird_science.WeirdScience;
import com.mad_scientists.weird_science.block.programming_interface.base.ProgrammerBaseBlock;
import com.mad_scientists.weird_science.block.programming_interface.ProgrammingInterfaceBlock;
import com.mad_scientists.weird_science.block.tinkers_table.TinkersTableBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class AllBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, WeirdScience.ID);

    public static final RegistryObject<Block> TINKERS_TABLE = registerBlockWithoutBlockItem("tinkers_table",
            () -> new TinkersTableBlock(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.NETHERITE_BLOCK).noOcclusion()));
    public static final RegistryObject<ProgrammerBaseBlock> PROGRAMMING_BASE = registerBlockWithoutBlockItem("programming_base",
            () -> new ProgrammerBaseBlock(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.NETHERITE_BLOCK).noOcclusion()));
    public static final RegistryObject<ProgrammingInterfaceBlock> PROGRAMMING_INTERFACE = registerBlockWithoutBlockItem("programming_interface",
            () -> new ProgrammingInterfaceBlock(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.GLASS).noOcclusion()));

    private static <T extends Block> RegistryObject<T> registerBlockWithoutBlockItem(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
