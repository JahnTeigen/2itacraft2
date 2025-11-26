package net.mcreator.itacraft.procedures;

import net.neoforged.bus.api.Event;

public class FireballWhileProjectileFlyingTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (world instanceof ServerLevel _level)
			_level.sendParticles(ParticleTypes.FLAME, x, y, z, 10, 1, 0, 0, 0);
		if (world instanceof ServerLevel _level)
			_level.sendParticles(ParticleTypes.LARGE_SMOKE, x, y, z, 10, 1, 0, 0, 0);
	}
}