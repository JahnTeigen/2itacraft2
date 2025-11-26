package net.mcreator.itacraft.potion;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.server.level.ServerLevel;

import net.mcreator.itacraft.procedures.RadiationPoisoningOnEffectActiveTickProcedure;

public class RadiationPoisoningMobEffect extends MobEffect {
	public RadiationPoisoningMobEffect() {
		super(MobEffectCategory.HARMFUL, -6711040);
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
		return true;
	}

	@Override
	public boolean applyEffectTick(ServerLevel level, LivingEntity entity, int amplifier) {
		RadiationPoisoningOnEffectActiveTickProcedure.execute(entity);
		return super.applyEffectTick(level, entity, amplifier);
	}
}