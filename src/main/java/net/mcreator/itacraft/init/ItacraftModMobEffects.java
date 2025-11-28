/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.itacraft.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.core.registries.Registries;

import net.mcreator.itacraft.procedures.PregnantEffectExpiresProcedure;
import net.mcreator.itacraft.potion.RadiationresistanceMobEffect;
import net.mcreator.itacraft.potion.RadiationPoisoningMobEffect;
import net.mcreator.itacraft.potion.PregnantMobEffect;
import net.mcreator.itacraft.potion.DeportablePotionEffectMobEffect;
import net.mcreator.itacraft.ItacraftMod;

@EventBusSubscriber
public class ItacraftModMobEffects {
	public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(Registries.MOB_EFFECT, ItacraftMod.MODID);
	public static final DeferredHolder<MobEffect, MobEffect> DEPORTABLE_POTION_EFFECT = REGISTRY.register("deportable_potion_effect", () -> new DeportablePotionEffectMobEffect());
	public static final DeferredHolder<MobEffect, MobEffect> RADIATION_POISONING = REGISTRY.register("radiation_poisoning", () -> new RadiationPoisoningMobEffect());
	public static final DeferredHolder<MobEffect, MobEffect> RADIATIONRESISTANCE = REGISTRY.register("radiationresistance", () -> new RadiationresistanceMobEffect());
	public static final DeferredHolder<MobEffect, MobEffect> PREGNANT = REGISTRY.register("pregnant", () -> new PregnantMobEffect());

	@SubscribeEvent
	public static void onEffectRemoved(MobEffectEvent.Remove event) {
		MobEffectInstance effectInstance = event.getEffectInstance();
		if (effectInstance != null) {
			expireEffects(event.getEntity(), effectInstance);
		}
	}

	@SubscribeEvent
	public static void onEffectExpired(MobEffectEvent.Expired event) {
		MobEffectInstance effectInstance = event.getEffectInstance();
		if (effectInstance != null) {
			expireEffects(event.getEntity(), effectInstance);
		}
	}

	private static void expireEffects(Entity entity, MobEffectInstance effectInstance) {
		if (effectInstance.getEffect().is(PREGNANT)) {
			PregnantEffectExpiresProcedure.execute(entity.level(), entity.getX(), entity.getY(), entity.getZ(), entity);
		}
	}
}