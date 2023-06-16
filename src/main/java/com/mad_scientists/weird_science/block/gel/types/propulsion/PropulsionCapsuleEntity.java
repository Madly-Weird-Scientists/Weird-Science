package com.mad_scientists.weird_science.block.gel.types.propulsion;

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

public class PropulsionCapsuleEntity extends AbstractArrow implements ItemSupplier {
    public PropulsionCapsuleEntity(Level level, Player player) {
        super(AllEntities.PROPULSION_CAPSULE_ENTITY.get(), player, level);
        this.pickup = Pickup.DISALLOWED;
    }

    public PropulsionCapsuleEntity(EntityType<PropulsionCapsuleEntity> propulsionCapsuleEntityEntityType, Level level) {
        super(AllEntities.PROPULSION_CAPSULE_ENTITY.get(), level);
        this.pickup = Pickup.DISALLOWED;
    }

    public PropulsionCapsuleEntity(Level level, double x, double y, double z) {
        super(AllEntities.PROPULSION_CAPSULE_ENTITY.get(), x, y, z, level);
    }


    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(AllItems.GEL_BOMB_PROPULSION.get());
    }

    @Override
    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);
        PropulsionExplosion.explode(level, this.getX(), this.getY(), this.getZ(), 3.0f, true, Explosion.BlockInteraction.NONE);
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
