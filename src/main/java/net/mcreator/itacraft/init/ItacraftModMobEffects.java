/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.itacraft.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.core.registries.Registries;

import net.mcreator.itacraft.potion.DeportationIminentMobEffect;
import net.mcreator.itacraft.ItacraftMod;

public class ItacraftModMobEffects {
	public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(Registries.MOB_EFFECT, ItacraftMod.MODID);
	public static final DeferredHolder<MobEffect, MobEffect> DEPORTATION_IMINENT = REGISTRY.register("deportation_iminent", () -> new DeportationIminentMobEffect());
}