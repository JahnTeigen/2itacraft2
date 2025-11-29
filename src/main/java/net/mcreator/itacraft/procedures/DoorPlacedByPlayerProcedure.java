package net.mcreator.itacraft.procedures;

import net.neoforged.bus.api.Event;

@EventBusSubscriber
public class DoorPlacedByPlayerProcedure {
	@SubscribeEvent
	public static void onBlockPlace(BlockEvent.EntityPlaceEvent event) {
		execute(event, event.getLevel(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ());
	}

	public static void execute(LevelAccessor world, double x, double y, double z) {
		execute(null, world, x, y, z);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z) {
		if ((BuiltInRegistries.BLOCK.getKey((world.getBlockState(BlockPos.containing(x, y, z))).getBlock()).toString()).equals(BuiltInRegistries.BLOCK.getKey(Blocks.OAK_DOOR).toString())) {
			ItacraftMod.queueServerWork((int) Mth.nextDouble(RandomSource.create(), 10, 10), () -> {
				if (world instanceof ServerLevel _level) {
					Entity entityToSpawn = ItacraftModEntities.DELETED_MOD_ELEMENT.get().spawn(_level, BlockPos.containing(x + 1, y, z), EntitySpawnReason.MOB_SUMMONED);
					if (entityToSpawn != null) {
					}
				}
			});
		}
	}
}