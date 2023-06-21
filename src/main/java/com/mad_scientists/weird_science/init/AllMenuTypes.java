package com.mad_scientists.weird_science.init;

import com.mad_scientists.weird_science.WeirdScience;
import com.mad_scientists.weird_science.content.block.modification_station.ModificationMenu;
import com.mad_scientists.weird_science.content.block.programming_interface.base.ProgrammerBaseMenu;
import com.mad_scientists.weird_science.content.block.tinkers_table.TinkersTableMenu;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AllMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.CONTAINERS, WeirdScience.ID);

    public static final RegistryObject<MenuType<TinkersTableMenu>> TINKERS_TABLE =
            registerMenuType(TinkersTableMenu::new, "tinkers_table");

    public static final RegistryObject<MenuType<ProgrammerBaseMenu>> PROGRAMMING_BASE =
            registerMenuType(ProgrammerBaseMenu::new, "programming_base");

    public static final RegistryObject<MenuType<ModificationMenu>> MODIFICATION =
            registerMenuType(ModificationMenu::new, "modification");


    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory,
                                                                                                  String name) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
