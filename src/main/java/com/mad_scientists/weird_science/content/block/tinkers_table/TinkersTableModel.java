package com.mad_scientists.weird_science.content.block.tinkers_table;

import com.mad_scientists.weird_science.WeirdScience;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class TinkersTableModel extends AnimatedGeoModel<TinkersTableBlockEntity> {
    @Override
    public ResourceLocation getAnimationFileLocation(TinkersTableBlockEntity animatable) {
            return new ResourceLocation(WeirdScience.ID, "animations/tinkers_table.animation.json");
    }

    @Override
    public ResourceLocation getModelLocation(TinkersTableBlockEntity animatable) {
            return new ResourceLocation(WeirdScience.ID, "geo/tinkers_table.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(TinkersTableBlockEntity entity) {
            return new ResourceLocation(WeirdScience.ID + ":textures/block/tinkers_table.png");
    }
}
