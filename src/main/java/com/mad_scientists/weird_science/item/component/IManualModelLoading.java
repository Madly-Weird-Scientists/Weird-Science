package com.mad_scientists.weird_science.item.component;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Consumer;

public interface IManualModelLoading {
    @OnlyIn(Dist.CLIENT)
    void loadModels(Consumer<ModelResourceLocation> var1);
}
