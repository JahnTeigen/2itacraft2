package net.mcreator.itacraft.client.gui;

public class SkatteetatenGuiScreen extends AbstractContainerScreen<SkatteetatenGuiMenu> implements ItacraftModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private Button button_betal_skatt;

	public SkatteetatenGuiScreen(SkatteetatenGuiMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 250;
		this.imageHeight = 200;
	}

	@Override
	public void updateMenuState(int elementType, String name, Object elementState) {
		menuStateUpdateActive = true;
		menuStateUpdateActive = false;
	}

	private static final ResourceLocation texture = ResourceLocation.parse("itacraft:textures/screens/skatteetaten_gui.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
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
		guiGraphics.drawString(this.font, Component.translatable("gui.itacraft.skatteetaten_gui.label_jeg_tar_bare_vipps"), 10, 9, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.itacraft.skatteetaten_gui.label_regler"), 117, 10, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.itacraft.skatteetaten_gui.label_du_ma_ha_mer_en_1500_kr"), 117, 25, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.itacraft.skatteetaten_gui.label_jeg_tar_50"), 117, 38, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.itacraft.skatteetaten_gui.label_loper_du_vekk"), 117, 50, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.itacraft.skatteetaten_gui.label_tar_jeg_alt_du_eier"), 127, 62, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.itacraft.skatteetaten_gui.label_empty"), 230, 182, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		button_betal_skatt = Button.builder(Component.translatable("gui.itacraft.skatteetaten_gui.button_betal_skatt"), e -> {
			int x = SkatteetatenGuiScreen.this.x;
			int y = SkatteetatenGuiScreen.this.y;
			if (true) {
				ClientPacketDistributor.sendToServer(new SkatteetatenGuiButtonMessage(0, x, y, z));
				SkatteetatenGuiButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 14, this.topPos + 63, 82, 20).build();
		this.addRenderableWidget(button_betal_skatt);
	}
}