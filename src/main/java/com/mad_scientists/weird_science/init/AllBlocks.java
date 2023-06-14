package com.mad_scientists.weird_science.init;

import com.mad_scientists.weird_science.WeirdScience;
import com.mad_scientists.weird_science.block.gel.types.propulsion.PropulsionGelBlock;
import com.mad_scientists.weird_science.block.gel.types.repulsion.RepulsionGelBlock;
import com.mad_scientists.weird_science.block.programming_interface.ProgrammingInterfaceBlock;
import com.mad_scientists.weird_science.block.programming_interface.base.ProgrammerBaseBlock;
import com.mad_scientists.weird_science.block.tinkers_table.TinkersTableBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GlowLichenBlock;
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
    public static final RegistryObject<RepulsionGelBlock> REPULSION_GEL = registerBlock("repulsion_gel",
            () -> new RepulsionGelBlock(BlockBehaviour.Properties.of(Material.ICE).friction(0.95F).noCollission().strength(0.2F).sound(SoundType.SLIME_BLOCK)), WeirdScience.TAB);

    public static final RegistryObject<PropulsionGelBlock> PROPULSION_GEL = registerBlock("propulsion_gel",
            () -> new PropulsionGelBlock(BlockBehaviour.Properties.of(Material.ICE).friction(0.95F).noCollission().strength(0.2F).sound(SoundType.SLIME_BLOCK)), WeirdScience.TAB);


    private static <T extends Block> RegistryObject<T> registerBlockWithoutBlockItem(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return AllItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
