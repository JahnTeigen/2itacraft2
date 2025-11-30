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
		if (event.getType() == VillagerProfession.ARMORER) {
			event.getTrades().get(4)
					.add(new BasicItemListing(new ItemStack(ItacraftModItems.ETT_TUSEN_KRONER.get(), 10), new ItemStack(ItacraftModItems.FEM_HUNDRE_KRONER.get(), 5), new ItemStack(ItacraftModBlocks.BLOCK_OF_BYGG_54.get()), 10, 5, 0.15f));
		}
	}
}