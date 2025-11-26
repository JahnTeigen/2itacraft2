package net.mcreator.itacraft.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.SimpleParticleType;

import net.mcreator.itacraft.init.ItacraftModParticleTypes;

public class RussiaBlockOnBlockRightclickedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (world instanceof ServerLevel _level)
			_level.sendParticles((SimpleParticleType) (ItacraftModParticleTypes.RUSSIAPARTICLES.get()), x, y, z, 20, 1, 0, 0, 0.1);
	}
}