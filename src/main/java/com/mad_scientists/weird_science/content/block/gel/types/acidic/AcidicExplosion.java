package com.mad_scientists.weird_science.content.block.gel.types.acidic;

import com.mad_scientists.weird_science.init.AllTags;
import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public class AcidicExplosion extends Explosion {
    public AcidicExplosion(Level p_151471_, @Nullable Entity p_151472_, double p_151473_, double p_151474_, double p_151475_, float p_151476_) {
        super(p_151471_, p_151472_, p_151473_, p_151474_, p_151475_, p_151476_);
    }

    public AcidicExplosion(Level p_46024_, @Nullable Entity p_46025_, double p_46026_, double p_46027_, double p_46028_, float p_46029_, List<BlockPos> p_46030_) {
        super(p_46024_, p_46025_, p_46026_, p_46027_, p_46028_, p_46029_, p_46030_);
    }

    public AcidicExplosion(Level p_46041_, @Nullable Entity p_46042_, double p_46043_, double p_46044_, double p_46045_, float p_46046_, boolean p_46047_, BlockInteraction p_46048_, List<BlockPos> p_46049_) {
        super(p_46041_, p_46042_, p_46043_, p_46044_, p_46045_, p_46046_, p_46047_, p_46048_, p_46049_);
    }

    public AcidicExplosion(Level p_46032_, @Nullable Entity p_46033_, double p_46034_, double p_46035_, double p_46036_, float p_46037_, boolean p_46038_, BlockInteraction p_46039_) {
        super(p_46032_, p_46033_, p_46034_, p_46035_, p_46036_, p_46037_, p_46038_, p_46039_);
    }

    public AcidicExplosion(Level level, @Nullable Entity entity, @Nullable DamageSource damageSource, @Nullable ExplosionDamageCalculator calculator, double z, double source, double radius, float p_46058_, boolean fire, BlockInteraction interaction) {
        super(level, entity, damageSource, calculator, z, source, radius, p_46058_, fire, interaction);
    }

    public static Explosion explode(Level level, double toBlow, double hitPlayers, double p_46522_, float p_46523_, boolean p_46524_, BlockInteraction interaction) {
        return explode(level, null, null, toBlow, hitPlayers, p_46522_, p_46523_, p_46524_, interaction);
    }

    public static Explosion explode(Level level, @javax.annotation.Nullable DamageSource damageSource, @javax.annotation.Nullable ExplosionDamageCalculator calculator, double p_46529_, double p_46530_, double p_46531_, float p_46532_, boolean p_46533_, BlockInteraction p_46534_) {
        Explosion explosion = new AcidicExplosion(level, null, damageSource, calculator, p_46529_, p_46530_, p_46531_, p_46532_, p_46533_, p_46534_);
        if (net.minecraftforge.event.ForgeEventFactory.onExplosionStart(level, explosion)) return explosion;
        explosion.explode();
        explosion.finalizeExplosion(true);
        return explosion;
    }


    public void finalizeExplosion(boolean p_46076_) {
        if (this.level.isClientSide) {
            this.level.playLocalSound(this.x, this.y, this.z, SoundEvents.GLASS_BREAK, SoundSource.BLOCKS, 4.0F, (1.0F + (this.level.random.nextFloat() - this.level.random.nextFloat()) * 0.2F) * 0.7F, false);
            this.level.playLocalSound(this.x, this.y, this.z, SoundEvents.NETHERITE_BLOCK_BREAK, SoundSource.BLOCKS, 4.0F, (1.0F + (this.level.random.nextFloat() - this.level.random.nextFloat()) * 0.2F) * 0.7F, false);

        }

        boolean flag = this.blockInteraction != BlockInteraction.NONE;
        if (p_46076_) {
            if (!(this.radius < 6.0F) && flag) {
                this.level.addParticle(ParticleTypes.EXPLOSION_EMITTER, this.x, this.y, this.z, 1.0D, 0.0D, 0.0D);
            } else {
                this.level.addParticle(ParticleTypes.GLOW, this.x, this.y, this.z, 1.0D, 0.0D, 0.0D);
            }
        }

        if (flag) {
            ObjectArrayList<Pair<ItemStack, BlockPos>> objectarraylist = new ObjectArrayList<>();
            Collections.shuffle(this.toBlow, this.level.random);

            for(BlockPos blockpos : this.toBlow) {
                BlockState blockstate = this.level.getBlockState(blockpos);
                Block block = blockstate.getBlock();
                if (!blockstate.isAir()) {
                    BlockPos blockpos1 = blockpos.immutable();
                    this.level.getProfiler().push("explosion_blocks");
                    if (blockstate.canDropFromExplosion(this.level, blockpos, this) && this.level instanceof ServerLevel) {
                        BlockEntity blockentity = blockstate.hasBlockEntity() ? this.level.getBlockEntity(blockpos) : null;
                        LootContext.Builder lootcontext$builder = (new LootContext.Builder((ServerLevel)this.level)).withRandom(this.level.random).withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(blockpos)).withParameter(LootContextParams.TOOL, ItemStack.EMPTY).withOptionalParameter(LootContextParams.BLOCK_ENTITY, blockentity).withOptionalParameter(LootContextParams.THIS_ENTITY, this.source);
                        if (this.blockInteraction == BlockInteraction.DESTROY) {
                            lootcontext$builder.withParameter(LootContextParams.EXPLOSION_RADIUS, this.radius);
                        }

                        blockstate.getDrops(lootcontext$builder).forEach((p_46074_) -> {
                            addBlockDrops(objectarraylist, p_46074_, blockpos1);
                        });
                    }

                    blockstate.onBlockExploded(this.level, blockpos, this);
                    this.level.getProfiler().pop();
                }
            }

            for(Pair<ItemStack, BlockPos> pair : objectarraylist) {
                Block.popResource(this.level, pair.getSecond(), pair.getFirst());
            }
        }

        if (this.fire) {
            for(BlockPos blockpos2 : this.toBlow) {
                if (this.random.nextInt(3) == 0 && this.level.getBlockState(blockpos2).isAir() && this.level.getBlockState(blockpos2.below()).isSolidRender(this.level, blockpos2.below())) {
                    this.level.setBlockAndUpdate(blockpos2, AcidicGelBlock.isStateDown(this.level, blockpos2.above()));
                }
                if (this.random.nextInt(3) == 0 && this.level.getBlockState(blockpos2).is(AllTags.Blocks.GEL_REMOVABLE) && !this.level.getBlockState(blockpos2.below()).isSolidRender(this.level, blockpos2.below())) {
                    this.level.setBlockAndUpdate(blockpos2, AcidicGelBlock.isStateDown(this.level, blockpos2.above()));
                }
            }
        }

    }
}
