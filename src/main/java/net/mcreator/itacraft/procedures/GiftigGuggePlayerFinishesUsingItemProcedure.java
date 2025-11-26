package net.mcreator.itacraft.procedures;

import net.neoforged.bus.api.Event;

public class GiftigGuggePlayerFinishesUsingItemProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof ServerPlayer _player && _player.level() instanceof ServerLevel _level) {
			AdvancementHolder _adv = _level.getServer().getAdvancements().get(ResourceLocation.parse("itacraft:deleted_mod_element"));
			if (_adv != null) {
				AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
				if (!_ap.isDone()) {
					for (String criteria : _ap.getRemainingCriteria())
						_player.getAdvancements().award(_adv, criteria);
				}
			}
		}
		ItacraftMod.queueServerWork(60, () -> {
			entity.hurt(new DamageSource(world.holderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("itacraft:forgiftet")))), 100);
		});
	}
}