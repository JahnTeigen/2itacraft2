package net.mcreator.itacraft.procedures;

import net.neoforged.bus.api.Event;

public class SolderingProgressValueProviderProcedure {
	public static double execute(LevelAccessor world, double x, double y, double z) {
		return (world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("solderingprogress") instanceof IntegerProperty _getip1 ? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getip1) : -1;
	}
}