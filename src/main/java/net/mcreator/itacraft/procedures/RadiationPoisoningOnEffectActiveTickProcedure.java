package net.mcreator.itacraft.procedures;

import net.neoforged.bus.api.Event;

public class RadiationPoisoningOnEffectActiveTickProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
			_entity.addEffect(new MobEffectInstance(MobEffects.POISON, 9999, 10, false, true));
	}
}