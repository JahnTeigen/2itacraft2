package net.mcreator.itacraft.client.gui;

public class PatchTableGUIScreen extends AbstractContainerScreen<PatchTableGUIMenu> implements ItacraftModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private Button button_assemble;

	public PatchTableGUIScreen(PatchTableGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 300;
		this.imageHeight = 218;
	}

	@Override
	public void updateMenuState(int elementType, String name, Object elementState) {
		menuStateUpdateActive = true;
		menuStateUpdateActive = false;
	}

	private static final ResourceLocation texture = ResourceLocation.parse("itacraft:textures/screens/patch_table_gui.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, ResourceLocation.parse("itacraft:textures/screens/modularplier.png"), this.leftPos + 209, this.topPos + 142, 0, 0, 64, 64, 64, 64);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, ResourceLocation.parse("itacraft:textures/screens/rj45_type_cable.png"), this.leftPos + 206, this.topPos + 45, 0, 0, 64, 64, 64, 64);
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
		guiGraphics.drawString(this.font, Component.translatable("gui.itacraft.patch_table_gui.label_cable"), 17, 15, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.itacraft.patch_table_gui.label_plug"), 61, 16, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.itacraft.patch_table_gui.label_foil"), 101, 17, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		button_assemble = Button.builder(Component.translatable("gui.itacraft.patch_table_gui.button_assemble"), e -> {
			int x = PatchTableGUIScreen.this.x;
			int y = PatchTableGUIScreen.this.y;
			if (true) {
				ClientPacketDistributor.sendToServer(new PatchTableGUIButtonMessage(0, x, y, z));
				PatchTableGUIButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 72, this.topPos + 71, 67, 20).build();
		this.addRenderableWidget(button_assemble);
	}
}