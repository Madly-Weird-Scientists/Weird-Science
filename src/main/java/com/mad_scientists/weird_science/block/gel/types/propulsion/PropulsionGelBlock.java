package com.mad_scientists.weird_science.block.gel.types.propulsion;

import com.mad_scientists.weird_science.block.gel.GelBlock;
import com.mad_scientists.weird_science.init.AllBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
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
    //public void updateEntityAfterFallOn(BlockGetter getter, Entity entity) {
    //    if (entity.isSuppressingBounce()) {
    //        super.updateEntityAfterFallOn(getter, entity);
    //    } else {
    //        this.bounceUp(entity);
    //    }
//
    //}
//
    //private void bounceUp(Entity entity) {
    //    Vec3 vec3 = entity.getDeltaMovement();
    //    if (vec3.y < 0.0D) {
    //        double d0 = entity instanceof LivingEntity ? 1.0D : 0.4D;
    //        entity.setDeltaMovement(vec3.x, -vec3.y * d0, vec3.z);
    //    }
//
    //}
    //private void travelBounceUp(Entity entity) {
    //    Vec3 vec3 = entity.getDeltaMovement();
    //    if (vec3.y >= 0.0D) {
    //        double d0 = entity instanceof LivingEntity ? 1.0D : 0.4D;
    //        entity.setDeltaMovement(vec3.x * d0, vec3.y, vec3.z * d0);
    //    }
//
    //}
    //public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
    //    double d0 = Math.abs(entity.getDeltaMovement().y);
    //    if (d0 < 0.1D && !entity.isSteppingCarefully()) {
    //        double d1 = 0.4D + d0 * 0.2D;
    //        this.travelBounceUp(entity);
    //        entity.setDeltaMovement(entity.getDeltaMovement().multiply(d1, 2.0D, d1));
    //    }
    //    double d1 = 0.4D + d0 * 0.2D;
    //    entity.setDeltaMovement(entity.getDeltaMovement().multiply(d1, 2.0D, d1));
    //    super.stepOn(level, pos, state, entity);
    //}

    public static BlockState isStateDown (BlockGetter getter, BlockPos blockPos) {
        BlockPos blockpos = blockPos.below();
        BlockState blockstate = getter.getBlockState(blockpos);
        return blockstate.isFaceSturdy(getter, blockpos, Direction.DOWN) ? blockstate : AllBlocks.PROPULSION_GEL.get().getStateForPlacement(blockstate, getter, blockpos, Direction.DOWN);
    }
    @Override
    public void entityInside(BlockState blockstate, Level world, BlockPos pos, Entity entity) {
        super.entityInside(blockstate, world, pos, entity);
        Vec3 vec3 = entity.getDeltaMovement();
        Vec3 dir = entity.getViewVector(1);
        if (entity == null)
            return;
        if (vec3.x < 0.0D && !entity.isSteppingCarefully()) {
            double d0 = entity instanceof LivingEntity ? 1.1D : 0.7D;
            entity.setDeltaMovement(dir.x * d0, dir.y, dir.z);
        }
        if (vec3.z < 0.0D && !entity.isSteppingCarefully()) {
            double d0 = entity instanceof LivingEntity ? 1.1D : 0.7D;
            entity.setDeltaMovement(new Vec3(dir.x, dir.y, dir.z * d0));
        }
        if (-vec3.x < 0.0D && !entity.isSteppingCarefully()) {
            double d0 = entity instanceof LivingEntity ? 1.1D : 0.7D;
            entity.setDeltaMovement(dir.x * d0, dir.y, dir.z);
        }
        if (-vec3.z < 0.0D && !entity.isSteppingCarefully()) {
            double d0 = entity instanceof LivingEntity ? 1.1D : 0.7D;
            entity.setDeltaMovement(new Vec3(dir.x, dir.y, dir.z * d0));
        }
    }
}
