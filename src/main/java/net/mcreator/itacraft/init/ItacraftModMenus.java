/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.itacraft.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;

import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.core.registries.Registries;
import net.minecraft.client.Minecraft;

import net.mcreator.itacraft.world.inventory.VippsMiniBankGUIMenu;
import net.mcreator.itacraft.world.inventory.RadioActiveBarrelGUIMenu;
import net.mcreator.itacraft.world.inventory.PatchTableGUIMenu;
import net.mcreator.itacraft.world.inventory.OsloBorsMenu;
import net.mcreator.itacraft.world.inventory.NSMRequestInterfaceMenu;
import net.mcreator.itacraft.network.MenuStateUpdateMessage;
import net.mcreator.itacraft.ItacraftMod;

import java.util.Map;

public class ItacraftModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(Registries.MENU, ItacraftMod.MODID);
	public static final DeferredHolder<MenuType<?>, MenuType<OsloBorsMenu>> OSLO_BORS = REGISTRY.register("oslo_bors", () -> IMenuTypeExtension.create(OsloBorsMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<NSMRequestInterfaceMenu>> NSM_REQUEST_INTERFACE = REGISTRY.register("nsm_request_interface", () -> IMenuTypeExtension.create(NSMRequestInterfaceMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<PatchTableGUIMenu>> PATCH_TABLE_GUI = REGISTRY.register("patch_table_gui", () -> IMenuTypeExtension.create(PatchTableGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<VippsMiniBankGUIMenu>> VIPPS_MINI_BANK_GUI = REGISTRY.register("vipps_mini_bank_gui", () -> IMenuTypeExtension.create(VippsMiniBankGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<RadioActiveBarrelGUIMenu>> RADIO_ACTIVE_BARREL_GUI = REGISTRY.register("radio_active_barrel_gui", () -> IMenuTypeExtension.create(RadioActiveBarrelGUIMenu::new));

	public interface MenuAccessor {
		Map<String, Object> getMenuState();

		Map<Integer, Slot> getSlots();

		default void sendMenuStateUpdate(Player player, int elementType, String name, Object elementState, boolean needClientUpdate) {
			getMenuState().put(elementType + ":" + name, elementState);
			if (player instanceof ServerPlayer serverPlayer) {
				PacketDistributor.sendToPlayer(serverPlayer, new MenuStateUpdateMessage(elementType, name, elementState));
			} else if (player.level().isClientSide) {
				if (Minecraft.getInstance().screen instanceof ItacraftModScreens.ScreenAccessor accessor && needClientUpdate)
					accessor.updateMenuState(elementType, name, elementState);
				ClientPacketDistributor.sendToServer(new MenuStateUpdateMessage(elementType, name, elementState));
			}
		}

		default <T> T getMenuState(int elementType, String name, T defaultValue) {
			try {
				return (T) getMenuState().getOrDefault(elementType + ":" + name, defaultValue);
			} catch (ClassCastException e) {
				return defaultValue;
			}
		}
	}
}