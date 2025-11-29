package net.mcreator.itacraft.procedures;

import net.neoforged.bus.api.Event;

public class VippsRanerPlayerCollidesWithThisEntityProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player _player) {
			ItemStack _stktoremove = new ItemStack(ItacraftModItems.HAWK_PHONE.get());
			_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, _player.inventoryMenu.getCraftSlots());
		}
	}
}