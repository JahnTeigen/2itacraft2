package net.mcreator.itacraft.procedures;

import net.neoforged.bus.api.Event;

@EventBusSubscriber
public class OnPlayerEatsProcedure {
	@SubscribeEvent
	public static void onUseItemFinish(LivingEntityUseItemEvent.Finish event) {
		if (event.getEntity() != null) {
			execute(event, event.getEntity(), event.getItem());
		}
	}

	public static void execute(Entity entity, ItemStack itemstack) {
		execute(null, entity, itemstack);
	}

	private static void execute(@Nullable Event event, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		if (itemstack.has(DataComponents.FOOD)) {
			if (entity.getData(ItacraftModVariables.PLAYER_VARIABLES).poopAmount < 17) {
				{
					ItacraftModVariables.PlayerVariables _vars = entity.getData(ItacraftModVariables.PLAYER_VARIABLES);
					_vars.poopAmount = entity.getData(ItacraftModVariables.PLAYER_VARIABLES).poopAmount + 3;
					_vars.markSyncDirty();
				}
			}
		}
	}
}