package net.mcreator.itacraft.procedures;

import net.neoforged.bus.api.Event;

@EventBusSubscriber
public class OnDoorRightClickedProcedure {
	@SubscribeEvent
	public static void onBlockBreak(BlockEvent.BreakEvent event) {
		execute(event, event.getLevel(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), event.getPlayer());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if ((BuiltInRegistries.BLOCK.getKey((world.getBlockState(BlockPos.containing(x, y, z))).getBlock()).toString()).equals(BuiltInRegistries.BLOCK.getKey(Blocks.OAK_DOOR).toString())) {
			if (!entity.level().isClientSide())
				entity.discard();
		}
	}
}