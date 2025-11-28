package net.mcreator.itacraft.procedures;

import net.neoforged.bus.api.Event;

@EventBusSubscriber
public class PooptickhandlerProcedure {
	@SubscribeEvent
	public static void onPlayerTick(PlayerTickEvent.Post event) {
		execute(event);
	}

	public static void execute() {
		execute(null);
	}

private static void execute(
@Nullable Event event
) {
if (<100) {}if (>0) {}
}
}