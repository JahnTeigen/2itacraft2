package net.mcreator.itacraft.procedures;

import net.neoforged.bus.api.Event;

public class PoopShortcutOnKeyPressedProcedure {
public static void execute(
LevelAccessor world,
double x,
double y,
double z,
Entity entity ) {
if (
entity == null ) return ;
if (>0) {if (world instanceof ServerLevel _level) {
_level.getServer().getPlayerList().broadcastSystemMessage(Component.literal((entity.getDisplayName().getString()+"- Du m\u00E5 vente med \u00E5 drite."))
, false);
}}if (<=0) {entity.hurt(new DamageSource(world.holderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("itacraft:deleted_mod_element")))), 1);if (world instanceof ServerLevel _level) {
ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(ItacraftModItems.BESJ.get()));
entityToSpawn.setPickUpDelay(10);
_level.addFreshEntity(entityToSpawn);
}if (world instanceof Level _level) {
if (!_level.isClientSide()) {
_level.playSound(null, BlockPos.containing(x,y,z),
BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("itacraft:caca")),
SoundSource.NEUTRAL, 1, 1);
} else {
_level.playLocalSound(x, y, z,
BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("itacraft:caca")),
SoundSource.NEUTRAL, 1, 1, false);
}
}
if (<0) {ItacraftModVariables.MapVariables.get(world).aura = 0;
ItacraftModVariables.MapVariables.get(world).markSyncDirty();}}
}
}