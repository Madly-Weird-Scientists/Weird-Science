package com.mad_scientists.weird_science.client.component;

import com.mad_scientists.weird_science.WeirdScience;
import com.mad_scientists.weird_science.client.SpecialItemRenderer;
import com.mad_scientists.weird_science.init.AllItems;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = WeirdScience.ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ComponentRenderer extends SpecialItemRenderer {
    public static final ComponentRenderer INSTANCE = new ComponentRenderer();

    public ComponentRenderer() {
    }

    public void renderByItem(@NotNull ItemStack stack, @NotNull ItemTransforms.TransformType transformType, @NotNull PoseStack matrices, @NotNull MultiBufferSource buffer, int light, int overlay) {
        CompoundTag tag = stack.getTag();
        String type = "component";
        String material = "gold";
        if (tag != null) {
            type = tag.getString("Secondary").toLowerCase();
            material = tag.getString("Primary").toLowerCase();
        }

        ModelResourceLocation mat = new ModelResourceLocation("weird_science:textures/item/component/%s/material/%s#inventory".formatted(type.toLowerCase(), material.toLowerCase()));
        ModelResourceLocation typ = new ModelResourceLocation("weird_science:textures/item/component/%s/unmodified#inventory".formatted(type.toLowerCase()));
        this.renderModel(typ, 0, stack, transformType, matrices, buffer, light, overlay, null);
        this.renderModel(mat, 0, stack, transformType, matrices, buffer, light, overlay, null);
        Function<ResourceLocation, TextureAtlasSprite> atlas = Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS);
        atlas.apply(new ResourceLocation(mat.getNamespace(), "item/" + mat.getPath()));
    }
    //@SubscribeEvent
    //public void renderItem() {
    //    INSTANCE.renderByItem(new ItemStack(AllItems.COMPONENT.get()), ItemTransforms.TransformType.NONE, new PoseStack(), (MultiBufferSource) Minecraft.getInstance().renderBuffers().bufferSource().getBuffer(RenderType.cutout()), 15728880, 1);
    //
    //}
}
