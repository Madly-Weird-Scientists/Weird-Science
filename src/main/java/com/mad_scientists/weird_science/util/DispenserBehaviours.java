package com.mad_scientists.weird_science.util;

import com.mad_scientists.weird_science.block.gel.types.propulsion.PropulsionCapsuleEntity;
import com.mad_scientists.weird_science.block.gel.types.repulsion.RepulsionCapsuleEntity;
import com.mad_scientists.weird_science.init.AllItems;
import net.minecraft.core.BlockSource;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import static net.minecraft.world.level.block.DispenserBlock.registerBehavior;

public class DispenserBehaviours implements DispenseItemBehavior {
    @Override
    public ItemStack dispense(BlockSource source, ItemStack stack) {
        return register();
    }
    static ItemStack register() {
        registerBehavior(AllItems.GEL_BOMB_REPULSION.get(), new AbstractProjectileDispenseBehavior() {
            protected Projectile getProjectile(Level level, Position position, ItemStack stack) {
                RepulsionCapsuleEntity repulsion = new RepulsionCapsuleEntity(level, position.x(), position.y(), position.z());
                repulsion.pickup = RepulsionCapsuleEntity.Pickup.DISALLOWED;
                return repulsion;
            }
        });
        registerBehavior(AllItems.GEL_BOMB_PROPULSION.get(), new AbstractProjectileDispenseBehavior() {
            protected Projectile getProjectile(Level level, Position position, ItemStack stack) {
                PropulsionCapsuleEntity propulsion = new PropulsionCapsuleEntity(level, position.x(), position.y(), position.z());
                propulsion.pickup = PropulsionCapsuleEntity.Pickup.DISALLOWED;
                return propulsion;
            }
        });
        return null;
    }
}
