package com.mad_scientists.weird_science.content.block.gel;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.ParametersAreNonnullByDefault;

import java.util.Random;

import static software.bernie.example.block.HabitatBlock.FACING;

@ParametersAreNonnullByDefault
@SuppressWarnings("deprecation")
public class GelBlock extends MultifaceBlock {
    public GelBlock(Properties properties) {
        super(properties);
    }

    //public final BlockState planeState(BlockState state) {
    //    return state.equals(this.defaultBlockState()) ? this.defaultBlockState().setValue(FACING, Direction.DOWN) : state;
    //}
    public boolean isRandomlyTicking(BlockState state) {
        return true;
    }
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, Random random) {
        if (level.isRaining() && canSeeSky(level, pos)) {
            level.removeBlock(pos, false);
        }
    }

    public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
        return true;
    }
    public boolean canSeeSky(Level world, BlockPos pos) {
        return world.canSeeSky(pos);
    }
}
