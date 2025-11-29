/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.itacraft.init;

import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.mcreator.itacraft.client.particle.RussiaparticlesParticle;
import net.mcreator.itacraft.client.particle.RadiationParticle;
import net.mcreator.itacraft.client.particle.BazingaParticleParticle;

@EventBusSubscriber(Dist.CLIENT)
public class ItacraftModParticles {
	@SubscribeEvent
	public static void registerParticles(RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(ItacraftModParticleTypes.RUSSIAPARTICLES.get(), RussiaparticlesParticle::provider);
		event.registerSpriteSet(ItacraftModParticleTypes.RADIATION.get(), RadiationParticle::provider);
		event.registerSpriteSet(ItacraftModParticleTypes.BAZINGA_PARTICLE.get(), BazingaParticleParticle::provider);
	}
}