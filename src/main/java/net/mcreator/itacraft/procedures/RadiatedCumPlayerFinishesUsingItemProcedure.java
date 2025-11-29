package net.mcreator.itacraft.procedures;

import net.neoforged.bus.api.Event;

public class RadiatedCumPlayerFinishesUsingItemProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
			_entity.addEffect(new MobEffectInstance(ItacraftModMobEffects.RADIATION_POISONING, 1600, 1, false, false));
		if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
			_entity.addEffect(new MobEffectInstance(ItacraftModMobEffects.PREGNANT, 1200, 1, false, false));
	}
}