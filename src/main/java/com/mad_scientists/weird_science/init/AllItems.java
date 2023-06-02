package com.mad_scientists.weird_science.init;

import com.mad_scientists.weird_science.WeirdScience;
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
                    new Item.Properties().tab(GeckoLibMod.geckolibItemGroup)));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
