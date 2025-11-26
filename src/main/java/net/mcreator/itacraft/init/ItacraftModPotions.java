/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.itacraft.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.core.registries.Registries;

import net.mcreator.itacraft.ItacraftMod;

public class ItacraftModPotions {
	public static final DeferredRegister<Potion> REGISTRY = DeferredRegister.create(Registries.POTION, ItacraftMod.MODID);
	public static final DeferredHolder<Potion, Potion> RUSSIA_POTION = REGISTRY.register("russia_potion",
			() -> new Potion("russia_potion", new MobEffectInstance(ItacraftModMobEffects.DEPORTABLE, 3600, 1, false, true), new MobEffectInstance(MobEffects.GLOWING, 3600, 1, false, true)));
}