/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.itacraft.init;

import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.mcreator.itacraft.client.particle.RussiaParticleParticle;

@EventBusSubscriber(Dist.CLIENT)
public class ItacraftModParticles {
	@SubscribeEvent
	public static void registerParticles(RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(ItacraftModParticleTypes.RUSSIA_PARTICLE.get(), RussiaParticleParticle::provider);
	}
}