package net.mcreator.itacraft.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;

import net.mcreator.itacraft.init.ItacraftModMenus;
import net.mcreator.itacraft.init.ItacraftModItems;

public class AssembleCableProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (getAmountInGUISlot(entity, 3) == 1) {
			if (entity instanceof Player _player && _player.containerMenu instanceof ItacraftModMenus.MenuAccessor _menu) {
				ItemStack _setstack1 = new ItemStack(ItacraftModItems.FOILED_RJ_45_TYPE_ETHERNET_CABLE.get()).copy();
				_setstack1.setCount(1);
				_menu.getSlots().get(4).set(_setstack1);
				_player.containerMenu.broadcastChanges();
			}
			if (world instanceof ServerLevel _level) {
				(entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof ItacraftModMenus.MenuAccessor _menu2 ? _menu2.getSlots().get(0).getItem() : ItemStack.EMPTY).hurtAndBreak(3, _level, null, _stkprov -> {
				});
			}
			entity.hurt(new DamageSource(world.holderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("itacraft:patch_cable_damage_type")))), 5);
		} else {
			if (entity instanceof Player _player && _player.containerMenu instanceof ItacraftModMenus.MenuAccessor _menu) {
				ItemStack _setstack6 = new ItemStack(ItacraftModItems.RJ_45_TYPE_ETHERNET_CABLE.get()).copy();
				_setstack6.setCount(1);
				_menu.getSlots().get(4).set(_setstack6);
				_player.containerMenu.broadcastChanges();
			}
			if (world instanceof ServerLevel _level) {
				(entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof ItacraftModMenus.MenuAccessor _menu7 ? _menu7.getSlots().get(0).getItem() : ItemStack.EMPTY).hurtAndBreak(3, _level, null, _stkprov -> {
				});
			}
			entity.hurt(new DamageSource(world.holderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("itacraft:patch_cable_damage_type")))), 5);
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