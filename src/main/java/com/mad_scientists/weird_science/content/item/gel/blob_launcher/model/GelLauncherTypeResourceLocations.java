package com.mad_scientists.weird_science.content.item.gel.blob_launcher.model;

import com.mad_scientists.weird_science.WeirdScience;
import net.minecraft.resources.ResourceLocation;

public enum GelLauncherTypeResourceLocations {
    PROPULSION(new ResourceLocation(WeirdScience.ID,"textures/item/gel/blob_launcher/propulsion.png")),
    ACIDIC(new ResourceLocation(WeirdScience.ID,"textures/item/gel/blob_launcher/acidic.png")),
    REPULSION(new ResourceLocation(WeirdScience.ID,"textures/item/gel/blob_launcher/repulsion.png")),
    STICKY(new ResourceLocation(WeirdScience.ID,"textures/item/gel/blob_launcher/sticky.png")),
    CLINGER(new ResourceLocation(WeirdScience.ID,"textures/item/gel/blob_launcher/clinger.png")),
    SPIKE(new ResourceLocation(WeirdScience.ID,"textures/item/gel/blob_launcher/spike.png")),
    EXPANDING(new ResourceLocation(WeirdScience.ID,"textures/item/gel/blob_launcher/expanding.png")),
    SENSITIVE(new ResourceLocation(WeirdScience.ID,"textures/item/gel/blob_launcher/sensitive.png")),

    ;

    private final ResourceLocation texture;

    GelLauncherTypeResourceLocations(ResourceLocation texture) {
        this.texture = texture;
    }

    public ResourceLocation getTexture() {
        return texture;
    }
}
