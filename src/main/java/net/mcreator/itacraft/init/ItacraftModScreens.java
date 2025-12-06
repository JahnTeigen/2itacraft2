/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.itacraft.init;

import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.mcreator.itacraft.client.gui.*;

@EventBusSubscriber(Dist.CLIENT)
public class ItacraftModScreens {
	@SubscribeEvent
	public static void clientLoad(RegisterMenuScreensEvent event) {
		event.register(ItacraftModMenus.OSLO_BORS.get(), OsloBorsScreen::new);
		event.register(ItacraftModMenus.NSM_REQUEST_INTERFACE.get(), NSMRequestInterfaceScreen::new);
		event.register(ItacraftModMenus.PATCH_TABLE_GUI.get(), PatchTableGUIScreen::new);
		event.register(ItacraftModMenus.VIPPS_MINI_BANK_GUI.get(), VippsMiniBankGUIScreen::new);
		event.register(ItacraftModMenus.HAWK_PHONE_GUI_HOME_SCREEN.get(), HawkPhoneGUIHomeScreenScreen::new);
		event.register(ItacraftModMenus.VIPPS_APP_GUI.get(), VippsAppGuiScreen::new);
		event.register(ItacraftModMenus.RADIO_ACTIVE_BARREL_GUI.get(), RadioActiveBarrelGUIScreen::new);
		event.register(ItacraftModMenus.HAWK_OSGUI.get(), HawkOSGUIScreen::new);
		event.register(ItacraftModMenus.SKATTEETATEN_GUI.get(), SkatteetatenGuiScreen::new);
		event.register(ItacraftModMenus.SOLDERING_TABLE_GUI.get(), SolderingTableGUIScreen::new);
		event.register(ItacraftModMenus.AD_POPUP.get(), AdPopupScreen::new);
		event.register(ItacraftModMenus.SECURITAS_GUI.get(), SecuritasGUIScreen::new);
	}

	public interface ScreenAccessor {
		void updateMenuState(int elementType, String name, Object elementState);
	}
}