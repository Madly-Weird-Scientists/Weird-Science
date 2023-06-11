package com.mad_scientists.weird_science.client;

import com.google.common.collect.Maps;
import com.mad_scientists.weird_science.WeirdScience;
import com.mad_scientists.weird_science.block.programming_interface.base.ProgrammerBaseScreen;
import com.mad_scientists.weird_science.block.programming_interface.base.ProgrammingBaseRenderer;
import com.mad_scientists.weird_science.block.tinkers_table.TinkersTableRenderer;
import com.mad_scientists.weird_science.block.tinkers_table.TinkersTableScreen;
import com.mad_scientists.weird_science.init.AllBlockEntities;
import com.mad_scientists.weird_science.init.AllBlocks;
import com.mad_scientists.weird_science.init.AllItems;
import com.mad_scientists.weird_science.init.AllMenuTypes;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.renderer.item.ItemPropertyFunction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.Map;
@SuppressWarnings("deprecation")
@Mod.EventBusSubscriber(modid = WeirdScience.ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientListener {
    private static final Map<Item, Map<ResourceLocation, ItemPropertyFunction>> PROPERTIES = Maps.newHashMap();
    public static void register(Item item, ResourceLocation location, ItemPropertyFunction itemPropertyFunction) {
        PROPERTIES.computeIfAbsent(item, (item1) -> {
            return Maps.newHashMap();
        }).put(location, itemPropertyFunction);
    }


    @SubscribeEvent
    static void registerModelLoader(ModelRegistryEvent event) {

        ItemStack itemStack = new ItemStack(AllItems.COMPONENT.get());
        CompoundTag nbt = itemStack.getOrCreateTag();
        CompoundTag tag = itemStack.getTag();
        int modelIdentifier = nbt.getInt("ModelIdentifier");
        ItemProperties.register(AllItems.COMPONENT.get(), new ResourceLocation("weird_science", "model"), (stack, world, entity, seed) -> {
            if (tag != null) {
                if (tag.getString("Secondary").equals("default")) {
                    return 0;
                } else if (tag.getString("Secondary").equals("iron")) {
                    return 1;
                } else if (tag.getString("Secondary").equals("copper")) {
                    return 2;
                } else if (tag.getString("Secondary").equals("gold")) {
                    return 3;
                } else if (tag.getString("Secondary").equals("aluminum")) {
                    return 4;
                } else if (tag.getString("Secondary").equals("emerald")) {
                    return 5;
                } else if (tag.getString("Secondary").equals("amethyst")) {
                    return 6;
                } else if (tag.getString("Secondary").equals("diamond")) {
                    return 7;
                } else if (tag.getString("Secondary").equals("netherite")) {
                    return 8;
                } else {
                    return 0;
                }
            }
            return 0;
        });
        //ItemProperties.register(AllItems.COMPONENT.get(), new ResourceLocation("weird_science", "model"), (stack, world, entity, seed) -> {
        //    return new Random(seed).nextInt(8);
        //});
    }

    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(AllBlockEntities.TINKERS_TABLE.get(), TinkersTableRenderer::new);
        event.registerBlockEntityRenderer(AllBlockEntities.PROGRAMMING_BASE.get(), ProgrammingBaseRenderer::new);
    }

    @SubscribeEvent
    public static void clientSetup(final FMLClientSetupEvent event) {
        //Inventory & UI Screens
        MenuScreens.register(AllMenuTypes.TINKERS_TABLE.get(), TinkersTableScreen::new);
        MenuScreens.register(AllMenuTypes.PROGRAMMING_BASE.get(), ProgrammerBaseScreen::new);

        //Block / Item Render Types
        ItemBlockRenderTypes.setRenderLayer(AllBlocks.PROGRAMMING_INTERFACE.get(), RenderType.cutout());
    }
}
