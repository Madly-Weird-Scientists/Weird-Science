package com.mad_scientists.weird_science.content.block.gel.types.acidic;

import com.mad_scientists.weird_science.content.block.gel.GelBlock;
import com.mad_scientists.weird_science.init.AllBlocks;
import com.mad_scientists.weird_science.init.AllTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class AcidicGelBlock extends GelBlock {
    public AcidicGelBlock(Properties properties) {
        super(properties);
    }

    public static BlockState isStateDown (BlockGetter getter, BlockPos blockPos) {
        BlockPos blockpos = blockPos.below();
        BlockState blockstate = getter.getBlockState(blockpos);
        return blockstate.isFaceSturdy(getter, blockpos, Direction.DOWN) ? blockstate : AllBlocks.ACIDIC_GEL.get().getStateForPlacement(blockstate, getter, blockpos, Direction.DOWN);
    }
    public void randomTick(@NotNull BlockState state, ServerLevel level, BlockPos pos, @NotNull Random random) {
        if (!level.getBlockState(pos.below()).is(AllTags.Blocks.ACIDIC_IMMUNE) && Math.random() > 0.2) {
            level.setBlock(pos.below(), AllBlocks.FULL_ACIDIC_GEL.get().defaultBlockState(), 2);
            level.playSound(null, pos.below(), SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 1.0F, 1.0F);
        }
        if (level.isRaining() && canSeeSky(level, pos)) {
            level.removeBlock(pos, false);
        }
    }
    public boolean canSurvive(@NotNull BlockState state, LevelReader level, BlockPos pos) {
        BlockPos belowPos = pos.below();
        BlockState belowState = level.getBlockState(belowPos);
        if (belowState.isAir()) {
            return false;
        } else {
            return !belowState.is(AllTags.Blocks.ACIDIC_IMMUNE) || isStateDown(level, pos).is(AllBlocks.FULL_ACIDIC_GEL.get());
        }
    }
    @Override
    public void entityInside(BlockState blockstate, Level world, BlockPos pos, Entity entity) {
        super.entityInside(blockstate, world, pos, entity);
        if (!entity.isSteppingCarefully()) {
            entity.makeStuckInBlock(blockstate, new Vec3(0.25D, 0.05D, 0.25D));
            if (!world.isClientSide && Math.random() > 0.5) {
                entity.hurt(DamageSource.MAGIC, 1.0F);
            }
        }
    }
}
