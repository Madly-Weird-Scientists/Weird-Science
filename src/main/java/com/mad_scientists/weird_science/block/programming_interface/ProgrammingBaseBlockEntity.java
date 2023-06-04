package com.mad_scientists.weird_science.block.programming_interface;

import com.mad_scientists.weird_science.init.AllBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class ProgrammingBaseBlockEntity extends BlockEntity implements IAnimatable {
    private final AnimationFactory manager = new AnimationFactory(this);

    public ProgrammingBaseBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(AllBlockEntities.PROGRAMMING_BASE.get(), pWorldPosition, pBlockState);
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        AnimationController controller = event.getController();
        controller.transitionLengthTicks = 0;
        controller.setAnimation(new AnimationBuilder().addAnimation("animation.programming_interface.place", false));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return this.manager;
    }
}
