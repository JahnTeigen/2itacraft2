/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.itacraft.init;

import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.mcreator.itacraft.client.gui.PatchTableGUIScreen;
import net.mcreator.itacraft.client.gui.OsloBorsScreen;
import net.mcreator.itacraft.client.gui.NSMRequestInterfaceScreen;

@EventBusSubscriber(Dist.CLIENT)
public class ItacraftModScreens {
	@SubscribeEvent
	public static void clientLoad(RegisterMenuScreensEvent event) {
		event.register(ItacraftModMenus.OSLO_BORS.get(), OsloBorsScreen::new);
		event.register(ItacraftModMenus.NSM_REQUEST_INTERFACE.get(), NSMRequestInterfaceScreen::new);
		event.register(ItacraftModMenus.PATCH_TABLE_GUI.get(), PatchTableGUIScreen::new);
	}

	public interface ScreenAccessor {
		void updateMenuState(int elementType, String name, Object elementState);
	}
}