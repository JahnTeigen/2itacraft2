package net.mcreator.itacraft.procedures;

import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.entity.Entity;

import net.mcreator.itacraft.network.ItacraftModVariables;

import javax.annotation.Nullable;

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
				_vars.poopAmount = entity.getData(ItacraftModVariables.PLAYER_VARIABLES).poopAmount + 0.003;
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