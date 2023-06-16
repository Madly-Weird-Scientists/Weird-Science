package com.mad_scientists.weird_science.init;

import com.mad_scientists.weird_science.WeirdScience;
import com.mad_scientists.weird_science.block.gel.types.acidic.AcidicGelBlock;
import com.mad_scientists.weird_science.block.gel.types.acidic.FullAcidicGelBlock;
import com.mad_scientists.weird_science.block.gel.types.propulsion.PropulsionGelBlock;
import com.mad_scientists.weird_science.block.gel.types.repulsion.RepulsionGelBlock;
import com.mad_scientists.weird_science.block.modification_station.ModificationBlock;
import com.mad_scientists.weird_science.block.programming_interface.ProgrammingInterfaceBlock;
import com.mad_scientists.weird_science.block.programming_interface.base.ProgrammerBaseBlock;
import com.mad_scientists.weird_science.block.tinkers_table.TinkersTableBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.Tags;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public class AllBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, WeirdScience.ID);

    /** Functional Blocks! **/

    public static final RegistryObject<Block> TINKERS_TABLE = registerBlockWithoutBlockItem("tinkers_table",
            () -> new TinkersTableBlock(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.NETHERITE_BLOCK).noOcclusion()));
    public static final RegistryObject<ProgrammerBaseBlock> PROGRAMMING_BASE = registerBlockWithoutBlockItem("programming_base",
            () -> new ProgrammerBaseBlock(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.NETHERITE_BLOCK).noOcclusion()));
    public static final RegistryObject<ProgrammingInterfaceBlock> PROGRAMMING_INTERFACE = registerBlockWithoutBlockItem("programming_interface",
            () -> new ProgrammingInterfaceBlock(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.GLASS).noOcclusion()));
    public static final RegistryObject<ModificationBlock> MODIFICATION_STATION = registerBlockWithoutBlockItem("modification_station",
            () -> new ModificationBlock(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.NETHERITE_BLOCK).noOcclusion()));

    /** Gel Blocks! **/
    public static final RegistryObject<RepulsionGelBlock> REPULSION_GEL = registerGelBlock("repulsion_gel",
            () -> new RepulsionGelBlock(BlockBehaviour.Properties.of(Material.TOP_SNOW).friction(0.95F).strength(0.2F).noCollission().sound(SoundType.SLIME_BLOCK)), WeirdScience.TAB);
    public static final RegistryObject<PropulsionGelBlock> PROPULSION_GEL = registerGelBlock("propulsion_gel",
            () -> new PropulsionGelBlock(BlockBehaviour.Properties.of(Material.TOP_SNOW).friction(0.95F).strength(0.2F).noCollission().sound(SoundType.SLIME_BLOCK)), WeirdScience.TAB);
    public static final RegistryObject<AcidicGelBlock> ACIDIC_GEL = registerBlock("acidic_gel",
            () -> new AcidicGelBlock(BlockBehaviour.Properties.of(Material.TOP_SNOW).friction(0.95F).strength(0.2F).noCollission().sound(SoundType.SLIME_BLOCK)), WeirdScience.TAB);
    public static final RegistryObject<FullAcidicGelBlock> FULL_ACIDIC_GEL = registerBlock("full_acidic_gel",
            () -> new FullAcidicGelBlock(BlockBehaviour.Properties.of(Material.TOP_SNOW).friction(0.95F).strength(0.2F).sound(SoundType.SLIME_BLOCK)), WeirdScience.TAB);

    /** Decoration Blocks! **/
    public static final RegistryObject<Block> ENERGIZED_ALUMINUM_PANEL = registerBlock("energized_aluminum_panel",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.NETHERITE_BLOCK)), WeirdScience.TAB);
    public static final RegistryObject<Block> ENERGIZED_ALUMINUM_BLOCK = registerBlock("energized_aluminum_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.NETHERITE_BLOCK)), WeirdScience.TAB);
    public static final RegistryObject<Block> DARKENED_ALUMINUM_PANEL = registerBlock("benchsteel_panel",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.NETHERITE_BLOCK)), WeirdScience.TAB);

    private static <T extends Block> RegistryObject<T> registerBlockWithoutBlockItem(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }
    private static <T extends Block> RegistryObject<T> registerGelBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerGelBlockItem(name, toReturn, tab);
        return toReturn;
    }
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return AllItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }
    private static <T extends Block> RegistryObject<Item> registerGelBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return AllItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab).craftRemainder(AllItems.CAPSULE.get())));
    }
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
