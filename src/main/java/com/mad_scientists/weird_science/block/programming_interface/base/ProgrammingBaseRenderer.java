package com.mad_scientists.weird_science.block.programming_interface.base;

import com.mad_scientists.weird_science.block.programming_interface.base.ProgrammingBaseBlockEntity;
import com.mad_scientists.weird_science.block.programming_interface.base.ProgrammingBaseModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class ProgrammingBaseRenderer extends GeoBlockRenderer<ProgrammingBaseBlockEntity> {
    public ProgrammingBaseRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(rendererDispatcherIn, new ProgrammingBaseModel());
    }
    @Override
    public RenderType getRenderType(ProgrammingBaseBlockEntity animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }
}
