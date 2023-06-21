package com.mad_scientists.weird_science.foundation.mixin;

import com.mad_scientists.weird_science.content.block.AluminumBlock;
import com.mad_scientists.weird_science.init.AllBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LightningBolt.class)
public class LightningBoltMixin {
    LightningBolt lightningBolt = (LightningBolt)(Object)this;
    @Shadow
    public int life;
    private final Level level = lightningBolt.getLevel();

    @Shadow
    public BlockPos getStrikePosition() {
        Vec3 vec3 = lightningBolt.position();
        return new BlockPos(vec3.x, vec3.y - 1.0E-6D, vec3.z);
    }
    private void powerAluminumBlock() {
        BlockPos blockpos = this.getStrikePosition();
        BlockState blockstate = this.level.getBlockState(blockpos);
        if (blockstate.is(AllBlocks.ALUMINUM_BLOCK.get())) {
            ((AluminumBlock)blockstate.getBlock()).onLightningStrike(blockstate, this.level, blockpos);
        }
    }
    @Inject(at = @At("TAIL") , method = "tick")
    private void injectTick(CallbackInfo info) {
        if (this.life == 2 && !this.level.isClientSide) {
                this.powerAluminumBlock();
        }
    }

}
