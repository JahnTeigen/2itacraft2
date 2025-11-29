package net.mcreator.itacraft.potion;

public class PregnantMobEffect extends MobEffect {
	public PregnantMobEffect() {
		super(MobEffectCategory.HARMFUL, -1);
		this.withSoundOnAdded(BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("itacraft:fuckin")));
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
		return true;
	}

	@Override
	public boolean applyEffectTick(ServerLevel level, LivingEntity entity, int amplifier) {
		PregnantOnEffectActiveTickProcedure.execute(entity);
		return super.applyEffectTick(level, entity, amplifier);
	}
}