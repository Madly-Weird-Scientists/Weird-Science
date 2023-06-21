package com.mad_scientists.weird_science.content.block;

import com.mad_scientists.weird_science.init.AllBlocks;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LightningRodBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;
@SuppressWarnings("deprecation")
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class AluminumBlock extends Block {
    public AluminumBlock(Properties properties) {
        super(properties);
    }
    public void animateTick(BlockState state, Level level, BlockPos pos, Random random) {
        if (level.isThundering() && (long)level.random.nextInt(200) <= level.getGameTime() % 200L && pos.getY() == level.getHeight(Heightmap.Types.WORLD_SURFACE, pos.getX(), pos.getZ()) - 1) {
            ParticleUtils.spawnParticlesOnBlockFaces(level, pos, ParticleTypes.ELECTRIC_SPARK, UniformInt.of(1, 2));
        }
    }
    @Override
    public void onProjectileHit(Level level, BlockState state, BlockHitResult result, Projectile projectile) {
        if (level.isThundering() && projectile instanceof ThrownTrident && ((ThrownTrident)projectile).isChanneling()) {
            BlockPos blockpos = result.getBlockPos();
            if (level.canSeeSky(blockpos)) {
                LightningBolt lightningbolt = null;
                lightningbolt = EntityType.LIGHTNING_BOLT.create(level);
                lightningbolt.moveTo(Vec3.atBottomCenterOf(blockpos.above()));
                Entity entity = projectile.getOwner();
                lightningbolt.setCause(entity instanceof ServerPlayer ? (ServerPlayer) entity : null);
                level.addFreshEntity(lightningbolt);
                level.playSound((Player) null, blockpos, SoundEvents.TRIDENT_THUNDER, SoundSource.WEATHER, 5.0F, 1.0F);
                level.setBlock(blockpos, AllBlocks.ENERGIZED_ALUMINUM_BLOCK.get().defaultBlockState(), 2);
            }
        }
    }
    public void onLightningStrike(BlockState state, Level level, BlockPos pos) {
        level.setBlock(pos, AllBlocks.ENERGIZED_ALUMINUM_BLOCK.get().defaultBlockState(), 2);
    }
}
