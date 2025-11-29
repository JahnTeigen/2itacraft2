package net.mcreator.itacraft.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

import net.mcreator.itacraft.network.ItacraftModVariables;
import net.mcreator.itacraft.init.ItacraftModMobEffects;
import net.mcreator.itacraft.init.ItacraftModItems;

public class AbortionProcedureProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof LivingEntity _livEnt0 && _livEnt0.hasEffect(ItacraftModMobEffects.PREGNANT)) {
			{
				ItacraftModVariables.PlayerVariables _vars = entity.getData(ItacraftModVariables.PLAYER_VARIABLES);
				_vars.abort_state = 1;
				_vars.markSyncDirty();
			}
			if (entity instanceof LivingEntity _entity)
				_entity.removeAllEffects();
			if (world instanceof ServerLevel _level) {
				ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(ItacraftModItems.FETUS.get()));
				entityToSpawn.setPickUpDelay(10);
				_level.addFreshEntity(entityToSpawn);
			}
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("itacraft:cum1")), SoundSource.PLAYERS, 1, 1);
				} else {
					_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(ResourceLocation.parse("itacraft:cum1")), SoundSource.PLAYERS, 1, 1, false);
				}
			}
			entity.hurt(new DamageSource(world.holderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("itacraft:prolaps")))), 4);
		}
		{
			ItacraftModVariables.PlayerVariables _vars = entity.getData(ItacraftModVariables.PLAYER_VARIABLES);
			_vars.abort_state = 0;
			_vars.markSyncDirty();
		}
	}
}