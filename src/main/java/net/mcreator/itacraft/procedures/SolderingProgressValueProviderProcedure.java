package net.mcreator.itacraft.procedures;

import net.minecraft.world.level.LevelAccessor;

import net.mcreator.itacraft.network.ItacraftModVariables;

public class SolderingProgressValueProviderProcedure {
	public static double execute(LevelAccessor world) {
		return ItacraftModVariables.MapVariables.get(world).solderingProgress;
	}
}