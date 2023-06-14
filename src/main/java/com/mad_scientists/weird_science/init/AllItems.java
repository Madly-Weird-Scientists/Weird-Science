package com.mad_scientists.weird_science.init;

import com.mad_scientists.weird_science.WeirdScience;
import com.mad_scientists.weird_science.block.gel.types.propulsion.PropulsionCapsuleItem;
import com.mad_scientists.weird_science.block.gel.types.repulsion.RepulsionCapsuleItem;
import com.mad_scientists.weird_science.block.programming_interface.ProgrammingInterfaceBlockItem;
import com.mad_scientists.weird_science.item.ScientistsTablet;
import com.mad_scientists.weird_science.item.capsule.CapsuleItem;
import com.mad_scientists.weird_science.item.capsule.FluxCapsuleItem;
import com.mad_scientists.weird_science.item.capsule.QuantaCapsuleItem;
import com.mad_scientists.weird_science.item.capsule.WarpCapsuleItem;
import com.mad_scientists.weird_science.item.component.ComponentItem;
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
    public static final RegistryObject<ComponentItem> COMPONENT = ITEMS.register("component",
            () -> new ComponentItem(new Item.Properties()));
    public static final RegistryObject<Item> TINKERERS_LENS = ITEMS.register("tinkerers_lens",
            () -> new Item(new Item.Properties().tab(WeirdScience.TAB)));
    public static final RegistryObject<Item> TAB_ICON = ITEMS.register("tab_icon",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<ScientistsTablet> SCIENTISTS_TABLET = ITEMS.register("scientists_tablet",
            () -> new ScientistsTablet(new Item.Properties().tab(WeirdScience.TAB)));
    public static final RegistryObject<CapsuleItem> CAPSULE = ITEMS.register("capsule",
            () -> new CapsuleItem(new Item.Properties().tab(WeirdScience.TAB)));
    public static final RegistryObject<FluxCapsuleItem> FLUX_CAPSULE = ITEMS.register("flux_capsule",
            () -> new FluxCapsuleItem(new Item.Properties()));
    public static final RegistryObject<WarpCapsuleItem> WARP_CAPSULE = ITEMS.register("warp_capsule",
            () -> new WarpCapsuleItem(new Item.Properties()));
    public static final RegistryObject<QuantaCapsuleItem> QUANTA_CAPSULE = ITEMS.register("quanta_capsule",
            () -> new QuantaCapsuleItem(new Item.Properties()));


    public static final RegistryObject<RepulsionCapsuleItem> GEL_CAPSULE_REPULSION = ITEMS.register("repulsion_gel_capsule",
            () -> new RepulsionCapsuleItem(new Item.Properties().tab(WeirdScience.TAB)));
    public static final RegistryObject<PropulsionCapsuleItem> GEL_CAPSULE_PROPULSION = ITEMS.register("propulsion_gel_capsule",
            () -> new PropulsionCapsuleItem(new Item.Properties().tab(WeirdScience.TAB)));




    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
