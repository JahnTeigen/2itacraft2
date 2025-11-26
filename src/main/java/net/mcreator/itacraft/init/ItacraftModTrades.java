/*
*	MCreator note: This file will be REGENERATED on each build.
*/
package net.mcreator.itacraft.init;

import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.common.BasicItemListing;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.npc.VillagerProfession;

@EventBusSubscriber
public class ItacraftModTrades {
	@SubscribeEvent
	public static void registerTrades(VillagerTradesEvent event) {
		if (event.getType() == VillagerProfession.LIBRARIAN) {
			event.getTrades().get(1).add(new BasicItemListing(new ItemStack(ItacraftModItems.KOG_STOCK.get()), new ItemStack(ItacraftModItems.FEM_HUNDRE_KRONER.get(), 2), 32, 9999, 0.03f));
		}
	}
}