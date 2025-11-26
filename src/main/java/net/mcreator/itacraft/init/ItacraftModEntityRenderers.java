/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.itacraft.init;

import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.mcreator.itacraft.client.renderer.TonyHaynesRenderer;
import net.mcreator.itacraft.client.renderer.JudeRenderer;
import net.mcreator.itacraft.client.renderer.GudrunRenderer;
import net.mcreator.itacraft.client.renderer.GeirHaoyRenderer;
import net.mcreator.itacraft.client.renderer.GamerGirlRenderer;

@EventBusSubscriber(Dist.CLIENT)
public class ItacraftModEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(ItacraftModEntities.GEIR_HAOY.get(), GeirHaoyRenderer::new);
		event.registerEntityRenderer(ItacraftModEntities.JUDE.get(), JudeRenderer::new);
		event.registerEntityRenderer(ItacraftModEntities.BODIL.get(), GudrunRenderer::new);
		event.registerEntityRenderer(ItacraftModEntities.GAMER_GIRL.get(), GamerGirlRenderer::new);
		event.registerEntityRenderer(ItacraftModEntities.TONY_HAYNES.get(), TonyHaynesRenderer::new);
	}
}