package com.mad_scientists.weird_science.block.programming_interface;

import com.mad_scientists.weird_science.init.AllBlocks;
import com.mad_scientists.weird_science.init.AllShapes;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@SuppressWarnings("deprecation")
public class ProgrammerBaseBlock extends BaseEntityBlock {
    public ProgrammerBaseBlock(Properties properties) {
        super(properties);
    }
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new ProgrammingBaseBlockEntity(pPos, pState);
    }
    @Override
    public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
        super.onPlace(blockstate, world, pos, oldState, moving);
        world.setBlock(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ()), AllBlocks.PROGRAMMING_INTERFACE.get().defaultBlockState(), 27);
    }
    @Override
    public void wasExploded(Level world, BlockPos pos, Explosion e) {
        super.wasExploded(world, pos, e);
        world.destroyBlock(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ()), false);
    }
    @Override
    public boolean onDestroyedByPlayer(BlockState blockstate, Level world, BlockPos pos, Player entity, boolean willHarvest, FluidState fluid) {
        boolean retval = super.onDestroyedByPlayer(blockstate, world, pos, entity, willHarvest, fluid);
        world.destroyBlock(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ()), false);
        return retval;
    }
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return AllShapes.PROGRAMMER_BASE;
    }
    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }
}
