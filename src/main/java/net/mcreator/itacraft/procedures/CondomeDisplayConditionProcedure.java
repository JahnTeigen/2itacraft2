package net.mcreator.itacraft.procedures;

import net.neoforged.bus.api.Event;

public class CondomeDisplayConditionProcedure {
	public static boolean execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return false;
		if (hasEntityInInventory(entity, new ItemStack(ItacraftModItems.CONDOME.get()))) {
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
		return false;
	}

	private static boolean hasEntityInInventory(Entity entity, ItemStack itemstack) {
		if (entity instanceof Player player)
			return player.getInventory().contains(stack -> !stack.isEmpty() && ItemStack.isSameItem(stack, itemstack));
		return false;
	}
}