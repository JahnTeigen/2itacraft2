package net.mcreator.itacraft.procedures;

import net.neoforged.bus.api.Event;

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