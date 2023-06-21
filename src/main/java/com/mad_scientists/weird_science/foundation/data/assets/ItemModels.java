package com.mad_scientists.weird_science.foundation.data.assets;

import com.google.common.collect.Sets;
import com.mad_scientists.weird_science.WeirdScience;
import com.mad_scientists.weird_science.init.AllBlocks;
import com.mad_scientists.weird_science.content.item.gel.GelBombItem;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ItemModels extends ItemModelProvider {
    public static final String GENERATED = "item/generated";
    public ItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, WeirdScience.ID, existingFileHelper);
    }
    protected void registerModels() {
        Set<Item> items = ForgeRegistries.ITEMS.getValues().stream().filter((i) -> {
            return WeirdScience.ID.equals(ForgeRegistries.ITEMS.getKey(i).getNamespace());
        }).collect(Collectors.toSet());
        takeAll(items, (i) -> {
            return i instanceof GelBombItem;
        }).forEach((bomb) -> {
            this.itemGeneratedModel(bomb, this.resourceGelBomb(this.itemName(bomb)));
        });
        items.forEach((item) -> {
            this.itemGeneratedModel(item, this.resourceItem(this.itemName(item)));
        });
        Set<Item> capsuleItems = Sets.newHashSet(
                AllBlocks.PROPULSION_GEL.get().asItem(),
                AllBlocks.REPULSION_GEL.get().asItem()
        );
        takeAll(items, capsuleItems.toArray(new Item[0])).forEach(item -> itemGeneratedModel(item, resourceGelCapsule(itemName(item))));

    }
    public void blockBasedModel(Item item, String suffix) {
        String var10001 = this.itemName(item);
        String var10003 = this.itemName(item);
        this.withExistingParent(var10001, this.resourceBlock(var10003 + suffix));
    }
    public void itemGeneratedModel(Item item, ResourceLocation texture) {
        ((ItemModelBuilder)this.withExistingParent(this.itemName(item), GENERATED)).texture("layer0", texture);
    }
    private String itemName(Item item) {
        return item.getRegistryName().getPath();
    }

    public ResourceLocation resourceBlock(String path) {
        return new ResourceLocation(WeirdScience.ID, "block/" + path);
    }

    public ResourceLocation resourceItem(String path) {
        return new ResourceLocation(WeirdScience.ID, "item/" + path);
    }
    public ResourceLocation resourceGelCapsule(String path) {
        return new ResourceLocation(WeirdScience.ID, "item/gel/capsule/" + path.replace("_gel", ""));
    }
    public ResourceLocation resourceGelBomb(String path) {
        return new ResourceLocation(WeirdScience.ID, "item/gel/bomb/" + path.replace("_gel_bomb", ""));
    }
    @SafeVarargs
    public static <T> Collection<T> takeAll(Set<? extends T> src, T... items) {
        List<T> ret = Arrays.asList(items);
        Object[] var3 = items;
        int var4 = items.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            T item = (T) var3[var5];
            if (!src.contains(item)) {
                WeirdScience.LOGGER.warn("Item {} not found in set", item);
            }
        }

        if (!src.removeAll(ret)) {
            WeirdScience.LOGGER.warn("takeAll array didn't yield anything ({})", Arrays.toString(items));
        }

        return ret;
    }

    public static <T> Collection<T> takeAll(Set<T> src, Predicate<T> pred) {
        List<T> ret = new ArrayList();
        Iterator<T> iter = src.iterator();

        while(iter.hasNext()) {
            T item = iter.next();
            if (pred.test(item)) {
                iter.remove();
                ret.add(item);
            }
        }

        if (ret.isEmpty()) {
            WeirdScience.LOGGER.warn("takeAll predicate yielded nothing", new Throwable());
        }

        return ret;
    }
}
