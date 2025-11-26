package net.mcreator.itacraft.procedures;

import net.neoforged.neoforge.items.IItemHandlerModifiable;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

import net.mcreator.itacraft.init.ItacraftModMenus;
import net.mcreator.itacraft.init.ItacraftModItems;

public class GetPOBProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double moneyCount = 0;
		moneyCount = 0;
		if (entity.getCapability(Capabilities.ItemHandler.ENTITY, null) instanceof IItemHandlerModifiable _modHandlerIter) {
			for (int _idx = 0; _idx < _modHandlerIter.getSlots(); _idx++) {
				ItemStack itemstackiterator = _modHandlerIter.getStackInSlot(_idx).copy();
				if (ItacraftModItems.ETT_TUSEN_KRONER.get() == itemstackiterator.getItem()) {
					moneyCount = moneyCount + itemstackiterator.getCount();
				}
			}
		}
		if (moneyCount > 99) {
			if (getAmountInGUISlot(entity, 0) == 0) {
				if (entity instanceof Player _player) {
					ItemStack _stktoremove = new ItemStack(ItacraftModItems.ETT_TUSEN_KRONER.get());
					_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 100, _player.inventoryMenu.getCraftSlots());
				}
				if (entity instanceof Player _player && _player.containerMenu instanceof ItacraftModMenus.MenuAccessor _menu) {
					ItemStack _setstack7 = new ItemStack(ItacraftModItems.PERSONOPPLYSNINGSBLANKETT.get()).copy();
					_setstack7.setCount(1);
					_menu.getSlots().get(0).set(_setstack7);
					_player.containerMenu.broadcastChanges();
				}
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("itacraft:kaching")), SoundSource.AMBIENT, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("itacraft:kaching")), SoundSource.AMBIENT, 1, 1, false);
					}
				}
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