package com.mad_scientists.weird_science.content.block.modification_station;

import com.mad_scientists.weird_science.WeirdScience;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ModificationScreen extends AbstractContainerScreen<ModificationMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(WeirdScience.ID, "textures/gui/modification_station.png");

    public ModificationScreen(ModificationMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }
    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - 175) / 2;
        int y = (height - 163) / 2;

        this.blit(pPoseStack, x, y, 0, 0, 177, 192);
    }
    @Override
    public void render(PoseStack pPoseStack, int mouseX, int mouseY, float delta) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, mouseX, mouseY, delta);
        renderTooltip(pPoseStack, mouseX, mouseY);
    }
    @Override
    public void renderLabels(PoseStack p_97808_, int p_97809_, int p_97810_) {
    }
}
