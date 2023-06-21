package com.mad_scientists.weird_science.foundation;

import com.mad_scientists.weird_science.content.block.gel.types.acidic.AcidicBombEntity;
import com.mad_scientists.weird_science.content.block.gel.types.propulsion.PropulsionBombEntity;
import com.mad_scientists.weird_science.content.block.gel.types.repulsion.RepulsionBombEntity;
import com.mad_scientists.weird_science.init.AllItems;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class CommonSetup {
    public static void init(final FMLCommonSetupEvent event) {
        registerDispenserBehaviors();
    }
    public static void registerDispenserBehaviors() {
        DispenserBlock.registerBehavior(AllItems.GEL_BOMB_ACIDIC.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new AcidicBombEntity(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(AllItems.GEL_BOMB_PROPULSION.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new PropulsionBombEntity(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
        DispenserBlock.registerBehavior(AllItems.GEL_BOMB_REPULSION.get(), new AbstractProjectileDispenseBehavior()
        {
            @Override
            protected Projectile getProjectile(Level pLevel, Position pPosition, ItemStack pStack) {
                return new RepulsionBombEntity(pLevel, pPosition.x(), pPosition.y(), pPosition.z());
            }
        });
    }
}
