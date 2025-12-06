package net.mcreator.itacraft.entity;

import net.neoforged.neoforge.items.wrapper.EntityHandsInvWrapper;
import net.neoforged.neoforge.items.wrapper.EntityArmorInvWrapper;
import net.neoforged.neoforge.items.wrapper.CombinedInvWrapper;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.common.NeoForgeMod;

import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.projectile.AbstractThrownPotion;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.*;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.registries.BuiltInRegistries;

import net.mcreator.itacraft.world.inventory.SkatteetatenGuiMenu;
import net.mcreator.itacraft.procedures.SkattemanOnInitialEntitySpawnProcedure;
import net.mcreator.itacraft.procedures.SkatteetatenOnEntityTickProcedure;

import javax.annotation.Nullable;

import io.netty.buffer.Unpooled;

public class SkatteetatenEntity extends PathfinderMob {
	public SkatteetatenEntity(EntityType<SkatteetatenEntity> type, Level world) {
		super(type, world);
		xpReward = 0;
		setNoAi(false);
		setCustomName(Component.literal("Skatteetaten"));
		setCustomNameVisible(true);
		setPersistenceRequired();
	}

	@Override
	protected void registerGoals() {
	    super.registerGoals();
	    
	    // Follow nearest player
	    this.goalSelector.addGoal(1, new FollowPlayerGoal(this, 1.0, 10.0f, 2.0f));
	    
	    // Look at player
	    this.goalSelector.addGoal(2, new net.minecraft.world.entity.ai.goal.LookAtPlayerGoal(this, Player.class, 8.0f));
	    
	    // Random look around when not following
	    this.goalSelector.addGoal(3, new net.minecraft.world.entity.ai.goal.RandomLookAroundGoal(this));
	    
	    // Float in water
	    this.goalSelector.addGoal(4, new net.minecraft.world.entity.ai.goal.FloatGoal(this));
	}
	
	// Custom FollowPlayerGoal for Skatteetaten
	static class FollowPlayerGoal extends net.minecraft.world.entity.ai.goal.Goal {
	    private final PathfinderMob mob;
	    private Player targetPlayer;
	    private final double speedModifier;
	    private final float stopDistance;
	    private final float startDistance;
	    private int timeToRecalcPath;
	    
	    public FollowPlayerGoal(PathfinderMob mob, double speed, float stopDistance, float startDistance) {
	        this.mob = mob;
	        this.speedModifier = speed;
	        this.stopDistance = stopDistance;
	        this.startDistance = startDistance;
	        this.setFlags(java.util.EnumSet.of(net.minecraft.world.entity.ai.goal.Goal.Flag.MOVE, net.minecraft.world.entity.ai.goal.Goal.Flag.LOOK));
	    }
	    
	    @Override
	    public boolean canUse() {
	        // Find nearest player to follow
	        Player nearestPlayer = mob.level().getNearestPlayer(mob, 16);
	        if (nearestPlayer != null && nearestPlayer.isAlive() && !nearestPlayer.isSpectator()) {
	            this.targetPlayer = nearestPlayer;
	            return true;
	        }
	        return false;
	    }
	    
	    @Override
	    public boolean canContinueToUse() {
	        return targetPlayer != null && targetPlayer.isAlive() && 
	               !targetPlayer.isSpectator() && 
	               mob.distanceToSqr(targetPlayer) > (double)(stopDistance * stopDistance);
	    }
	    
	    @Override
	    public void start() {
	        this.timeToRecalcPath = 0;
	    }
	    
	    @Override
	    public void stop() {
	        this.targetPlayer = null;
	        mob.getNavigation().stop();
	    }
	    
	    @Override
	    public void tick() {
	        if (targetPlayer == null) return;
	        
	        mob.getLookControl().setLookAt(targetPlayer, 10.0f, (float)mob.getMaxHeadXRot());
	        
	        if (--timeToRecalcPath <= 0) {
	            timeToRecalcPath = 10;
	            
	            if (!mob.isLeashed() && !mob.isPassenger()) {
	                if (mob.distanceToSqr(targetPlayer) >= (double)(startDistance * startDistance)) {
	                    // Move toward player
	                    mob.getNavigation().moveTo(targetPlayer, speedModifier);
	                } else {
	                    // Stop if close enough
	                    mob.getNavigation().stop();
	                    
	                    // Still look at player
	                    mob.getLookControl().setLookAt(targetPlayer, 10.0f, (float)mob.getMaxHeadXRot());
	                }
	            }
	        }
	    }
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return false;
	}

	@Override
	public SoundEvent getHurtSound(DamageSource ds) {
		return BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("entity.generic.hurt"));
	}

	@Override
	public SoundEvent getDeathSound() {
		return BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("entity.generic.death"));
	}

	@Override
	public boolean hurtServer(ServerLevel level, DamageSource damagesource, float amount) {
		if (damagesource.is(DamageTypes.IN_FIRE))
			return false;
		if (damagesource.getDirectEntity() instanceof AbstractArrow)
			return false;
		if (damagesource.getDirectEntity() instanceof Player)
			return false;
		if (damagesource.getDirectEntity() instanceof AbstractThrownPotion || damagesource.getDirectEntity() instanceof AreaEffectCloud || damagesource.typeHolder().is(NeoForgeMod.POISON_DAMAGE))
			return false;
		if (damagesource.is(DamageTypes.FALL))
			return false;
		if (damagesource.is(DamageTypes.CACTUS))
			return false;
		if (damagesource.is(DamageTypes.DROWN))
			return false;
		if (damagesource.is(DamageTypes.LIGHTNING_BOLT))
			return false;
		if (damagesource.is(DamageTypes.EXPLOSION) || damagesource.is(DamageTypes.PLAYER_EXPLOSION))
			return false;
		if (damagesource.is(DamageTypes.TRIDENT))
			return false;
		if (damagesource.is(DamageTypes.FALLING_ANVIL))
			return false;
		if (damagesource.is(DamageTypes.DRAGON_BREATH))
			return false;
		if (damagesource.is(DamageTypes.WITHER) || damagesource.is(DamageTypes.WITHER_SKULL))
			return false;
		return super.hurtServer(level, damagesource, amount);
	}

	@Override
	public boolean ignoreExplosion(Explosion explosion) {
		return true;
	}

	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, EntitySpawnReason reason, @Nullable SpawnGroupData livingdata) {
		SpawnGroupData retval = super.finalizeSpawn(world, difficulty, reason, livingdata);
		SkattemanOnInitialEntitySpawnProcedure.execute(world, getX(), getY(), getZ(), this);
		return retval;
	}

	private final ItemStackHandler inventory = new ItemStackHandler(2);
	private final CombinedInvWrapper combined = new CombinedInvWrapper(inventory, new EntityHandsInvWrapper(this), new EntityArmorInvWrapper(this));

	public CombinedInvWrapper getCombinedInventory() {
		return combined;
	}

	@Override
	protected void dropEquipment(ServerLevel serverLevel) {
		super.dropEquipment(serverLevel);
		for (int i = 0; i < inventory.getSlots(); ++i) {
			ItemStack itemstack = inventory.getStackInSlot(i);
			if (!itemstack.isEmpty() && !EnchantmentHelper.has(itemstack, EnchantmentEffectComponents.PREVENT_EQUIPMENT_DROP)) {
				this.spawnAtLocation(serverLevel, itemstack);
			}
		}
	}

	@Override
	public void addAdditionalSaveData(ValueOutput valueOutput) {
		super.addAdditionalSaveData(valueOutput);
		inventory.serialize(valueOutput.child("InventoryCustom"));
	}

	@Override
	public void readAdditionalSaveData(ValueInput valueInput) {
		super.readAdditionalSaveData(valueInput);
		valueInput.child("InventoryCustom").ifPresent(input -> inventory.deserialize(input));
	}

	@Override
	public InteractionResult mobInteract(Player sourceentity, InteractionHand hand) {
		ItemStack itemstack = sourceentity.getItemInHand(hand);
		InteractionResult retval = InteractionResult.SUCCESS;
		if (sourceentity instanceof ServerPlayer serverPlayer) {
			serverPlayer.openMenu(new MenuProvider() {
				@Override
				public Component getDisplayName() {
					return Component.literal("Skatteetaten");
				}

				@Override
				public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
					FriendlyByteBuf packetBuffer = new FriendlyByteBuf(Unpooled.buffer());
					packetBuffer.writeBlockPos(sourceentity.blockPosition());
					packetBuffer.writeByte(0);
					packetBuffer.writeVarInt(SkatteetatenEntity.this.getId());
					return new SkatteetatenGuiMenu(id, inventory, packetBuffer);
				}
			}, buf -> {
				buf.writeBlockPos(sourceentity.blockPosition());
				buf.writeByte(0);
				buf.writeVarInt(this.getId());
			});
		}
		super.mobInteract(sourceentity, hand);
		return retval;
	}

	@Override
	public void baseTick() {
	    super.baseTick();
	    SkatteetatenOnEntityTickProcedure.execute(level(), this);
	}

	public static void init(RegisterSpawnPlacementsEvent event) {
	}

	public static AttributeSupplier.Builder createAttributes() {
	    AttributeSupplier.Builder builder = Mob.createMobAttributes();
	    builder = builder.add(Attributes.MOVEMENT_SPEED, 0.9); // Faster movement to follow players
	    builder = builder.add(Attributes.MAX_HEALTH, 10);
	    builder = builder.add(Attributes.ARMOR, 0);
	    builder = builder.add(Attributes.ATTACK_DAMAGE, 0);
	    builder = builder.add(Attributes.FOLLOW_RANGE, 32); // Increased follow range
	    builder = builder.add(Attributes.STEP_HEIGHT, 1.0); // Can step over blocks
	    return builder;
	}
}