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

import net.mcreator.itacraft.world.inventory.VippsMiniBankGUIMenu;
import net.mcreator.itacraft.network.VippsMiniBankGUIButtonMessage;
import net.mcreator.itacraft.init.ItacraftModScreens;

public class VippsMiniBankGUIScreen extends AbstractContainerScreen<VippsMiniBankGUIMenu> implements ItacraftModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private Button button_overfor;
	private Button button_hent_penger;

	public VippsMiniBankGUIScreen(VippsMiniBankGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 300;
		this.imageHeight = 200;
	}

	@Override
	public void updateMenuState(int elementType, String name, Object elementState) {
		menuStateUpdateActive = true;
		menuStateUpdateActive = false;
	}

	private static final ResourceLocation texture = ResourceLocation.parse("itacraft:textures/screens/vipps_mini_bank_gui.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, ResourceLocation.parse("itacraft:textures/screens/pil_opmvent.png"), this.leftPos + 141, this.topPos + 49, 0, 0, 32, 32, 32, 32);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, ResourceLocation.parse("itacraft:textures/screens/pil.png"), this.leftPos + 43, this.topPos + 51, 0, 0, 32, 32, 32, 32);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, ResourceLocation.parse("itacraft:textures/screens/rett_pil.png"), this.leftPos + 179, this.topPos + 21, 0, 0, 32, 32, 32, 32);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, ResourceLocation.parse("itacraft:textures/screens/burh_ahahah_vipps.png"), this.leftPos + 271, this.topPos + 170, 0, 0, 20, 20, 20, 20);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, ResourceLocation.parse("itacraft:textures/screens/burh_ahahah_vipps.png"), this.leftPos + 272, this.topPos + 5, 0, 0, 20, 20, 20, 20);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, ResourceLocation.parse("itacraft:textures/screens/burh_ahahah_vipps.png"), this.leftPos + 7, this.topPos + 7, 0, 0, 20, 20, 20, 20);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, ResourceLocation.parse("itacraft:textures/screens/burh_ahahah_vipps.png"), this.leftPos + 7, this.topPos + 172, 0, 0, 20, 20, 20, 20);
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
		guiGraphics.drawString(this.font, Component.translatable("gui.itacraft.vipps_mini_bank_gui.label_kontanter"), 30, 15, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.itacraft.vipps_mini_bank_gui.label_hawkphone"), 139, 13, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.itacraft.vipps_mini_bank_gui.label_vipps_minibank"), 117, 181, -42204, false);
	}

	@Override
	public void init() {
		super.init();
		button_overfor = Button.builder(Component.translatable("gui.itacraft.vipps_mini_bank_gui.button_overfor"), e -> {
			int x = VippsMiniBankGUIScreen.this.x;
			int y = VippsMiniBankGUIScreen.this.y;
			if (true) {
				ClientPacketDistributor.sendToServer(new VippsMiniBankGUIButtonMessage(0, x, y, z));
				VippsMiniBankGUIButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 77, this.topPos + 62, 61, 20).build();
		this.addRenderableWidget(button_overfor);
		button_hent_penger = Button.builder(Component.translatable("gui.itacraft.vipps_mini_bank_gui.button_hent_penger"), e -> {
			int x = VippsMiniBankGUIScreen.this.x;
			int y = VippsMiniBankGUIScreen.this.y;
			if (true) {
				ClientPacketDistributor.sendToServer(new VippsMiniBankGUIButtonMessage(1, x, y, z));
				VippsMiniBankGUIButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}).bounds(this.leftPos + 212, this.topPos + 28, 82, 20).build();
		this.addRenderableWidget(button_hent_penger);
	}
}