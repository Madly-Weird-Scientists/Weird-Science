package com.mad_scientists.weird_science.item.component;

import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import com.mad_scientists.weird_science.WeirdScience;
import com.mad_scientists.weird_science.block.modification_station.Modification;
import com.mad_scientists.weird_science.init.AllItems;
import com.mad_scientists.weird_science.item.LensRequiringItem;
import com.mad_scientists.weird_science.util.Lang;
import net.minecraft.ChatFormatting;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.CuriosApi;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

import static net.minecraft.util.datafix.fixes.ItemIdFix.getItem;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ComponentItem extends LensRequiringItem {

    private ResourceLocation baseModel;
    private ResourceLocation material;

    public ComponentItem(Properties properties) {
        super(properties);
    }
    @Override
    public void fillItemCategory(@NotNull CreativeModeTab group, @NotNull NonNullList<ItemStack> items) {
        if (group == WeirdScience.TAB) {
            ItemStack stack = new ItemStack(this);
            CompoundTag nbt = stack.getOrCreateTag();
            nbt.putInt("Flux", 0);
            nbt.putInt("Warp", 0);
            nbt.putInt("Quanta", 0);
            nbt.putInt("ModelIdentifier", 6);
            nbt.putString("Primary", "Default");
            nbt.putString("Secondary", "Default");
            nbt.putBoolean("isLensPresent", false);
            items.add(stack);
        }
    }

    @Nullable
    public ResourceLocation getModel()
    {
        return baseModel;
    }
    @Nullable
    public ResourceLocation getMaterial()
    {
        return material;
    }
    @Override
    public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(itemstack, world, entity, slot, selected);
        CompoundTag tag = itemstack.getTag();
        if (entity instanceof LivingEntity lv && CuriosApi.getCuriosHelper().findEquippedCurio(AllItems.TINKERERS_LENS.get(), lv).isPresent()) {
            itemstack.getOrCreateTag().putBoolean("isLensPresent", true);
        } else {
            itemstack.getOrCreateTag().putBoolean("isLensPresent", false);
        }
        if (tag != null) {
            if (tag.getString("Secondary").isEmpty()) {
                tag.putString("Secondary", "Default");
            }
        }
    }
    public static String getSecondaryValue(ItemStack stack) {
        CompoundTag nbt = stack.getOrCreateTag();
        return nbt.getString("Secondary");
    }
    public static boolean getPrimaryValue(ItemStack stack, String key) {
        CompoundTag nbt = stack.getOrCreateTag();
        return nbt.getString("Primary").equals(key);
    }


    public String getItemName(ItemStack itemStack) {
        String primary = Lang.translate("material.default.primary").string();
        String secondary = Lang.translate("material.default.secondary").string();
        int flux = 0;
        int warp = 0;
        int quanta = 0;


        CompoundTag tag = itemStack.getTag();
        if (tag != null) {
            flux = tag.getInt("Flux");
            warp = tag.getInt("Warp");
            quanta = tag.getInt("Quanta");
            primary = Lang.translate("material." + tag.getString("Primary").toLowerCase() + ".primary").string();
            secondary = Lang.translate("material." + tag.getString("Secondary").toLowerCase() + ".secondary").string();
            if (tag.getString("Primary").equals("")) {
                primary = Lang.translate("material.default.primary").string();
            }
            if (tag.getString("Secondary").equals("")) {
                primary = Lang.translate("material.default.secondary").string();
            }
        }
        String moduleItemNameKey = primary;
        if (flux != 0 || warp != 0 || quanta != 0) {
            int max = Math.max(flux, Math.max(warp, quanta));
           if (flux == max) {
               moduleItemNameKey += " " + Lang.translate("component.name.flux").string();
           }
           if (warp == max) {
               moduleItemNameKey += " " + Lang.translate("component.name.warp").string();
           }
           if (quanta == max) {
               moduleItemNameKey += " " + Lang.translate("component.name.quanta").string();
           }
        }
        moduleItemNameKey += " " + secondary;

        return moduleItemNameKey;
    }
    @Override
    public Component getName(ItemStack stack) {
        return new TextComponent(getItemName(stack));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        CompoundTag tag = stack.getTag();
        if (tag != null) {
            String primary = Lang.translate("material." + tag.getString("Primary").toLowerCase() + ".primary").string();
            String secondary = Lang.translate("material." + tag.getString("Secondary").toLowerCase() + ".primary").string();
            if (tag.getBoolean("isLensPresent")) {
            if (tag.getString("Secondary").equalsIgnoreCase("gold")) {
                secondary = Lang.translate("material.gold.tooltip").string();
            }
            if (tag.getString("Primary").equalsIgnoreCase("gold")) {
                primary = Lang.translate("material.gold.tooltip").string();
            }
            tooltip.add(Component.nullToEmpty(Lang.translateDirect("component.primary", primary).getString()));
            tooltip.add(Component.nullToEmpty(Lang.translateDirect("component.secondary", secondary).getString()));
            tooltip.add(Component.nullToEmpty(Lang.translateDirect("component.flux", stack.getTag().getInt("Flux")).withStyle(ChatFormatting.GREEN).getString()));
            tooltip.add(Component.nullToEmpty(Lang.translateDirect("component.warp", stack.getTag().getInt("Warp")).withStyle(ChatFormatting.LIGHT_PURPLE).getString()));
            tooltip.add(Component.nullToEmpty(Lang.translateDirect("component.quanta", stack.getTag().getInt("Quanta")).withStyle(ChatFormatting.AQUA).getString()));
        } else if (!tag.getBoolean("isLensPresent")) {
            if (tag.getString("Secondary").equalsIgnoreCase("gold")) {
                secondary = Lang.translate("material.gold.tooltip").string();
            }
            if (tag.getString("Primary").equalsIgnoreCase("gold")) {
                primary = Lang.translate("material.gold.tooltip").string();
            }
            tooltip.add(Component.nullToEmpty(Lang.translateDirect("component.obfuscated.primary", primary).getString()));
            tooltip.add(Component.nullToEmpty(Lang.translateDirect("component.obfuscated.secondary", secondary).getString()));
            tooltip.add(Component.nullToEmpty(Lang.translateDirect("component.obfuscated.flux", stack.getTag().getInt("Flux")).withStyle(ChatFormatting.GREEN).getString()));
            tooltip.add(Component.nullToEmpty(Lang.translateDirect("component.obfuscated.warp", stack.getTag().getInt("Warp")).withStyle(ChatFormatting.LIGHT_PURPLE).getString()));
            tooltip.add(Component.nullToEmpty(Lang.translateDirect("component.obfuscated.quanta", stack.getTag().getInt("Quanta")).withStyle(ChatFormatting.AQUA).getString()));
            }
        }
    }
}
