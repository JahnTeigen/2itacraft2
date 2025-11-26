/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.itacraft.init;

import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.mcreator.itacraft.client.model.ModelTonyHaynes;
import net.mcreator.itacraft.client.model.ModelJude;
import net.mcreator.itacraft.client.model.ModelGeirHaoy;
import net.mcreator.itacraft.client.model.ModelGamerGirl;
import net.mcreator.itacraft.client.model.ModelBodil;

@EventBusSubscriber(Dist.CLIENT)
public class ItacraftModModels {
	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(ModelJude.LAYER_LOCATION, ModelJude::createBodyLayer);
		event.registerLayerDefinition(ModelTonyHaynes.LAYER_LOCATION, ModelTonyHaynes::createBodyLayer);
		event.registerLayerDefinition(ModelBodil.LAYER_LOCATION, ModelBodil::createBodyLayer);
		event.registerLayerDefinition(ModelGeirHaoy.LAYER_LOCATION, ModelGeirHaoy::createBodyLayer);
		event.registerLayerDefinition(ModelGamerGirl.LAYER_LOCATION, ModelGamerGirl::createBodyLayer);
	}
}