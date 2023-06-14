package com.mad_scientists.weird_science.item.component;

import com.mad_scientists.weird_science.WeirdScience;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ComponentModel extends AnimatedGeoModel<ComponentItem> {
    @Override
    public ResourceLocation getModelLocation(ComponentItem object) {
        ItemStack stack = new ItemStack(object);
        CompoundTag tag = stack.getTag();
        String primary = "gold";
        String secondary = "iron";
        if (tag != null) {
            primary = tag.getString("Primary").toLowerCase();
            secondary = tag.getString("Secondary").toLowerCase();
        }
        return new ResourceLocation(WeirdScience.ID, "models/item/component_variants/component_variant_" + primary + "_" + secondary + ".json");
    }

    @Override
    public ResourceLocation getTextureLocation(ComponentItem object) {
        return null;
    }

    @Override
    public ResourceLocation getAnimationFileLocation(ComponentItem animatable) {
        return null;
    }
}
