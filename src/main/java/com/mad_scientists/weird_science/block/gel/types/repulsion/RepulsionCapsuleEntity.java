package com.mad_scientists.weird_science.block.gel.types.repulsion;

import com.mad_scientists.weird_science.init.AllBlocks;
import com.mad_scientists.weird_science.init.AllEntities;
import com.mad_scientists.weird_science.init.AllItems;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.Util;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.phys.HitResult;

import java.util.Map;


public class RepulsionCapsuleEntity extends AbstractArrow implements ItemSupplier {
    private static final Map<Item, DispenseItemBehavior> DISPENSER_REGISTRY = Util.make(new Object2ObjectOpenHashMap<>(), (p_52723_) -> {
        p_52723_.defaultReturnValue(new DefaultDispenseItemBehavior());
    });
    public RepulsionCapsuleEntity(Level level, Player player) {
        super(AllEntities.REPULSION_CAPSULE_ENTITY.get(), player, level);
        this.pickup = Pickup.DISALLOWED;
    }

    public RepulsionCapsuleEntity(EntityType<RepulsionCapsuleEntity> repulsionCapsuleEntityEntityType, Level level) {
        super(AllEntities.REPULSION_CAPSULE_ENTITY.get(), level);
        this.pickup = Pickup.DISALLOWED;
    }

    public RepulsionCapsuleEntity(Level level, double x, double y, double z) {
        super(AllEntities.REPULSION_CAPSULE_ENTITY.get(), x, y, z, level);
    }


    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(AllItems.GEL_BOMB_REPULSION.get());
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
    public static void registerBehavior(ItemLike itemLike, DispenseItemBehavior dispenseItemBehavior) {
        DISPENSER_REGISTRY.put(itemLike.asItem(), dispenseItemBehavior);
    }

}
