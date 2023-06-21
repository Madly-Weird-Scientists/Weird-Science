package com.mad_scientists.weird_science.content.item.gel.blob_launcher;

import com.mad_scientists.weird_science.WeirdScience;
import com.mad_scientists.weird_science.content.block.gel.types.acidic.AcidicBombEntity;
import com.mad_scientists.weird_science.content.block.gel.types.propulsion.PropulsionBombEntity;
import com.mad_scientists.weird_science.content.block.gel.types.repulsion.RepulsionBombEntity;
import com.mad_scientists.weird_science.content.item.gel.GelBombItem;
import com.mad_scientists.weird_science.content.item.gel.blob_launcher.renderer.AcidicGelBlobLauncherRenderer;
import com.mad_scientists.weird_science.content.item.gel.blob_launcher.renderer.GelBlobLauncherRenderer;
import com.mad_scientists.weird_science.content.item.gel.blob_launcher.renderer.PropulsionGelBlobLauncherRenderer;
import com.mad_scientists.weird_science.content.item.gel.blob_launcher.renderer.RepulsionGelBlobLauncherRenderer;
import com.mad_scientists.weird_science.foundation.util.Lang;
import com.mad_scientists.weird_science.init.AllItems;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.IItemRenderProperties;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.network.GeckoLibNetwork;
import software.bernie.geckolib3.network.ISyncable;
import software.bernie.geckolib3.util.GeckoLibUtil;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class GelBlobLauncherItem extends ProjectileWeaponItem implements Vanishable, IAnimatable, ISyncable {
    public GelBlobLauncherItem(Properties properties) {
        super(properties);
        GeckoLibNetwork.registerSyncable(this);
    }

    @Override
    public void fillItemCategory(@NotNull CreativeModeTab group, @NotNull NonNullList<ItemStack> items) {
        if (group == WeirdScience.TAB) {
            ItemStack stackEmpty = new ItemStack(this);
            CompoundTag nbtEmpty = stackEmpty.getOrCreateTag();
            nbtEmpty.putInt("LoadsLeft", 0);
            nbtEmpty.putString("LoadedGel", "empty");
            items.add(stackEmpty);
            ItemStack stackAcidic = new ItemStack(this);
            CompoundTag nbtAcidic = stackAcidic.getOrCreateTag();
            nbtAcidic.putInt("LoadsLeft", 8);
            nbtAcidic.putString("LoadedGel", "acidic");
            items.add(stackAcidic);
            ItemStack stackPropulsion = new ItemStack(this);
            CompoundTag nbtPropulsion = stackPropulsion.getOrCreateTag();
            nbtPropulsion.putInt("LoadsLeft", 8);
            nbtPropulsion.putString("LoadedGel", "propulsion");
            items.add(stackPropulsion);
            ItemStack stackRepulsion = new ItemStack(this);
            CompoundTag nbtRepulsion = stackRepulsion.getOrCreateTag();
            nbtRepulsion.putInt("LoadsLeft", 8);
            nbtRepulsion.putString("LoadedGel", "repulsion");
            items.add(stackRepulsion);
        }
    }
    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return (stack) -> stack.getItem() instanceof GelBombItem;
    }
    @Override
    public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(itemstack, world, entity, slot, selected);
        CompoundTag tag = itemstack.getTag();
        if (tag != null) {
            if (tag.getString("LoadedGel").isEmpty()) {
                tag.putString("LoadedGel", "empty");
            }
            if (!tag.getString("LoadedGel").equals("empty") && tag.getInt("LoadsLeft") == 0) {
                tag.putString("LoadedGel", "empty");
            }
        }

    }

    public static ResourceLocation textureLocation(ItemStack itemstack) {
        CompoundTag nbt = itemstack.getTag();
        if (nbt != null) {
            return new ResourceLocation(WeirdScience.ID, "textures/item/gel/blob_launcher/" + nbt.getString("LoadedGel") + ".png");
        }
        return new ResourceLocation(WeirdScience.ID, "textures/item/gel/blob_launcher/default.png");
    }
    /** Actual Projectile Stuff **/
    public static final int MAX_DRAW_DURATION = 20;
    public static final int DEFAULT_RANGE = 5;

    @Override
    public int getDefaultProjectileRange() {
        return DEFAULT_RANGE;
    }
    public int getUseDuration(ItemStack p_40680_) {
        return 72000;
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        InteractionResultHolder<ItemStack> ret = ForgeEventFactory.onArrowNock(itemstack, level, player, hand, true);
        if (!level.isClientSide) {
            final int id = GeckoLibUtil.guaranteeIDForStack(itemstack, (ServerLevel) level);
            final PacketDistributor.PacketTarget target = PacketDistributor.TRACKING_ENTITY_AND_SELF
                    .with(() -> player);
            GeckoLibNetwork.syncAnimation(target, this, id, ANIM_CHARGE);
        }
        if (ret != null) return ret;

        if (!player.getAbilities().instabuild) {
            return InteractionResultHolder.fail(itemstack);
        } else {
            player.startUsingItem(hand);
            return InteractionResultHolder.consume(itemstack);
        }
    }
    public static float getPowerForTime(int p_40662_) {
        float f = (float)p_40662_ / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }
    public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int var4) {
        if (entity instanceof Player player) {
            boolean flag = player.getAbilities().instabuild || EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, stack) > 0;
            ItemStack itemstack = player.getProjectile(stack);

            int i = this.getUseDuration(stack) - var4;
            i = ForgeEventFactory.onArrowLoose(stack, level, player, i, !itemstack.isEmpty() || flag);
            if (i < 0) return;

            if (flag) {
                float f = getPowerForTime(i);
                if (!((double)f < 0.1D)) {
                    Player playerEntity = (Player) entity;
                    if (!level.isClientSide) {
                        playerEntity.getCooldowns().addCooldown(this, 100);
                        if (stack.getTag() != null) {
                            if (stack.getTag().getString("LoadedGel").equals("acidic") && stack.getTag().getInt("LoadsLeft") > 0) {
                                AcidicBombEntity acidicBomb = new AcidicBombEntity(level, player);
                                acidicBomb = acidicBomb(acidicBomb);
                                acidicBomb.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, f * 3.0F, 1.0F);
                                stack.hurtAndBreak(1, player, (p_40665_) -> p_40665_.broadcastBreakEvent(player.getUsedItemHand()));
                                stack.getTag().putInt("LoadsLeft", stack.getTag().getInt("LoadsLeft") - 1);
                                level.addFreshEntity(acidicBomb);
                            } else if (stack.getTag().getString("LoadedGel").equals("propulsion") && stack.getTag().getInt("LoadsLeft") > 0) {
                                PropulsionBombEntity propulsionBomb = new PropulsionBombEntity(level, player);
                                propulsionBomb = propulsionBomb(propulsionBomb);
                                propulsionBomb.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, f * 3.0F, 1.0F);
                                stack.hurtAndBreak(1, player, (p_40665_) -> p_40665_.broadcastBreakEvent(player.getUsedItemHand()));
                                stack.getTag().putInt("LoadsLeft", stack.getTag().getInt("LoadsLeft") - 1);
                                level.addFreshEntity(propulsionBomb);
                            } else if (stack.getTag().getString("LoadedGel").equals("repulsion") && stack.getTag().getInt("LoadsLeft") > 0) {
                                RepulsionBombEntity repulsionBomb = new RepulsionBombEntity(level, player);
                                repulsionBomb = repulsionBomb(repulsionBomb);
                                repulsionBomb.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, f * 3.0F, 1.0F);
                                stack.hurtAndBreak(1, player, (p_40665_) -> p_40665_.broadcastBreakEvent(player.getUsedItemHand()));
                                stack.getTag().putInt("LoadsLeft", stack.getTag().getInt("LoadsLeft") - 1);
                                level.addFreshEntity(repulsionBomb);
                            }
                        }
                    }
                    if (!level.isClientSide) {
                        final int id = GeckoLibUtil.guaranteeIDForStack(stack, (ServerLevel) level);
                        final PacketDistributor.PacketTarget target = PacketDistributor.TRACKING_ENTITY_AND_SELF
                                .with(() -> playerEntity);
                        GeckoLibNetwork.syncAnimation(target, this, id, ANIM_FIRE);
                    }

                    level.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.SLIME_BLOCK_BREAK, SoundSource.PLAYERS, 1.0F, 1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);

                    player.awardStat(Stats.ITEM_USED.get(this));
                }
            }
        }
    }
    public AcidicBombEntity acidicBomb(AcidicBombEntity bomb) {
        return bomb;
    }
    public PropulsionBombEntity propulsionBomb(PropulsionBombEntity bomb) {
        return bomb;
    }
    public RepulsionBombEntity repulsionBomb(RepulsionBombEntity bomb) {
        return bomb;
    }











    /** GeckoLib Animation Stuff **/
    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.NONE;
    }
    public <P extends Item & IAnimatable> PlayState predicate(AnimationEvent<P> event) {
        return PlayState.CONTINUE;
    }
    public AnimationFactory factory = new AnimationFactory(this);
    public String controllerName = "controller";
    public static final int ANIM_FIRE = 0;
    public static final int ANIM_CHARGE = 1;

    @Override
    public void initializeClient(Consumer<IItemRenderProperties> consumer) {
        super.initializeClient(consumer);
        ItemStack stack = new ItemStack(this);
        CompoundTag nbt = stack.getOrCreateTag();
        if (!nbt.getString("LoadedGel").isEmpty()) {
            if (nbt.getString("LoadedGel").equalsIgnoreCase("empty")) {
                consumer.accept(new IItemRenderProperties() {
                    private final BlockEntityWithoutLevelRenderer renderer = new GelBlobLauncherRenderer();

                    @Override
                    public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
                        return renderer;
                    }
                });
            }
            if (nbt.getString("LoadedGel").equalsIgnoreCase("propulsion")) {
                consumer.accept(new IItemRenderProperties() {
                    private final BlockEntityWithoutLevelRenderer renderer = new PropulsionGelBlobLauncherRenderer();

                    @Override
                    public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
                        return renderer;
                    }
                });
            }
            if (nbt.getString("LoadedGel").equalsIgnoreCase("acidic")) {
                consumer.accept(new IItemRenderProperties() {
                    private final BlockEntityWithoutLevelRenderer renderer = new AcidicGelBlobLauncherRenderer();

                    @Override
                    public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
                        return renderer;
                    }
                });
            }
            if (nbt.getString("LoadedGel").equalsIgnoreCase("repulsion")) {
                consumer.accept(new IItemRenderProperties() {
                    private final BlockEntityWithoutLevelRenderer renderer = new RepulsionGelBlobLauncherRenderer();

                    @Override
                    public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
                        return renderer;
                    }
                });
            }
        }
    }
    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, controllerName, 1, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public void onAnimationSync(int id, int state) {
        if (state == ANIM_FIRE) {
            final AnimationController<?> controller = GeckoLibUtil.getControllerForID(this.factory, id, controllerName);
            if (controller.getAnimationState() == AnimationState.Stopped) {
                controller.markNeedsReload();
                controller.setAnimation(new AnimationBuilder().addAnimation("animation.gel_blob_launcher.fire", false));
            }
        }
        if (state == ANIM_CHARGE) {
            final AnimationController<?> controller = GeckoLibUtil.getControllerForID(this.factory, id, controllerName);
            if (controller.getAnimationState() == AnimationState.Stopped) {
                controller.markNeedsReload();
                controller.setAnimation(new AnimationBuilder().addAnimation("animation.gel_blob_launcher.charge", true));
            }
        }
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        if (stack.getTag() != null) {
            String loadedGel = Lang.translateExisting("block.weird_science." + stack.getTag().getString("LoadedGel") + "_gel").getString();
            tooltip.add(Component.nullToEmpty(Lang.translateDirect("gel_launcher.tooltip.containsGel", loadedGel).getString()));
            tooltip.add(Component.nullToEmpty(Lang.translateDirect("gel_launcher.tooltip.loadsLeft", stack.getTag().getInt("LoadsLeft"), "/8").getString()));
        }
        if (stack.getTag() == null || stack.getTag().getString("LoadedGel").equals("empty")) {
            tooltip.add(Component.nullToEmpty(Lang.translateDirect("empty_gel_launcher").getString()));
        }
    }
}
