package net.mcreator.itacraft.client.screens;

import org.checkerframework.checker.units.qual.h;

import net.neoforged.neoforge.client.event.RenderGuiEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.Minecraft;

import net.mcreator.itacraft.procedures.CondomeDisplayConditionProcedure;
import net.mcreator.itacraft.procedures.AndreasDisplayConditionProcedure;

@EventBusSubscriber(Dist.CLIENT)
public class CornerOverlayOverlay {
	@SubscribeEvent(priority = EventPriority.HIGH)
	public static void eventHandler(RenderGuiEvent.Pre event) {
		int w = event.getGuiGraphics().guiWidth();
		int h = event.getGuiGraphics().guiHeight();
		Level world = null;
		double x = 0;
		double y = 0;
		double z = 0;
		Player entity = Minecraft.getInstance().player;
		if (entity != null) {
			world = entity.level();
			x = entity.getX();
			y = entity.getY();
			z = entity.getZ();
		}
		if (true) {
			if (CondomeDisplayConditionProcedure.execute(world, entity)) {
				event.getGuiGraphics().blit(RenderPipelines.GUI_TEXTURED, ResourceLocation.parse("itacraft:textures/screens/condome.png"), 6, 6, 0, 0, 16, 16, 16, 16);
			}
			event.getGuiGraphics().blit(RenderPipelines.GUI_TEXTURED, ResourceLocation.parse("itacraft:textures/screens/nrk_minecraft.png"), w - 59, 5, 0, 0, 54, 32, 54, 32);

			if (AndreasDisplayConditionProcedure.execute(entity)) {
				event.getGuiGraphics().blit(RenderPipelines.GUI_TEXTURED, ResourceLocation.parse("itacraft:textures/screens/andreas_vanna_ut.png"), w / 2 + -245, h / 2 + -237, 0, 0, 517, 442, 517, 442);
			}
			if (CondomeDisplayConditionProcedure.execute(world, entity))
				event.getGuiGraphics().drawString(Minecraft.getInstance().font, Component.translatable("gui.itacraft.corner_overlay.label_condome_equipped"), 26, 10, -10092340, false);
		}
	}
}