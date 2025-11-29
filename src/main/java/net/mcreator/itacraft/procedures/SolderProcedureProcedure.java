package net.mcreator.itacraft.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;

import net.mcreator.itacraft.init.ItacraftModMenus;
import net.mcreator.itacraft.init.ItacraftModItems;
import net.mcreator.itacraft.ItacraftMod;

public class SolderProcedureProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		entity.getPersistentData().putDouble("solderProgress", 0);
		if (world instanceof ServerLevel _level) {
			_level.getServer().getPlayerList().broadcastSystemMessage(Component.literal("Running solder procedure"), false);
		}
		if (getAmountInGUISlot(entity, 0) == 1) {
			if (world instanceof ServerLevel _level) {
				_level.getServer().getPlayerList().broadcastSystemMessage(Component.literal("Slot 0 is 1"), false);
			}
			if (getAmountInGUISlot(entity, 1) == 1) {
				if (world instanceof ServerLevel _level) {
					_level.getServer().getPlayerList().broadcastSystemMessage(Component.literal("Slot 1 is 1"), false);
				}
				for (int index0 = 0; index0 < 8; index0++) {
					ItacraftMod.queueServerWork(80, () -> {
						entity.getPersistentData().putDouble("solderProgress", (entity.getPersistentData().getDoubleOr("tagName", 0) + 1));
					});
				}
				if (entity.getPersistentData().getDoubleOr("tagName", 0) == 8) {
					if (entity instanceof Player _player && _player.containerMenu instanceof ItacraftModMenus.MenuAccessor _menu) {
						ItemStack _setstack10 = new ItemStack(ItacraftModItems.CIRCUIT_BOARD.get()).copy();
						_setstack10.setCount(1);
						_menu.getSlots().get(2).set(_setstack10);
						_menu.getSlots().get(1).remove(1);
						_player.containerMenu.broadcastChanges();
					}
				}
				ItacraftMod.queueServerWork(20, () -> {
					entity.getPersistentData().putDouble("solderProgress", 0);
				});
			}
		}
	}

	private static int getAmountInGUISlot(Entity entity, int sltid) {
		if (entity instanceof Player player && player.containerMenu instanceof ItacraftModMenus.MenuAccessor menuAccessor) {
			ItemStack stack = menuAccessor.getSlots().get(sltid).getItem();
			if (stack != null)
				return stack.getCount();
		}
		return 0;
	}
}