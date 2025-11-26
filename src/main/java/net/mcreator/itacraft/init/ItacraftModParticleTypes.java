/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.itacraft.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.core.registries.Registries;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleType;

import net.mcreator.itacraft.ItacraftMod;

public class ItacraftModParticleTypes {
	public static final DeferredRegister<ParticleType<?>> REGISTRY = DeferredRegister.create(Registries.PARTICLE_TYPE, ItacraftMod.MODID);
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> RUSSIA_PARTICLE = REGISTRY.register("russia_particle", () -> new SimpleParticleType(true));
}