package com.mad_scientists.weird_science.content.item.gel.blob_launcher.model;

import com.mad_scientists.weird_science.WeirdScience;
import com.mad_scientists.weird_science.content.item.gel.blob_launcher.GelBlobLauncherItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

import static com.mad_scientists.weird_science.content.item.gel.blob_launcher.model.GelLauncherTypeResourceLocations.ACIDIC;

public class AcidicGelBlobLauncherModel extends AnimatedGeoModel<GelBlobLauncherItem> {
    @Override
    public ResourceLocation getModelLocation(GelBlobLauncherItem object) {
        return new ResourceLocation(WeirdScience.ID, "geo/gel_blob_launcher.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(GelBlobLauncherItem object) {
        return ACIDIC.getTexture();
    }

    @Override
    public ResourceLocation getAnimationFileLocation(GelBlobLauncherItem animatable) {
        return new ResourceLocation(WeirdScience.ID, "animations/gel_blob_launcher.animation.json");
    }
}
