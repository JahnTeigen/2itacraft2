package net.mcreator.itacraft.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.SimpleParticleType;

import net.mcreator.itacraft.init.ItacraftModParticleTypes;
import net.mcreator.itacraft.init.ItacraftModMobEffects;

public class RussiaBlockOnBlockRightclickedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (world instanceof ServerLevel _level)
			_level.sendParticles((SimpleParticleType) (ItacraftModParticleTypes.RUSSIAPARTICLES.get()), x, y, z, 20, 1, 0, 0, 0.1);
		if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
			_entity.addEffect(new MobEffectInstance(ItacraftModMobEffects.DEPORTABLE_POTION_EFFECT, 5, 2));
	}
}