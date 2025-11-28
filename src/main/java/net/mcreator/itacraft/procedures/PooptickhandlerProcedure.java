package net.mcreator.itacraft.procedures;

import net.neoforged.bus.api.Event;

@EventBusSubscriber
public class PooptickhandlerProcedure {
	@SubscribeEvent
	public static void onPlayerTick(PlayerTickEvent.Post event) {
		execute(event, event.getEntity());
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		if (entity.getData(ItacraftModVariables.PLAYER_VARIABLES).poopAmount < 100) {
			{
				ItacraftModVariables.PlayerVariables _vars = entity.getData(ItacraftModVariables.PLAYER_VARIABLES);
				_vars.poopAmount = entity.getData(ItacraftModVariables.PLAYER_VARIABLES).poopAmount + 0.03;
				_vars.markSyncDirty();
			}
		}
		if (entity.getData(ItacraftModVariables.PLAYER_VARIABLES).poopCooldown > 0) {
			{
				ItacraftModVariables.PlayerVariables _vars = entity.getData(ItacraftModVariables.PLAYER_VARIABLES);
				_vars.poopCooldown = entity.getData(ItacraftModVariables.PLAYER_VARIABLES).poopCooldown - 1;
				_vars.markSyncDirty();
			}
		}
	}
}