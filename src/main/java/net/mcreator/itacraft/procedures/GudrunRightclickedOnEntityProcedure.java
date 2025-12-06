package net.mcreator.itacraft.procedures;

import net.neoforged.bus.api.Event;

public class GudrunRightclickedOnEntityProcedure {
	public static void execute(LevelAccessor world, Entity sourceentity) {
		if (sourceentity == null)
			return;
		if ((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == Items.BUCKET) {
			if (sourceentity instanceof Player _player) {
				ItemStack _stktoremove = new ItemStack(Items.BUCKET);
				_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, _player.inventoryMenu.getCraftSlots());
			}
			if (sourceentity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(ItacraftModItems.BODILS_SPERM_BUCKET.get()).copy();
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		} else {
			if (world instanceof ServerLevel _level) {
				_level.getServer().getPlayerList().broadcastSystemMessage(Component.literal("feil item"), false);
			}
		}
	}
}