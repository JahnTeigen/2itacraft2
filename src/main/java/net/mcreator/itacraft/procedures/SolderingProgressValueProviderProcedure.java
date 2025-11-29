package net.mcreator.itacraft.procedures;

import net.neoforged.bus.api.Event;

public class SolderingProgressValueProviderProcedure {
	public static double execute(LevelAccessor world) {
		return ItacraftModVariables.MapVariables.get(world).solderingProgress;
	}
}