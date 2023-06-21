package com.mad_scientists.weird_science.foundation.util;

import com.mad_scientists.weird_science.WeirdScience;
import com.mad_scientists.weird_science.init.AllItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;

public class ModItemGroup extends CreativeModeTab {

    private static final String PATH = "textures/gui/";

    public ModItemGroup(String label) {
        super(label);
        this.setBackgroundImage(new ResourceLocation(WeirdScience.ID, PATH + "tab_items.png"));
        this.hideTitle();
    }

    @Nonnull
    @Override
    public ItemStack makeIcon() {
        return new ItemStack(AllItems.TAB_ICON.get());
    }

    @Nonnull
    @Override
    public ResourceLocation getTabsImage() {
        return new ResourceLocation(WeirdScience.ID, PATH + "tabs.png");
    }
}
