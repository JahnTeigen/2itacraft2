package net.mcreator.itacraft.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.SimpleParticleType;

import net.mcreator.itacraft.init.ItacraftModParticleTypes;

public class DeportablePotionEffectOnEffectActiveTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (world instanceof ServerLevel _level)
			_level.sendParticles((SimpleParticleType) (ItacraftModParticleTypes.RUSSIAPARTICLES.get()), x, (y + 1), z, 5, 1, 0, 0, 1);
		if (hasEntityInInventory(entity, new ItemStack(ItacraftModItems.PERSONOPPLYSNINGSBLANKETT.get()))) {
			if (entity instanceof Player _player) {
				ItemStack _stktoremove = new ItemStack(ItacraftModItems.PERSONOPPLYSNINGSBLANKETT.get());
				_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, _player.inventoryMenu.getCraftSlots());
			}
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("itacraft:russia_tts_1")), SoundSource.UI, 10, 2);
				} else {
					_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("itacraft:russia_tts_1")), SoundSource.UI, 10, 2, false);
				}
			}
		}
	}

	private static boolean hasEntityInInventory(Entity entity, ItemStack itemstack) {
		if (entity instanceof Player player)
			return player.getInventory().contains(stack -> !stack.isEmpty() && ItemStack.isSameItem(stack, itemstack));
		return false;
	}
}