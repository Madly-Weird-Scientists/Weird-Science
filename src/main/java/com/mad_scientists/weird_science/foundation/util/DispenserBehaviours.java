package com.mad_scientists.weird_science.foundation.util;

import com.mad_scientists.weird_science.content.block.gel.types.propulsion.PropulsionBombEntity;
import com.mad_scientists.weird_science.content.block.gel.types.repulsion.RepulsionBombEntity;
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
                RepulsionBombEntity repulsion = new RepulsionBombEntity(level, position.x(), position.y(), position.z());
                repulsion.pickup = RepulsionBombEntity.Pickup.DISALLOWED;
                return repulsion;
            }
        });
        registerBehavior(AllItems.GEL_BOMB_PROPULSION.get(), new AbstractProjectileDispenseBehavior() {
            protected Projectile getProjectile(Level level, Position position, ItemStack stack) {
                PropulsionBombEntity propulsion = new PropulsionBombEntity(level, position.x(), position.y(), position.z());
                propulsion.pickup = PropulsionBombEntity.Pickup.DISALLOWED;
                return propulsion;
            }
        });
        return null;
    }
}
