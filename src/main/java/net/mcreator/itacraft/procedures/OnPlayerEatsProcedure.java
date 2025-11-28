package net.mcreator.itacraft.procedures;

import net.neoforged.bus.api.Event;

@EventBusSubscriber
public class OnPlayerEatsProcedure {
	@SubscribeEvent
	public static void onUseItemFinish(LivingEntityUseItemEvent.Finish event) {
		if (event.getEntity() != null) {
			execute(event, event.getItem());
		}
	}

	public static void execute(ItemStack itemstack) {
		execute(null, itemstack);
	}

private static void execute(
@Nullable Event event,
ItemStack itemstack ) {
if (itemstack.has(DataComponents.FOOD)) {if (<17) {}}
}
}