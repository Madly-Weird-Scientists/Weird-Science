package com.mad_scientists.weird_science.block.programming_interface.base;

import com.mad_scientists.weird_science.WeirdScience;
import com.mad_scientists.weird_science.block.programming_interface.base.ProgrammingBaseBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ProgrammingBaseModel extends AnimatedGeoModel<ProgrammingBaseBlockEntity> {
    @Override
    public ResourceLocation getModelLocation(ProgrammingBaseBlockEntity object) {
        return new ResourceLocation(WeirdScience.ID, "geo/programming_interface.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(ProgrammingBaseBlockEntity object) {
        return new ResourceLocation(WeirdScience.ID + ":textures/block/programming_interface.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(ProgrammingBaseBlockEntity animatable) {
        return new ResourceLocation(WeirdScience.ID, "animations/programming_interface.animation.json");
    }
}
