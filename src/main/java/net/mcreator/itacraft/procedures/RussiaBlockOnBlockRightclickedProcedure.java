package net.mcreator.itacraft.procedures;

import net.neoforged.bus.api.Event;

public class RussiaBlockOnBlockRightclickedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (world instanceof ServerLevel _level)
			_level.sendParticles((SimpleParticleType) (ItacraftModParticleTypes.RUSSIAPARTICLES.get()), x, y, z, 20, 1, 0, 0, 0.1);
		if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
			_entity.addEffect(new MobEffectInstance(ItacraftModMobEffects.DEPORTABLE_POTION_EFFECT, 5, 2));
		if (world instanceof Level _level) {
			if (!_level.isClientSide()) {
				_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("itacraft:sukablyat")), SoundSource.AMBIENT, 2, 1);
			} else {
				_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("itacraft:sukablyat")), SoundSource.AMBIENT, 2, 1, false);
			}
		}
	}
}