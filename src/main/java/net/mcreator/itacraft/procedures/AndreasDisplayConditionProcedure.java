package net.mcreator.itacraft.procedures;

import net.neoforged.bus.api.Event;

public class AndreasDisplayConditionProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		if (hasEntityInInventory(entity, new ItemStack(ItacraftModBlocks.ANDREAS.get()))) {
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(ItacraftModMobEffects.RADIATION_POISONING, 60, 1));
			return true;
		}
		return false;
	}

	private static boolean hasEntityInInventory(Entity entity, ItemStack itemstack) {
		if (entity instanceof Player player)
			return player.getInventory().contains(stack -> !stack.isEmpty() && ItemStack.isSameItem(stack, itemstack));
		return false;
	}
}