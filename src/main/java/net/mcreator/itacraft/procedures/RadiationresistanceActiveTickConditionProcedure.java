package net.mcreator.itacraft.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.itacraft.init.ItacraftModMobEffects;

public class RadiationresistanceActiveTickConditionProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof LivingEntity _entity)
			_entity.removeEffect(ItacraftModMobEffects.RADIATION_POISONING);
	}
}