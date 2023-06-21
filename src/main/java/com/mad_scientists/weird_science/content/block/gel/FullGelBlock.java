package com.mad_scientists.weird_science.content.block.gel;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HalfTransparentBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
@SuppressWarnings("deprecation")
public class FullGelBlock extends HalfTransparentBlock {

    public FullGelBlock(Properties properties) {
        super(properties);
    }
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

    public VoxelShape getVisualShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }

    public float getShadeBrightness(BlockState state, BlockGetter getter, BlockPos pos) {
        return 1.0F;
    }

    public boolean propagatesSkylightDown(BlockState state, BlockGetter getter, BlockPos pos) {
        return true;
    }
}
