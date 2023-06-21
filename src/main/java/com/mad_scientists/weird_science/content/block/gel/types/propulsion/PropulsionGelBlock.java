package com.mad_scientists.weird_science.content.block.gel.types.propulsion;

import com.mad_scientists.weird_science.content.block.gel.GelBlock;
import com.mad_scientists.weird_science.content.config.CommonConfig;
import com.mad_scientists.weird_science.init.AllBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.Vec3;

import javax.annotation.ParametersAreNonnullByDefault;

@SuppressWarnings("deprecation")
@ParametersAreNonnullByDefault
public class PropulsionGelBlock extends GelBlock {
    public PropulsionGelBlock(Properties properties) {
        super(properties);
    }
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float p_154571_) {
        if (entity.isSuppressingBounce()) {
            super.fallOn(level, state, pos, entity, p_154571_);
        } else {
            entity.causeFallDamage(p_154571_, 0.0F, DamageSource.FALL);
        }
    }
    public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
        return !context.getItemInHand().is(AllBlocks.PROPULSION_GEL.get().asItem()) || super.canBeReplaced(state, context);
    }
    public static BlockState isStateDown (BlockGetter getter, BlockPos blockPos) {
        BlockPos blockpos = blockPos.below();
        BlockState blockstate = getter.getBlockState(blockpos);
        return blockstate.isFaceSturdy(getter, blockpos, Direction.DOWN) ? blockstate : AllBlocks.PROPULSION_GEL.get().getStateForPlacement(blockstate, getter, blockpos, Direction.DOWN);
    }
    @Override
    public float getFriction() {
        return 0.989F;
    }

    public Double MultXZ = CommonConfig.PROPULSION_GEL_XZ_MODIFIER.get();
    public Double MultY = CommonConfig.PROPULSION_GEL_Y_MODIFIER.get();
    @Override
    public void entityInside(BlockState blockstate, Level world, BlockPos pos, Entity entity) {
        super.entityInside(blockstate, world, pos, entity);
        Vec3 vec3 = entity.getDeltaMovement();
        Vec3 direction = entity.getLookAngle();
        if (!entity.isSteppingCarefully()) {
            entity.setDeltaMovement(vec3.multiply(MultXZ, 1.0D, MultXZ));
            if (vec3.y > 0.0D && !blockstate.getValue(BlockStateProperties.UP) && !blockstate.getValue(BlockStateProperties.DOWN)) {
                entity.setDeltaMovement(vec3.multiply(MultXZ, MultY, MultXZ));
            }
            //if (!blockstate.getValue(BlockStateProperties.DOWN) && !blockstate.getValue(BlockStateProperties.UP)) {
            //    if (vec3.z > 0.0D && blockstate.getValue(BlockStateProperties.SOUTH)) {
            //        entity.setDeltaMovement(new Vec3(vec3.x, vec3.y, vec3.z + 0.3));
            //    }
            //    if (vec3.z < 0.0D && blockstate.getValue(BlockStateProperties.NORTH)) {
            //        entity.setDeltaMovement(new Vec3(vec3.x, vec3.y, vec3.z + 0.3));
            //    }
            //    if (vec3.x > 0.0D && blockstate.getValue(BlockStateProperties.EAST)) {
            //        entity.setDeltaMovement(new Vec3(vec3.x + 0.3, vec3.y, vec3.z));
            //    }
            //    if (vec3.x < 0.0D && blockstate.getValue(BlockStateProperties.WEST)) {
            //        entity.setDeltaMovement(new Vec3(vec3.x + 0.3, vec3.y, vec3.z));
            //    }
            //}
        }
    }
}
