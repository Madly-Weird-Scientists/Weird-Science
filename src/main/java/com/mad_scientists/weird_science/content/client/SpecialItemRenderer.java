package com.mad_scientists.weird_science.content.client;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.ForgeHooksClient;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class SpecialItemRenderer extends BlockEntityWithoutLevelRenderer {
    public SpecialItemRenderer() {
        super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
    }

    public void renderModel(ModelResourceLocation location, int tint, ItemStack stack, ItemTransforms.TransformType transformType, PoseStack matrices, MultiBufferSource buffer, int light, int overlay, Boolean foil) {
        BakedModel model = Minecraft.getInstance().getModelManager().getModel(location);
        if (transformType == ItemTransforms.TransformType.GUI && !model.usesBlockLight()) {
            Lighting.setupForFlatItems();
        }

        matrices.pushPose();
        matrices.translate(0.5, 0.5, 0.5);
        model = ForgeHooksClient.handleCameraTransforms(matrices, model, transformType, transformType == ItemTransforms.TransformType.FIRST_PERSON_LEFT_HAND || transformType == ItemTransforms.TransformType.THIRD_PERSON_LEFT_HAND);
        matrices.translate(-0.5, -0.5, -0.5);
        if (model.isLayered()) {
            Iterator var11 = model.getLayerModels(stack, true).iterator();

            while(var11.hasNext()) {
                Pair<BakedModel, RenderType> layerModel = (Pair)var11.next();
                BakedModel layer = (BakedModel)layerModel.getFirst();
                RenderType renderType = (RenderType)layerModel.getSecond();
                ForgeHooksClient.setRenderType(renderType);
                VertexConsumer consumer = ItemRenderer.getFoilBufferDirect(buffer, renderType, true, foil == null ? stack.hasFoil() : foil);
                Minecraft.getInstance().getItemRenderer().renderModelLists(layer, stack, light, overlay, matrices, consumer);
                if (buffer instanceof MultiBufferSource.BufferSource) {
                    MultiBufferSource.BufferSource src = (MultiBufferSource.BufferSource)buffer;
                    src.endBatch(renderType);
                }
            }

            ForgeHooksClient.setRenderType((RenderType)null);
        } else {
            RenderType renderType = ItemBlockRenderTypes.getRenderType(stack, true);
            VertexConsumer consumer = ItemRenderer.getFoilBufferDirect(buffer, renderType, true, foil == null ? stack.hasFoil() : foil);
            this.renderModelLists(model, tint, matrices, consumer, light, overlay);
            if (buffer instanceof MultiBufferSource.BufferSource) {
                MultiBufferSource.BufferSource src = (MultiBufferSource.BufferSource)buffer;
                src.endBatch(renderType);
            }
        }

        if (transformType == ItemTransforms.TransformType.GUI && !model.usesBlockLight()) {
            Lighting.setupFor3DItems();
        }

        matrices.popPose();
    }

    public void renderModelLists(BakedModel model, int tint, PoseStack matrices, VertexConsumer buffer, int light, int overlay) {
        Random random = new Random();
        Direction[] var8 = Direction.values();
        int var9 = var8.length;

        for(int var10 = 0; var10 < var9; ++var10) {
            Direction direction = var8[var10];
            this.renderQuadList(model.getQuads((BlockState)null, direction, random), tint, matrices, buffer, light, overlay);
        }

        this.renderQuadList(model.getQuads((BlockState)null, (Direction)null, random), tint, matrices, buffer, light, overlay);
    }

    public void renderQuadList(List<BakedQuad> quads, int tint, PoseStack matrices, VertexConsumer buffer, int light, int overlay) {
        PoseStack.Pose matrix = matrices.last();
        Iterator var8 = quads.iterator();

        while(var8.hasNext()) {
            BakedQuad quad = (BakedQuad)var8.next();
            float red = (float)(tint >> 16 & 255) / 255.0F;
            float green = (float)(tint >> 8 & 255) / 255.0F;
            float blue = (float)(tint & 255) / 255.0F;
            buffer.putBulkData(matrix, quad, red, green, blue, light, overlay, true);
        }

    }
}
