package net.mcreator.itacraft.procedures;

import net.neoforged.bus.api.Event;

public class PregnantOnEffectActiveTickProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
			_entity.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 1200, 1));
	}
}