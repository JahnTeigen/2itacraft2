package net.mcreator.itacraft.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.component.DataComponents;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.itacraft.world.inventory.VippsAppGuiMenu;
import net.mcreator.itacraft.init.ItacraftModScreens;

public class VippsAppGuiScreen extends AbstractContainerScreen<VippsAppGuiMenu> implements ItacraftModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;

	public VippsAppGuiScreen(VippsAppGuiMenu container, Inventory inventory, Component text) {
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

	private static final ResourceLocation texture = ResourceLocation.parse("itacraft:textures/screens/vipps_app_gui.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, ResourceLocation.parse("itacraft:textures/screens/burh_ahahah_vipps.png"), this.leftPos + 10, this.topPos + 8, 0, 0, 20, 20, 20, 20);
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
		guiGraphics.drawString(this.font, Component.translatable("gui.itacraft.vipps_app_gui.label_real_vipps"), 34, 14, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.itacraft.vipps_app_gui.label_kontonummer"), 11, 38, -12829636, false);
		
		// Get the phone from player's hand and display money
		ItemStack phoneItem = this.entity.getMainHandItem();
		double money = phoneItem.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY)
			.copyTag()
			.getDouble("money")
			.orElse(0.0);
		String moneyText = String.format("%.2f kr", money);
		guiGraphics.drawString(this.font, Component.literal(moneyText), 11, 50, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
	}
}