package net.mcreator.itacraft.procedures;

import net.neoforged.bus.api.Event;

public class RadiationresistanceActiveTickConditionProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof LivingEntity _entity)
			_entity.removeEffect(ItacraftModMobEffects.RADIATION_POISONING);
	}
}