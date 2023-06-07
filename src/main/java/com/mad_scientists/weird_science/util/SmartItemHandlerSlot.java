package com.mad_scientists.weird_science.util;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SmartItemHandlerSlot extends SlotItemHandler {
    public SmartItemHandlerSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }

    public int getMaxStackSize(ItemStack stack) {
        return this.getItemHandler().getSlotLimit(this.getSlotIndex());
    }
}
