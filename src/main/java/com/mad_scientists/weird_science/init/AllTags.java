package com.mad_scientists.weird_science.init;

import com.mad_scientists.weird_science.WeirdScience;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class AllTags {
    public static void init() {
        Items.init();
    }

    public static class Items {
        private static void init() {}

        /** Items that are rich in a value */
        public static final TagKey<Item> VALUE_RICH = tag("value_rich");
        /** Items that can be used to make flux capsules */
        public static final TagKey<Item> FLUX_RICH = tag("value_rich/flux");
        /** Items that can be used to make warp capsules */
        public static final TagKey<Item> WARP_RICH = tag("value_rich/warp");
        /** Items that can be used to make quanta capsules */
        public static final TagKey<Item> QUANTA_RICH = tag("value_rich/quanta");
        /** Makes a tag in the mod domain */
        private static TagKey<Item> tag(String name) {
            return TagKey.create(Registry.ITEM_REGISTRY, WeirdScience.asResource(name));
        }
        /** Makes a tag in the forge domain */
        public static TagKey<Item> forgeTag(String name) {
            return TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("forge", name));
        }
    }

    public static class Blocks {
        private static void init() {}

        public static final TagKey<Block> ACIDIC_IMMUNE = tag("acidic_immune");

        private static TagKey<Block> tag(String name) {
            return TagKey.create(Registry.BLOCK_REGISTRY, WeirdScience.asResource(name));
        }
    }
}
