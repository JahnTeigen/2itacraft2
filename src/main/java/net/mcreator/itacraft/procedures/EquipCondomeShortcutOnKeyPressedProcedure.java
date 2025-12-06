package net.mcreator.itacraft.procedures;

import net.neoforged.neoforge.items.ItemHandlerHelper;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.BlockPos;

import net.mcreator.itacraft.network.ItacraftModVariables;
import net.mcreator.itacraft.init.ItacraftModParticleTypes;
import net.mcreator.itacraft.init.ItacraftModItems;

public class EquipCondomeShortcutOnKeyPressedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (hasEntityInInventory(entity, new ItemStack(ItacraftModItems.CONDOME.get()))) {
			if (entity.getData(ItacraftModVariables.PLAYER_VARIABLES).isCondomeEquipped) {
				{
					ItacraftModVariables.PlayerVariables _vars = entity.getData(ItacraftModVariables.PLAYER_VARIABLES);
					_vars.isCondomeEquipped = false;
					_vars.markSyncDirty();
				}
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("itacraft:condome_unequip")), SoundSource.NEUTRAL, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("itacraft:condome_unequip")), SoundSource.NEUTRAL, 1, 1, false);
					}
				}
				if (entity instanceof Player _player) {
					ItemStack _stktoremove = new ItemStack(ItacraftModItems.CONDOME.get());
					_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, _player.inventoryMenu.getCraftSlots());
				}
				if (entity instanceof Player _player) {
					ItemStack _setstack = new ItemStack(ItacraftModItems.USED_CONDOME.get()).copy();
					_setstack.setCount(1);
					ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
				}
			} else {
				{
					ItacraftModVariables.PlayerVariables _vars = entity.getData(ItacraftModVariables.PLAYER_VARIABLES);
					_vars.isCondomeEquipped = true;
					_vars.markSyncDirty();
				}
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("itacraft:condome_application")), SoundSource.NEUTRAL, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("itacraft:condome_application")), SoundSource.NEUTRAL, 1, 1, false);
					}
				}
				if (world instanceof ServerLevel _level)
					_level.sendParticles((SimpleParticleType) (ItacraftModParticleTypes.BAZINGA_PARTICLE.get()), x, y, z, 5, 2, 2, 2, 1);
			}
		}
	}

	private static boolean hasEntityInInventory(Entity entity, ItemStack itemstack) {
		if (entity instanceof Player player)
			return player.getInventory().contains(stack -> !stack.isEmpty() && ItemStack.isSameItem(stack, itemstack));
		return false;
	}
}