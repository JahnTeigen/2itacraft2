package net.mcreator.itacraft.potion;

public class RadiationPoisoningMobEffect extends MobEffect {
	public RadiationPoisoningMobEffect() {
		super(MobEffectCategory.HARMFUL, -6711040);
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
		return true;
	}

	@Override
	public boolean applyEffectTick(ServerLevel level, LivingEntity entity, int amplifier) {
		RadiationPoisoningOnEffectActiveTickProcedure.execute(entity);
		return super.applyEffectTick(level, entity, amplifier);
	}
}