package net.mcreator.itacraft.procedures;

import net.neoforged.bus.api.Event;

public class EquipCondomeShortcutOnKeyPressedProcedure {
public static void execute(
LevelAccessor world,
double x,
double y,
double z,
Entity entity ) {
if (
entity == null ) return ;
if (hasEntityInInventory(entity, new ItemStack(ItacraftModItems.CONDOME.get()))) {if () {}else{if (world instanceof Level _level) {
if (!_level.isClientSide()) {
_level.playSound(null, BlockPos.containing(x,y,z),
BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("itacraft:condome_application")),
SoundSource.NEUTRAL, 1, 1);
} else {
_level.playLocalSound(x, y, z,
BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("itacraft:condome_application")),
SoundSource.NEUTRAL, 1, 1, false);
}
}
if (world instanceof ServerLevel _level)
_level.sendParticles((SimpleParticleType) (ItacraftModParticleTypes.DELETED_MOD_ELEMENT.get()), x, y, z, 5, 2, 2, 2, 1);}}
}

	private static boolean hasEntityInInventory(Entity entity, ItemStack itemstack) {
		if (entity instanceof Player player)
			return player.getInventory().contains(stack -> !stack.isEmpty() && ItemStack.isSameItem(stack, itemstack));
		return false;
	}
}