package net.mcreator.itacraft.entity;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Level.ExplosionInteraction;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.util.RandomSource;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;

import net.mcreator.itacraft.procedures.HomingMissileEntityProjectileHitsPlayerProcedure;
import net.mcreator.itacraft.procedures.HomingMissileEntityProjectileHitsBlockProcedure;
import net.mcreator.itacraft.init.ItacraftModEntities;

import javax.annotation.Nullable;
import java.util.UUID;

public class HomingMissileEntityEntity extends AbstractArrow implements ItemSupplier {
	public static final ItemStack PROJECTILE_ITEM = new ItemStack(Items.ARROW);
	private int knockback = 0;
	private static final int MAX_HOMING_TICKS = 400; // 20 seconds (increased timeout)
	private static final double HOMING_SPEED = 2.0; // Much faster - was 0.5
	private static final double MAX_TARGET_DISTANCE = 100.0; // Max distance to track target
	private static final double EXPLOSION_RADIUS = 5.0; // Increased explosion radius
	
	// Custom sound events - replace these with your actual sound event names
	private static final ResourceLocation MISSILE_WARNING_SOUND = ResourceLocation.parse("itacraft:misslelockedon");
	private static final ResourceLocation MISSILE_EXPLOSION_SOUND = ResourceLocation.parse("itacraft:explosionmissle");
	private boolean hasPlayedWarningSound = false;

	public HomingMissileEntityEntity(EntityType<? extends HomingMissileEntityEntity> type, Level world) {
		super(type, world);
		setupHomingData();
	}

	public HomingMissileEntityEntity(EntityType<? extends HomingMissileEntityEntity> type, double x, double y, double z, Level world, @Nullable ItemStack firedFromWeapon) {
		super(type, x, y, z, world, PROJECTILE_ITEM, firedFromWeapon);
		if (firedFromWeapon != null)
			setKnockback(EnchantmentHelper.getItemEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.KNOCKBACK), firedFromWeapon));
		setupHomingData();
	}

	public HomingMissileEntityEntity(EntityType<? extends HomingMissileEntityEntity> type, LivingEntity entity, Level world, @Nullable ItemStack firedFromWeapon) {
		super(type, entity, world, PROJECTILE_ITEM, firedFromWeapon);
		if (firedFromWeapon != null)
			setKnockback(EnchantmentHelper.getItemEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.KNOCKBACK), firedFromWeapon));
		setupHomingData();
	}

	private void setupHomingData() {
		// Mark this as a homing missile
		CompoundTag data = this.getPersistentData();
		data.putBoolean("isHomingMissile", true);
		data.putDouble("speed", HOMING_SPEED);
		data.putInt("homingTicks", 0);
		data.putBoolean("hasFoundTarget", false);
	}

	@Override
	public ItemStack getItem() {
		return PROJECTILE_ITEM;
	}

	@Override
	protected ItemStack getDefaultPickupItem() {
		return new ItemStack(Items.ARROW);
	}

	@Override
	protected void doPostHurtEffects(LivingEntity entity) {
		super.doPostHurtEffects(entity);
		entity.setArrowCount(entity.getArrowCount() - 1);
	}

	public void setKnockback(int knockback) {
		this.knockback = knockback;
	}

	@Override
	protected void doKnockback(LivingEntity livingEntity, DamageSource damageSource) {
		if (knockback > 0.0) {
			double d1 = Math.max(0.0, 1.0 - livingEntity.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));
			Vec3 vec3 = this.getDeltaMovement().multiply(1.0, 0.0, 1.0).normalize().scale(knockback * 0.6 * d1);
			if (vec3.lengthSqr() > 0.0) {
				livingEntity.push(vec3.x, 0.1, vec3.z);
			}
		} else { // knockback might be set by firedFromWeapon passed into constructor
			super.doKnockback(livingEntity, damageSource);
		}
	}

	@Override
	public void playerTouch(Player entity) {
		super.playerTouch(entity);
		HomingMissileEntityProjectileHitsPlayerProcedure.execute(this.level(), this.getX(), this.getY(), this.getZ());
	}

	@Override
	public void onHitBlock(BlockHitResult blockHitResult) {
		super.onHitBlock(blockHitResult);
		HomingMissileEntityProjectileHitsBlockProcedure.execute(this.level(), blockHitResult.getBlockPos().getX(), blockHitResult.getBlockPos().getY(), blockHitResult.getBlockPos().getZ());
	}

	@Override
	public void tick() {
		super.tick();
		
		// Play warning sound when missile first spawns
		if (!this.level().isClientSide && !hasPlayedWarningSound) {
			playWarningSound();
			hasPlayedWarningSound = true;
		}
		
		// Update homing behavior every tick
		updateHomingBehavior();
		
		if (this.isInGround())
			this.discard();
	}

	private void playWarningSound() {
		if (this.level() instanceof ServerLevel serverLevel) {
			// Play warning sound to ALL players in the same dimension, regardless of distance
			Player targetPlayer = getTargetPlayer();
			
			// Always play the sound to the target player specifically (at their location)
			if (targetPlayer != null) {
				// Play directly to target player - they will always hear it
				serverLevel.playSound(null, targetPlayer.getX(), targetPlayer.getY(), targetPlayer.getZ(), 
					BuiltInRegistries.SOUND_EVENT.getValue(MISSILE_WARNING_SOUND), 
					SoundSource.HOSTILE, 5.0F, 1.0F); // Increased volume to 5.0
				
				// Also play to all other players in the dimension (global warning)
				for (Player player : serverLevel.players()) {
					if (player != targetPlayer) {
						// Other players hear it at their own location but slightly quieter
						serverLevel.playSound(null, player.getX(), player.getY(), player.getZ(), 
							BuiltInRegistries.SOUND_EVENT.getValue(MISSILE_WARNING_SOUND), 
							SoundSource.HOSTILE, 3.0F, 1.0F);
					}
				}
			} else {
				// If no specific target, play globally to all players
				for (Player player : serverLevel.players()) {
					serverLevel.playSound(null, player.getX(), player.getY(), player.getZ(), 
						BuiltInRegistries.SOUND_EVENT.getValue(MISSILE_WARNING_SOUND), 
						SoundSource.HOSTILE, 3.0F, 1.0F);
				}
			}
			
			// Also play the sound at the missile's location for ambient effect
			serverLevel.playSound(null, this.getX(), this.getY(), this.getZ(), 
				BuiltInRegistries.SOUND_EVENT.getValue(MISSILE_WARNING_SOUND), 
				SoundSource.HOSTILE, 2.0F, 0.8F); // Different pitch for variety
		}
	}

	private void updateHomingBehavior() {
		if (this.level().isClientSide) return;
		
		CompoundTag data = this.getPersistentData();
		
		// Get homing ticks from persistent data
		int homingTicks = data.getInt("homingTicks").orElse(0);
		homingTicks++;
		data.putInt("homingTicks", homingTicks);
		
		// Self-destruct after timeout
		if (homingTicks > MAX_HOMING_TICKS) {
			explode();
			return;
		}
		
		// Find target player
		Player targetPlayer = getTargetPlayer();
		
		// If we have a target and it's alive
		if (targetPlayer != null && targetPlayer.isAlive()) {
			double distanceToTarget = this.position().distanceTo(targetPlayer.position());
			
			// If target is too far away, don't explode - just keep chasing
			if (distanceToTarget > MAX_TARGET_DISTANCE) {
				// Continue moving in current direction but don't explode
				data.putBoolean("hasFoundTarget", true);
				return;
			}
			
			// Mark that we've found our target
			data.putBoolean("hasFoundTarget", true);
			
			// Update homing movement
			Vec3 missilePos = this.position();
			Vec3 targetPos = targetPlayer.position().add(0, targetPlayer.getEyeHeight() / 2, 0); // Aim for body center
			
			// Calculate direction to target
			Vec3 direction = targetPos.subtract(missilePos).normalize();
			
			// Apply movement towards target with increased speed
			this.setDeltaMovement(
				direction.x * HOMING_SPEED,
				direction.y * HOMING_SPEED,
				direction.z * HOMING_SPEED
			);
			
			// Update rotation to face target
			this.setYRot((float) Math.toDegrees(Math.atan2(direction.x, direction.z)));
			this.setXRot((float) Math.toDegrees(Math.asin(direction.y)));
			
			// Check if close to target (explosion radius)
			if (distanceToTarget < EXPLOSION_RADIUS) {
				// Hit target - explode
				explode();
			}
		} else {
			// No valid target found
			boolean hasFoundTarget = data.getBoolean("hasFoundTarget").orElse(false);
			
			// If we previously had a target but lost it, continue forward for a while
			if (hasFoundTarget && homingTicks < MAX_HOMING_TICKS - 100) {
				// Continue in current direction for a bit longer
				return;
			} else {
				// Never found target or been chasing too long without target - explode
				explode();
			}
		}
	}

	private Player getTargetPlayer() {
		CompoundTag data = this.getPersistentData();
		
		// Get target from NBT data
		if (data.contains("targetPlayer")) {
			try {
				// Use orElse to handle Optional
				String uuidString = data.getString("targetPlayer").orElse("");
				if (!uuidString.isEmpty()) {
					UUID targetPlayerUUID = UUID.fromString(uuidString);
					if (this.level() instanceof ServerLevel serverLevel) {
						Player player = serverLevel.getPlayerByUUID(targetPlayerUUID);
						// Only return player if they're in the same dimension and not too far
						if (player != null && player.isAlive() && player.level() == this.level()) {
							return player;
						}
					}
				}
			} catch (IllegalArgumentException e) {
				// Invalid UUID format
			}
		}
		
		return null;
	}

	public void setTargetPlayer(Player player) {
		// Store target in NBT for persistence
		CompoundTag data = this.getPersistentData();
		data.putString("targetPlayer", player.getStringUUID());
	}

	private void explode() {
		if (!this.level().isClientSide && this.level() instanceof ServerLevel serverLevel) {
			// Play custom explosion sound at explosion location (very loud)
			serverLevel.playSound(null, this.getX(), this.getY(), this.getZ(), 
				BuiltInRegistries.SOUND_EVENT.getValue(MISSILE_EXPLOSION_SOUND), 
				SoundSource.HOSTILE, 8.0F, 1.0F); // Increased to 8.0 for massive explosion
			
			// Also play explosion sound to all players globally so they definitely hear it
			for (Player player : serverLevel.players()) {
				if (player.distanceToSqr(this) > 1000) { // If player is far from explosion
					serverLevel.playSound(null, player.getX(), player.getY(), player.getZ(), 
						BuiltInRegistries.SOUND_EVENT.getValue(MISSILE_EXPLOSION_SOUND), 
						SoundSource.HOSTILE, 4.0F, 1.0F); // Quieter distant explosion
				}
			}
			
			// Create TNT-like explosion that breaks blocks
			// Use BLOCK instead of NONE to allow block destruction
			serverLevel.explode(this, this.getX(), this.getY(), this.getZ(), 6.0F, ExplosionInteraction.BLOCK);
			
			// Damage nearby entities in a larger radius
			double explosionRadius = 10.0;
			for (LivingEntity entity : this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(explosionRadius))) {
				double distance = this.position().distanceTo(entity.position());
				if (distance < explosionRadius) {
					// Scale damage based on distance (more damage closer to center)
					float damage = (float) (30.0 * (1.0 - (distance / explosionRadius)));
					entity.hurt(this.damageSources().explosion(this, null), damage);
				}
			}
		}
		this.discard();
	}

	public static HomingMissileEntityEntity shoot(Level world, LivingEntity entity, RandomSource source) {
		return shoot(world, entity, source, 3f, 10000, 5);
	}

	public static HomingMissileEntityEntity shoot(Level world, LivingEntity entity, RandomSource source, float pullingPower) {
		return shoot(world, entity, source, pullingPower * 3f, 10000, 5);
	}

	public static HomingMissileEntityEntity shoot(Level world, LivingEntity entity, RandomSource random, float power, double damage, int knockback) {
		HomingMissileEntityEntity entityarrow = new HomingMissileEntityEntity(ItacraftModEntities.HOMING_MISSILE_ENTITY.get(), entity, world, null);
		entityarrow.shoot(entity.getViewVector(1).x, entity.getViewVector(1).y, entity.getViewVector(1).z, power * 2, 0);
		entityarrow.setSilent(true);
		entityarrow.setCritArrow(true);
		entityarrow.setBaseDamage(damage);
		entityarrow.setKnockback(knockback);
		world.addFreshEntity(entityarrow);
		world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("entity.arrow.shoot")), SoundSource.PLAYERS, 1, 1f / (random.nextFloat() * 0.5f + 1) + (power / 2));
		return entityarrow;
	}

	public static HomingMissileEntityEntity shoot(LivingEntity entity, LivingEntity target) {
		HomingMissileEntityEntity entityarrow = new HomingMissileEntityEntity(ItacraftModEntities.HOMING_MISSILE_ENTITY.get(), entity, entity.level(), null);
		double dx = target.getX() - entity.getX();
		double dy = target.getY() + target.getEyeHeight() - 1.1;
		double dz = target.getZ() - entity.getZ();
		entityarrow.shoot(dx, dy - entityarrow.getY() + Math.hypot(dx, dz) * 0.2F, dz, 3f * 2, 12.0F);
		entityarrow.setSilent(true);
		entityarrow.setBaseDamage(10000);
		entityarrow.setKnockback(5);
		entityarrow.setCritArrow(true);
		if (target instanceof Player) {
			entityarrow.setTargetPlayer((Player) target);
		}
		entity.level().addFreshEntity(entityarrow);
		entity.level().playSound(null, entity.getX(), entity.getY(), entity.getZ(), BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("entity.arrow.shoot")), SoundSource.PLAYERS, 1, 1f / (RandomSource.create().nextFloat() * 0.5f + 1));
		return entityarrow;
	}

	// New method for spawning homing missile from procedure
	public static HomingMissileEntityEntity spawnHomingMissile(Level world, Player targetPlayer) {
		if (world.isClientSide()) return null;
		
		// Spawn missile 80 blocks away from player (as you wanted)
		double spawnX = targetPlayer.getX() + 80;
		double spawnY = targetPlayer.getY() + 80;
		double spawnZ = targetPlayer.getZ();
		
		HomingMissileEntityEntity missile = new HomingMissileEntityEntity(ItacraftModEntities.HOMING_MISSILE_ENTITY.get(), spawnX, spawnY, spawnZ, world, null);
		missile.setTargetPlayer(targetPlayer);
		missile.setSilent(true);
		missile.setBaseDamage(10000);
		missile.setKnockback(5);
		missile.setCritArrow(true);
		
		world.addFreshEntity(missile);
		return missile;
	}
}