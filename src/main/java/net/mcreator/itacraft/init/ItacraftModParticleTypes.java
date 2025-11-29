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
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> RUSSIAPARTICLES = REGISTRY.register("russiaparticles", () -> new SimpleParticleType(true));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> RADIATION = REGISTRY.register("radiation", () -> new SimpleParticleType(true));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> BAZINGA_PARTICLE = REGISTRY.register("bazinga_particle", () -> new SimpleParticleType(false));
}