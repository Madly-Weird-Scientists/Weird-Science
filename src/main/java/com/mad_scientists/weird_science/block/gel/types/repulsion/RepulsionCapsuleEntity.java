package com.mad_scientists.weird_science.block.gel.types.repulsion;

import com.mad_scientists.weird_science.init.AllBlocks;
import com.mad_scientists.weird_science.init.AllEntities;
import com.mad_scientists.weird_science.init.AllItems;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

public class RepulsionCapsuleEntity extends AbstractArrow implements ItemSupplier {
    public RepulsionCapsuleEntity(Level level, Player player) {
        super(AllEntities.REPULSION_CAPSULE_ENTITY.get(), player, level);
        this.pickup = Pickup.DISALLOWED;
    }

    public RepulsionCapsuleEntity(EntityType<RepulsionCapsuleEntity> repulsionCapsuleEntityEntityType, Level level) {
        super(AllEntities.REPULSION_CAPSULE_ENTITY.get(), level);
        this.pickup = Pickup.DISALLOWED;
    }


    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(AllBlocks.REPULSION_GEL.get().asItem());
    }

    @Override
    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);
        RepulsionExplosion.explode(level, this.getX(), this.getY(), this.getZ(), 3.0f, true, Explosion.BlockInteraction.NONE);
        if (!this.level.isClientSide()) {
            this.level.broadcastEntityEvent(this, (byte)3);
            this.remove(RemovalReason.DISCARDED);
        }
    }

    @Override
    public ItemStack getItem() {
        return getPickupItem();
    }

    protected SoundEvent getDefaultHitGroundSoundEvent() {
        return SoundEvents.GLASS_BREAK;
    }
}