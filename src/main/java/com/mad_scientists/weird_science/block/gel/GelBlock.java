package com.mad_scientists.weird_science.block.gel;

import com.mad_scientists.weird_science.init.AllBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.MultifaceBlock;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.ParametersAreNonnullByDefault;

import static software.bernie.example.block.HabitatBlock.FACING;

@ParametersAreNonnullByDefault
public class GelBlock extends MultifaceBlock {
    public GelBlock(Properties properties) {
        super(properties);
    }

    public final BlockState planeState(BlockState state) {
        return state.equals(this.defaultBlockState()) ? this.defaultBlockState().setValue(FACING, Direction.DOWN) : state;
    }

}
