package com.mad_scientists.weird_science.item;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.item.Item;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ScientistsTablet extends Item {
    public ScientistsTablet(Properties properties) {
        super(properties.stacksTo(1));
    }

    //@Nonnull
    //@Override
    //public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
    //    ItemStack stack = playerIn.getItemInHand(handIn);
    //    if(playerIn instanceof ServerPlayer serverPlayer) {
    //        PatchouliAPI.get().openBookGUI(serverPlayer, Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(this)));
    //    }
//
    //    return new InteractionResultHolder<>(InteractionResult.CONSUME, stack);
    //}
}
