package net.mcreator.itacraft.procedures;

import net.neoforged.bus.api.Event;

public class DeportablePotionEffectOnEffectActiveTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (world instanceof ServerLevel _level)
			_level.sendParticles((SimpleParticleType) (ItacraftModParticleTypes.RUSSIAPARTICLES.get()), x, (y + 1), z, 5, 1, 0, 0, 1);
	}
}