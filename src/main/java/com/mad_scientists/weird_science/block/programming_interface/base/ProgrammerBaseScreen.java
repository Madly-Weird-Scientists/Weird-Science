package com.mad_scientists.weird_science.block.programming_interface.base;

import com.mad_scientists.weird_science.WeirdScience;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ProgrammerBaseScreen extends AbstractContainerScreen<ProgrammerBaseMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(WeirdScience.ID, "textures/gui/programming_base.png");

    public ProgrammerBaseScreen(ProgrammerBaseMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - 177) / 2;
        int y = (height - 192) / 2;

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
