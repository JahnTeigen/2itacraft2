package net.mcreator.itacraft.client.screens;

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

			event.getGuiGraphics().blit(RenderPipelines.GUI_TEXTURED, ResourceLocation.parse("itacraft:textures/screens/nrk_minecraft.png"), w - 59, 5, 0, 0, 54, 32, 54, 32);

			event.getGuiGraphics().blit(RenderPipelines.GUI_TEXTURED, ResourceLocation.parse("itacraft:textures/screens/andreas_vanna_ut.png"), w / 2 + -245, h / 2 + -237, 0, 0, 517, 442, 517, 442);

		}
	}

}