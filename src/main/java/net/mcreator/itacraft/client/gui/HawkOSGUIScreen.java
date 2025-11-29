package net.mcreator.itacraft.client.gui;

public class HawkOSGUIScreen extends AbstractContainerScreen<HawkOSGUIMenu> implements ItacraftModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private Button button_onoff;

	public HawkOSGUIScreen(HawkOSGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 304;
		this.imageHeight = 181;
	}

	@Override
	public void updateMenuState(int elementType, String name, Object elementState) {
		menuStateUpdateActive = true;
		menuStateUpdateActive = false;
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, ResourceLocation.parse("itacraft:textures/screens/hawkos_gui_background.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 304, 181, 304, 181);
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
	}

	@Override
	public void init() {
		super.init();
		button_onoff = new PlainTextButton(this.leftPos + 4, this.topPos + 163, 56, 20, Component.translatable("gui.itacraft.hawk_osgui.button_onoff"), e -> {
		}, this.font);
		this.addRenderableWidget(button_onoff);
	}
}