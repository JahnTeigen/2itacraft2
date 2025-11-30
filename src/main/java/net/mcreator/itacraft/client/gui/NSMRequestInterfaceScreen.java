package net.mcreator.itacraft.client.gui;

public class NSMRequestInterfaceScreen extends AbstractContainerScreen<NSMRequestInterfaceMenu> implements ItacraftModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private Button button_klarer_meg;

	public NSMRequestInterfaceScreen(NSMRequestInterfaceMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 271;
		this.imageHeight = 173;
	}

	@Override
	public void updateMenuState(int elementType, String name, Object elementState) {
		menuStateUpdateActive = true;
		menuStateUpdateActive = false;
	}

	private static final ResourceLocation texture = ResourceLocation.parse("itacraft:textures/screens/nsm_request_interface.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, ResourceLocation.parse("itacraft:textures/screens/1000_downsized.png"), this.leftPos + 15, this.topPos + 54, 0, 0, 64, 32, 64, 32);
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
		guiGraphics.drawString(this.font, Component.translatable("gui.itacraft.nsm_request_interface.label_nasjonal_sikkerhetsmyndighet"), 62, 14, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.itacraft.nsm_request_interface.label_kostnad"), 15, 37, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.itacraft.nsm_request_interface.label_x100"), 81, 75, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		button_klarer_meg = Button.builder(Component.translatable("gui.itacraft.nsm_request_interface.button_klarer_meg"), e -> {
			int x = NSMRequestInterfaceScreen.this.x;
			int y = NSMRequestInterfaceScreen.this.y;
			if (true) {
				ClientPacketDistributor.sendToServer(new NSMRequestInterfaceButtonMessage(0, x, y, z));
				NSMRequestInterfaceButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 117, this.topPos + 47, 77, 20).build();
		this.addRenderableWidget(button_klarer_meg);
	}
}