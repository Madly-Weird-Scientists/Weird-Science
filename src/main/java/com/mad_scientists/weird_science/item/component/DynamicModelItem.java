package com.mad_scientists.weird_science.item.component;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;

public interface DynamicModelItem {
    String NBT_GENERIC_MODEL_KEY = "ComponentModelId";

    /** @deprecated */
    @Deprecated(
            forRemoval = true
    )
    static Optional<ResourceLocation> getGenericModelId(ItemStack itemStack) {
        CompoundTag nbt = itemStack.getTag();
        if (nbt == null) {
            return Optional.empty();
        } else {
            String componentModelId = nbt.getString("ComponentModelId");
            return componentModelId.isEmpty() ? Optional.empty() : Optional.of(new ResourceLocation(componentModelId));
        }
    }

    /** @deprecated */
    @Deprecated(
            forRemoval = true
    )
    static void setGenericModelId(ItemStack itemStack, ResourceLocation modelId) {
        CompoundTag nbt = itemStack.getTag();
        nbt.putString("ComponentModelId", modelId.toString());
    }

    Optional<ResourceLocation> getDynamicModelId(ItemStack var1);
}
