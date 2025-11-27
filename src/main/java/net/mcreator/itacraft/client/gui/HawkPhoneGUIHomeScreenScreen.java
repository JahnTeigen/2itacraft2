package net.mcreator.itacraft.client.gui;

import net.neoforged.neoforge.client.network.ClientPacketDistributor;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.itacraft.world.inventory.HawkPhoneGUIHomeScreenMenu;
import net.mcreator.itacraft.network.HawkPhoneGUIHomeScreenButtonMessage;
import net.mcreator.itacraft.init.ItacraftModScreens;

public class HawkPhoneGUIHomeScreenScreen extends AbstractContainerScreen<HawkPhoneGUIHomeScreenMenu> implements ItacraftModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private Button button_0;
	private Button button_vipps;
	private Button button_vy;

	public HawkPhoneGUIHomeScreenScreen(HawkPhoneGUIHomeScreenMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 150;
		this.imageHeight = 230;
	}

	@Override
	public void updateMenuState(int elementType, String name, Object elementState) {
		menuStateUpdateActive = true;
		menuStateUpdateActive = false;
	}

	private static final ResourceLocation texture = ResourceLocation.parse("itacraft:textures/screens/hawk_phone_gui_home_screen.png");

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
		guiGraphics.drawString(this.font, Component.translatable("gui.itacraft.hawk_phone_gui_home_screen.label_hawkphone"), 38, 8, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		button_0 = Button.builder(Component.translatable("gui.itacraft.hawk_phone_gui_home_screen.button_0"), e -> {
			int x = HawkPhoneGUIHomeScreenScreen.this.x;
			int y = HawkPhoneGUIHomeScreenScreen.this.y;
			if (true) {
				ClientPacketDistributor.sendToServer(new HawkPhoneGUIHomeScreenButtonMessage(0, x, y, z));
				HawkPhoneGUIHomeScreenButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 58, this.topPos + 205, 30, 20).build();
		this.addRenderableWidget(button_0);
		button_vipps = Button.builder(Component.translatable("gui.itacraft.hawk_phone_gui_home_screen.button_vipps"), e -> {
			int x = HawkPhoneGUIHomeScreenScreen.this.x;
			int y = HawkPhoneGUIHomeScreenScreen.this.y;
			if (true) {
				ClientPacketDistributor.sendToServer(new HawkPhoneGUIHomeScreenButtonMessage(1, x, y, z));
				HawkPhoneGUIHomeScreenButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}).bounds(this.leftPos + 17, this.topPos + 37, 51, 20).build();
		this.addRenderableWidget(button_vipps);
		button_vy = Button.builder(Component.translatable("gui.itacraft.hawk_phone_gui_home_screen.button_vy"), e -> {
		}).bounds(this.leftPos + 75, this.topPos + 37, 35, 20).build();
		this.addRenderableWidget(button_vy);
	}
}