package com.mad_scientists.weird_science.integration.jei.categories;

import com.mad_scientists.weird_science.WeirdScience;
import com.mad_scientists.weird_science.content.block.modification_station.Modification;
import com.mad_scientists.weird_science.foundation.util.Lang;
import com.mad_scientists.weird_science.init.AllBlocks;
import com.mad_scientists.weird_science.integration.jei.WeirdScienceRecipeTypes;
import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.Optional;

@SuppressWarnings("removal")
@MethodsReturnNonnullByDefault
public class ModificationModifierCategory implements IRecipeCategory<Modification> {
    public final static ResourceLocation UID = new ResourceLocation(WeirdScience.ID, "modification");
    private final IDrawable background;
    private final IDrawable icon;
    private final String additionSlotName = "addition";

    public ModificationModifierCategory(IGuiHelper helper) {
        ResourceLocation backgroundImage = new ResourceLocation(WeirdScience.ID, "textures/gui/jei/modification.png");
        background = helper.createDrawable(backgroundImage, 0, 0, 136, 71);
        icon = helper.createDrawableIngredient(VanillaTypes.ITEM, new ItemStack(AllBlocks.MODIFICATION_STATION.get()));
    }
    @Override
    public Component getTitle() {
        return Lang.translateDirect("jei.category.modification");
    }
    @Override
    public RecipeType<Modification> getRecipeType() {
        return WeirdScienceRecipeTypes.MODIFICATION;
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public ResourceLocation getUid() {
        return getRecipeType().getUid();
    }

    @Override
    public Class<? extends Modification> getRecipeClass() {
        return  getRecipeType().getRecipeClass();
    }

    @Override
    public void setRecipe(@Nonnull IRecipeLayoutBuilder builder, @Nonnull Modification recipe, @Nonnull IFocusGroup focusGroup) {
        builder.addSlot(RecipeIngredientRole.INPUT, 60, 7).addIngredients(recipe.getAddition()).setSlotName(additionSlotName);
    }
    @Override
    public void draw(Modification recipe, @NotNull IRecipeSlotsView recipeSlotsView, @NotNull PoseStack poseStack, double mouseX, double mouseY) {
        Optional<ItemStack> additionStack = recipeSlotsView.findSlotByName("addition")
                .flatMap(slot1 -> slot1.getDisplayedIngredient(VanillaTypes.ITEM));
        if (additionStack.isEmpty()) {
            return;
        }

        float flux = recipe.getFlux();
        int fluxAddition = (int) (flux);
        String fluxTxt = Lang.translate("jei.category.modification.flux", fluxAddition).string();
        float warp = recipe.getWarp();
        int warpAddition = (int) (warp);
        String warpTxt = Lang.translate("jei.category.modification.warp", warpAddition).string();
        float quanta = recipe.getQuanta();
        int quantaAddition = (int) quanta;
        String quantaTxt = Lang.translate("jei.category.modification.quanta", quantaAddition).string();

        Minecraft minecraft = Minecraft.getInstance();
        Font font = minecraft.font;
        font.draw(poseStack, fluxTxt, 20, 28, 0xFF5be32f);
        font.draw(poseStack, warpTxt, 20, 38, 0xFFc214da);
        font.draw(poseStack, quantaTxt, 20, 48, 0xFF25e2cb);
    }
}
