package net.mcreator.itacraft.potion;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.server.level.ServerLevel;

import net.mcreator.itacraft.procedures.RadiationresistanceOnEffectActiveTickProcedure;

public class RadiationresistanceMobEffect extends MobEffect {
	public RadiationresistanceMobEffect() {
		super(MobEffectCategory.BENEFICIAL, -10066330);
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
		return true;
	}

	@Override
	public boolean applyEffectTick(ServerLevel level, LivingEntity entity, int amplifier) {
		RadiationresistanceOnEffectActiveTickProcedure.execute(entity);
		return super.applyEffectTick(level, entity, amplifier);
	}
}