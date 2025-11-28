/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.itacraft.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.core.registries.Registries;

import net.mcreator.itacraft.potion.RadiationresistanceMobEffect;
import net.mcreator.itacraft.potion.RadiationPoisoningMobEffect;
import net.mcreator.itacraft.potion.DeportablePotionEffectMobEffect;
import net.mcreator.itacraft.ItacraftMod;

public class ItacraftModMobEffects {
	public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(Registries.MOB_EFFECT, ItacraftMod.MODID);
	public static final DeferredHolder<MobEffect, MobEffect> DEPORTABLE_POTION_EFFECT = REGISTRY.register("deportable_potion_effect", () -> new DeportablePotionEffectMobEffect());
	public static final DeferredHolder<MobEffect, MobEffect> RADIATION_POISONING = REGISTRY.register("radiation_poisoning", () -> new RadiationPoisoningMobEffect());
	public static final DeferredHolder<MobEffect, MobEffect> RADIATIONRESISTANCE = REGISTRY.register("radiationresistance", () -> new RadiationresistanceMobEffect());
}