package net.mcreator.itacraft.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;

import net.mcreator.itacraft.network.ItacraftModVariables;

public class CondomeDisplayConditionProcedure {
	public static boolean execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return false;
		if (entity.getData(ItacraftModVariables.PLAYER_VARIABLES).isCondomeEquipped) {
			if (world instanceof ServerLevel _level) {
				_level.getServer().getPlayerList().broadcastSystemMessage(Component.literal("Con dome is equipped").withStyle(ChatFormatting.UNDERLINE), false);
			}
			return true;
		}
		if (world instanceof ServerLevel _level) {
			_level.getServer().getPlayerList().broadcastSystemMessage(Component.literal("Con dome is not equipped"), false);
		}
		return false;
	}
}