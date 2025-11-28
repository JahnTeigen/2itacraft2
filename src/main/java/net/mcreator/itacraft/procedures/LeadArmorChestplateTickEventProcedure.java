package net.mcreator.itacraft.procedures;

import net.neoforged.bus.api.Event;

public class LeadArmorChestplateTickEventProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		while (true) {
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(ItacraftModMobEffects.RADIATIONRESISTANCE, 60, 1));
		}
	}
}