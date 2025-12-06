package net.mcreator.itacraft.client.screens;

@EventBusSubscriber(Dist.CLIENT)
public class TitleScreenOverlayOverlay {

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void eventHandler(ScreenEvent.Render.Post event) {
		if (event.getScreen() instanceof TitleScreen) {
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

				event.getGuiGraphics().blit(RenderPipelines.GUI_TEXTURED, ResourceLocation.parse("itacraft:textures/screens/ad_a.png"), -14, h / 2 + -96, 0, 0, 130, 200, 130, 200);

				event.getGuiGraphics().blit(RenderPipelines.GUI_TEXTURED, ResourceLocation.parse("itacraft:textures/screens/ad_b.png"), w / 2 + -94, h - 90, 0, 0, 200, 130, 200, 130);

				event.getGuiGraphics().blit(RenderPipelines.GUI_TEXTURED, ResourceLocation.parse("itacraft:textures/screens/ad_c.png"), w - 112, h / 2 + -96, 0, 0, 130, 200, 130, 200);

			}
		}
	}

}