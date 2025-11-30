package net.mcreator.itacraft.client.gui;

import net.neoforged.neoforge.client.network.ClientPacketDistributor;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.util.Mth;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.itacraft.world.inventory.SolderingTableGUIMenu;
import net.mcreator.itacraft.procedures.SolderingProgressValueProviderProcedure;
import net.mcreator.itacraft.network.SolderingTableGUIButtonMessage;
import net.mcreator.itacraft.init.ItacraftModScreens;

public class SolderingTableGUIScreen extends AbstractContainerScreen<SolderingTableGUIMenu> implements ItacraftModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private Button button_solder;

	public SolderingTableGUIScreen(SolderingTableGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 200;
		this.imageHeight = 200;
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
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, ResourceLocation.parse("itacraft:textures/screens/solderingtable_gui.png"), this.leftPos + 1, this.topPos + 0, 0, 0, 200, 200, 200, 200);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, ResourceLocation.parse("itacraft:textures/screens/mold_placeholder.png"), this.leftPos + 41, this.topPos + 43, 0, 0, 16, 16, 16, 16);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, ResourceLocation.parse("itacraft:textures/screens/ingot_placeholder.png"), this.leftPos + 92, this.topPos + 42, 0, 0, 16, 16, 16, 16);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, ResourceLocation.parse("itacraft:textures/screens/progress_circle_sprite.png"), this.leftPos + 92, this.topPos + 6,
				Mth.clamp((int) SolderingProgressValueProviderProcedure.execute(world, x, y, z) * 16, 0, 128), 0, 16, 16, 144, 16);
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
		button_solder = Button.builder(Component.translatable("gui.itacraft.soldering_table_gui.button_solder"), e -> {
			int x = SolderingTableGUIScreen.this.x;
			int y = SolderingTableGUIScreen.this.y;
			if (true) {
				ClientPacketDistributor.sendToServer(new SolderingTableGUIButtonMessage(0, x, y, z));
				SolderingTableGUIButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 14, this.topPos + 187, 177, 20).build();
		this.addRenderableWidget(button_solder);
	}
}