package net.mcreator.itacraft.procedures;

import net.neoforged.bus.api.Event;

public class RadiationresistanceOnEffectActiveTickProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof LivingEntity _entity)
			_entity.removeEffect(ItacraftModMobEffects.RADIATION_POISONING);
		if (entity instanceof LivingEntity _entity)
			_entity.removeEffect(MobEffects.POISON);
		if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
			_entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 60, 1));
	}
}