package net.mcreator.itacraft.entity;

import net.minecraft.nbt.Tag;
import net.minecraft.network.syncher.EntityDataAccessor;

public class RetardedGamerGirlEntity extends Monster {

	public RetardedGamerGirlEntity(EntityType<RetardedGamerGirlEntity> type, Level world) {
		super(type, world);
		xpReward = 0;
		setNoAi(false);

	}

	@Override
	protected void registerGoals() {
		super.registerGoals();

		this.goalSelector.addGoal(1, new RandomStrollGoal(this, 1));
		this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
		this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(4, new FloatGoal(this));

	}

	@Override
	public Vec3 getPassengerRidingPosition(Entity entity) {
		return super.getPassengerRidingPosition(entity).add(0, -0.35F, 0);
	}

	@Override
	public SoundEvent getAmbientSound() {
		return BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("itacraft:retardliving"));
	}

	@Override
	public SoundEvent getHurtSound(DamageSource ds) {
		return BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("entity.generic.hurt"));
	}

	@Override
	public SoundEvent getDeathSound() {
		return BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("itacraft:retarddod"));
	}

	public static void init(RegisterSpawnPlacementsEvent event) {
		event.register(ItacraftModEntities.RETARDED_GAMER_GIRL.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				(entityType, world, reason, pos, random) -> (world.getBlockState(pos.below()).is(BlockTags.ANIMALS_SPAWNABLE_ON) && world.getRawBrightness(pos, 0) > 8), RegisterSpawnPlacementsEvent.Operation.REPLACE);
	}

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3);
		builder = builder.add(Attributes.MAX_HEALTH, 10);
		builder = builder.add(Attributes.ARMOR, 0);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 3);
		builder = builder.add(Attributes.FOLLOW_RANGE, 16);

		builder = builder.add(Attributes.STEP_HEIGHT, 0.6);

		return builder;
	}

}