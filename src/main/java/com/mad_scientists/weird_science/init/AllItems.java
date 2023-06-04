package com.mad_scientists.weird_science.init;

import com.mad_scientists.weird_science.WeirdScience;
import com.mad_scientists.weird_science.block.programming_interface.ProgrammingInterfaceBlockItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import software.bernie.example.GeckoLibMod;

public class AllItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, WeirdScience.ID);

    public static final RegistryObject<BlockItem> TINKERS_TABLE = ITEMS.register("tinkers_table",
            () -> new BlockItem(AllBlocks.TINKERS_TABLE.get(),
                    new Item.Properties().tab(WeirdScience.TAB)));

    public static final RegistryObject<ProgrammingInterfaceBlockItem> PROGRAMMING_INTERFACE = ITEMS.register("programming_interface",
            () -> new ProgrammingInterfaceBlockItem(AllBlocks.PROGRAMMING_BASE.get(),
                    new Item.Properties().tab(WeirdScience.TAB)));

    public static final RegistryObject<Item> TAB_ICON = ITEMS.register("tab_icon",
            () -> new Item(new Item.Properties()));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
