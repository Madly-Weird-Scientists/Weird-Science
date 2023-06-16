package com.mad_scientists.weird_science.init;

import com.mad_scientists.weird_science.WeirdScience;
import com.mad_scientists.weird_science.block.gel.types.acidic.AcidicBombItem;
import com.mad_scientists.weird_science.block.gel.types.acidic.AcidicGelBlock;
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

import java.util.function.Supplier;
@SuppressWarnings("unused")
public class AllItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, WeirdScience.ID);

    /** Blocks **/
    public static final RegistryObject<Item> TAB_ICON = ITEMS.register("tab_icon",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<BlockItem> TINKERS_TABLE = ITEMS.register("tinkers_table",
            () -> new BlockItem(AllBlocks.TINKERS_TABLE.get(),
                    new Item.Properties().tab(WeirdScience.TAB)));
    public static final RegistryObject<ProgrammingInterfaceBlockItem> PROGRAMMING_INTERFACE = ITEMS.register("programming_interface",
            () -> new ProgrammingInterfaceBlockItem(AllBlocks.PROGRAMMING_BASE.get(),
                    new Item.Properties().tab(WeirdScience.TAB)));


    /** Gadgets and Components **/
    public static final RegistryObject<ComponentItem> COMPONENT = ITEMS.register("component",
            () -> new ComponentItem(new Item.Properties()));
    public static final RegistryObject<Item> TINKERERS_LENS = ITEMS.register("genius_eye",
            () -> new Item(new Item.Properties().tab(WeirdScience.TAB)));
    public static final RegistryObject<ScientistsTablet> SCIENTISTS_TABLET = ITEMS.register("scientists_tablet",
            () -> new ScientistsTablet(new Item.Properties().tab(WeirdScience.TAB)));

    /** Capsules **/
    public static final RegistryObject<CapsuleItem> CAPSULE = ITEMS.register("capsule",
            () -> new CapsuleItem(new Item.Properties().tab(WeirdScience.TAB)));
    public static final RegistryObject<FluxCapsuleItem> FLUX_CAPSULE = ITEMS.register("flux_capsule",
            () -> new FluxCapsuleItem(new Item.Properties()));
    public static final RegistryObject<WarpCapsuleItem> WARP_CAPSULE = ITEMS.register("warp_capsule",
            () -> new WarpCapsuleItem(new Item.Properties()));
    public static final RegistryObject<QuantaCapsuleItem> QUANTA_CAPSULE = ITEMS.register("quanta_capsule",
            () -> new QuantaCapsuleItem(new Item.Properties()));

    /** Metals **/
    public static final RegistryObject<Item> ENERGIZED_ALUMINUM = registerItem("energized_aluminum_ingot");
    public static final RegistryObject<Item> DARKENED_ALUMINUM = registerItem("benchsteel_ingot");


    /** Gel Bombs **/
    public static final RegistryObject<RepulsionCapsuleItem> GEL_BOMB_REPULSION = ITEMS.register("repulsion_gel_bomb",
            () -> new RepulsionCapsuleItem(new Item.Properties().tab(WeirdScience.TAB)));
    public static final RegistryObject<PropulsionCapsuleItem> GEL_BOMB_PROPULSION = ITEMS.register("propulsion_gel_bomb",
            () -> new PropulsionCapsuleItem(new Item.Properties().tab(WeirdScience.TAB)));
    public static final RegistryObject<AcidicBombItem> GEL_BOMB_ACIDIC = ITEMS.register("acidic_gel_bomb",
            () -> new AcidicBombItem(new Item.Properties().tab(WeirdScience.TAB)));





    /** Registry Util **/
    private static <T extends Item> RegistryObject<T> registerGelBomb(String name, Supplier<T> item) {
        return AllItems.ITEMS.register(name, item);
    }
    private static <T extends Item> RegistryObject<Item> registerItem(String name) {
        return AllItems.ITEMS.register(name, () -> new Item(new Item.Properties().tab(WeirdScience.TAB)));
    }
    private static <T extends Item> RegistryObject<Item> registerMetalSet(String name) {
        RegistryObject<Item> toReturn = ITEMS.register(name, () -> new Item(new Item.Properties().tab(WeirdScience.TAB)));
        //registerIngot(name);
        registerNugget(name);
        return toReturn;
    }
    private static <T extends Item> RegistryObject<Item> registerIngot(String name) {
        return AllItems.ITEMS.register(name + "_ingot", () -> new Item(new Item.Properties().tab(WeirdScience.TAB)));
    }
    private static <T extends Item> RegistryObject<Item> registerNugget(String name) {
        return AllItems.ITEMS.register(name.replace("ingot", "nugget"), () -> new Item(new Item.Properties().tab(WeirdScience.TAB)));
    }
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
