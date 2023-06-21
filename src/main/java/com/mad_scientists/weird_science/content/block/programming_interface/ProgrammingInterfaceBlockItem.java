package com.mad_scientists.weird_science.content.block.programming_interface;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class ProgrammingInterfaceBlockItem extends BlockItem {
    public ProgrammingInterfaceBlockItem(Block block, Item.Properties properties) {
        super(block, properties);
    }
    protected boolean placeBlock(BlockPlaceContext blockPlaceContext, BlockState blockState) {
        BlockPos blockpos = blockPlaceContext.getClickedPos().above();
        LevelAccessor world = blockPlaceContext.getLevel();
        double x = blockpos.getX();
        double y = blockpos.getY();
        double z = blockpos.getZ();
        if ((world.getBlockState(new BlockPos(x, y, z))).getBlock() == Blocks.AIR) {
            return super.placeBlock(blockPlaceContext, blockState);
        }
            return false;
    }
}
