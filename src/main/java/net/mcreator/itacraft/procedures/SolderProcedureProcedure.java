package net.mcreator.itacraft.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;

import net.mcreator.itacraft.init.ItacraftModMenus;
import net.mcreator.itacraft.init.ItacraftModItems;
import net.mcreator.itacraft.ItacraftMod;

public class SolderProcedureProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (world instanceof ServerLevel _level) {
			_level.getServer().getPlayerList().broadcastSystemMessage(Component.literal(("Target entity w/ coordinates: " + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "solderProgress"))).withColor(0xff0000), false);
		}
		if (!world.isClientSide()) {
			BlockPos _bp = BlockPos.containing(x, y, z);
			BlockEntity _blockEntity = world.getBlockEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_blockEntity != null) {
				_blockEntity.getPersistentData().putDouble("solderProgress", 0);
			}
			if (world instanceof Level _level)
				_level.sendBlockUpdated(_bp, _bs, _bs, 3);
		}
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
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putDouble("solderProgress", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "solderProgress") + 1));
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				}
				if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "solderProgress") == 8) {
					if (entity instanceof Player _player && _player.containerMenu instanceof ItacraftModMenus.MenuAccessor _menu) {
						ItemStack _setstack11 = new ItemStack(ItacraftModItems.CIRCUIT_BOARD.get()).copy();
						_setstack11.setCount(1);
						_menu.getSlots().get(2).set(_setstack11);
						_menu.getSlots().get(1).remove(1);
						_player.containerMenu.broadcastChanges();
					}
				}
				ItacraftMod.queueServerWork(20, () -> {
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putDouble("solderProgress", 0);
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				});
			}
		}
	}

	private static double getBlockNBTNumber(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getDoubleOr(tag, 0);
		return -1;
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