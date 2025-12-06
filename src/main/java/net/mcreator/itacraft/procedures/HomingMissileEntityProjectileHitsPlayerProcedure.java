package net.mcreator.itacraft.procedures;

import net.neoforged.bus.api.Event;

public class HomingMissileEntityProjectileHitsPlayerProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (world instanceof Level _level && !_level.isClientSide())
			_level.explode(null, x, y, z, 1000, Level.ExplosionInteraction.TNT);
	}
}