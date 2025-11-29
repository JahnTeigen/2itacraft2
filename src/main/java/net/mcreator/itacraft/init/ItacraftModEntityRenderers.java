/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.itacraft.init;

import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.client.renderer.entity.ThrownItemRenderer;

import net.mcreator.itacraft.client.renderer.*;

@EventBusSubscriber(Dist.CLIENT)
public class ItacraftModEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(ItacraftModEntities.GEIR_HAOY.get(), GeirHaoyRenderer::new);
		event.registerEntityRenderer(ItacraftModEntities.JUDE.get(), JudeRenderer::new);
		event.registerEntityRenderer(ItacraftModEntities.BODIL.get(), GudrunRenderer::new);
		event.registerEntityRenderer(ItacraftModEntities.GAMER_GIRL.get(), GamerGirlRenderer::new);
		event.registerEntityRenderer(ItacraftModEntities.TONY_HAYNES.get(), TonyHaynesRenderer::new);
		event.registerEntityRenderer(ItacraftModEntities.FIREBALL.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(ItacraftModEntities.MONICA.get(), MonicaRenderer::new);
		event.registerEntityRenderer(ItacraftModEntities.VIPPS_RANER.get(), VippsRanerRenderer::new);
		event.registerEntityRenderer(ItacraftModEntities.SKATTEETATEN.get(), SkatteetatenRenderer::new);
	}
}