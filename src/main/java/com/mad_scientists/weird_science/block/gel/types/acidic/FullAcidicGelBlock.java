package com.mad_scientists.weird_science.block.gel.types.acidic;

import com.mad_scientists.weird_science.block.gel.FullGelBlock;
import com.mad_scientists.weird_science.init.AllBlocks;
import com.mad_scientists.weird_science.init.AllTags;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class FullAcidicGelBlock extends FullGelBlock {
    public FullAcidicGelBlock(Properties properties) {
        super(properties);
    }
    public void randomTick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull Random random) {
        if (!level.getBlockState(pos.below()).is(AllTags.Blocks.ACIDIC_IMMUNE) && Math.random() > 0.2) {
            level.setBlock(pos.below(), AllBlocks.FULL_ACIDIC_GEL.get().defaultBlockState(), 2);
            level.playSound(null, pos.below(), SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 1.0F, 1.0F);
        }
        if (Math.random() > 0.4) {
            level.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
        }
        if (level.isRaining() && canSeeSky(level, pos)) {
            level.removeBlock(pos, false);
        }
    }
}
