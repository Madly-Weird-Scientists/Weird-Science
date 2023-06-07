package com.mad_scientists.weird_science.client;

import com.mad_scientists.weird_science.WeirdScience;
import com.mad_scientists.weird_science.block.programming_interface.base.ProgrammerBaseScreen;
import com.mad_scientists.weird_science.block.programming_interface.base.ProgrammingBaseRenderer;
import com.mad_scientists.weird_science.block.tinkers_table.TinkersTableRenderer;
import com.mad_scientists.weird_science.block.tinkers_table.TinkersTableScreen;
import com.mad_scientists.weird_science.init.AllBlockEntities;
import com.mad_scientists.weird_science.init.AllBlocks;
import com.mad_scientists.weird_science.init.AllMenuTypes;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = WeirdScience.ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientListener {


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
