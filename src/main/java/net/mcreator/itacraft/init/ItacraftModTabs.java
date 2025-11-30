/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.itacraft.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;

import net.mcreator.itacraft.ItacraftMod;

@EventBusSubscriber
public class ItacraftModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ItacraftMod.MODID);
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> STOCKS = REGISTRY.register("stocks",
			() -> CreativeModeTab.builder().title(Component.translatable("item_group.itacraft.stocks")).icon(() -> new ItemStack(ItacraftModItems.KOG_STOCK.get())).displayItems((parameters, tabData) -> {
				tabData.accept(ItacraftModItems.KOG_STOCK.get());
			}).withSearchBar().build());
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ELECTRONICS = REGISTRY.register("electronics",
			() -> CreativeModeTab.builder().title(Component.translatable("item_group.itacraft.electronics")).icon(() -> new ItemStack(ItacraftModItems.CIRCUIT_BOARD.get())).displayItems((parameters, tabData) -> {
				tabData.accept(ItacraftModBlocks.HP_WORKSTATION.get().asItem());
				tabData.accept(ItacraftModBlocks.HP_MONITOR.get().asItem());
				tabData.accept(ItacraftModItems.CIRCUIT_BOARD.get());
				tabData.accept(ItacraftModBlocks.SECURITAS_KEYPAD.get().asItem());
			}).withSearchBar().withTabsBefore(STOCKS.getId()).build());

	@SubscribeEvent
	public static void buildTabContentsVanilla(BuildCreativeModeTabContentsEvent tabData) {
		if (tabData.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
			tabData.accept(ItacraftModItems.GEIR_HAOY_SPAWN_EGG.get());
			tabData.accept(ItacraftModItems.JUDE_SPAWN_EGG.get());
			tabData.accept(ItacraftModItems.BODIL_SPAWN_EGG.get());
			tabData.accept(ItacraftModItems.GAMER_GIRL_SPAWN_EGG.get());
			tabData.accept(ItacraftModItems.TONY_HAYNES_SPAWN_EGG.get());
			tabData.accept(ItacraftModItems.MONICA_SPAWN_EGG.get());
			tabData.accept(ItacraftModItems.VIPPS_RANER_SPAWN_EGG.get());
			tabData.accept(ItacraftModItems.NAL_MED_CUM.get());
			tabData.accept(ItacraftModItems.RETARDED_GAMER_GIRL_SPAWN_EGG.get());
			tabData.accept(ItacraftModItems.SKATTEETATEN_SPAWN_EGG.get());
		} else if (tabData.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
			tabData.accept(ItacraftModBlocks.ANDREAS.get().asItem());
			tabData.accept(ItacraftModItems.FEMTI_KRONER.get());
			tabData.accept(ItacraftModItems.FEM_HUNDRE_KRONER.get());
			tabData.accept(ItacraftModItems.ETT_TUSEN_KRONER.get());
			tabData.accept(ItacraftModItems.PERSONOPPLYSNINGSBLANKETT.get());
			tabData.accept(ItacraftModItems.WIRE.get());
			tabData.accept(ItacraftModItems.PATCH_CABLE.get());
			tabData.accept(ItacraftModItems.RJ_45_TYPE_PLUG.get());
			tabData.accept(ItacraftModItems.MODULAR_PLIERS.get());
			tabData.accept(ItacraftModItems.RJ_45_TYPE_ETHERNET_CABLE.get());
			tabData.accept(ItacraftModItems.FOILED_RJ_45_TYPE_ETHERNET_CABLE.get());
			tabData.accept(ItacraftModItems.EN_KRONE.get());
			tabData.accept(ItacraftModItems.FEM_KRONER.get());
			tabData.accept(ItacraftModItems.TI_KRONER.get());
			tabData.accept(ItacraftModItems.TJUE_KRONER.get());
			tabData.accept(ItacraftModItems.TO_HUNDRE_KRONER.get());
			tabData.accept(ItacraftModItems.HUNDRE_KRONER.get());
			tabData.accept(ItacraftModItems.ETT_ORE.get());
			tabData.accept(ItacraftModItems.TI_ORE.get());
			tabData.accept(ItacraftModItems.HAWK_PHONE.get());
			tabData.accept(ItacraftModItems.COMMUNISM.get());
			tabData.accept(ItacraftModItems.TOM_NAL.get());
			tabData.accept(ItacraftModItems.DILDO.get());
		} else if (tabData.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
			tabData.accept(ItacraftModItems.HEROIN.get());
			tabData.accept(ItacraftModItems.NAL_MED_NOE_RART_I.get());
			tabData.accept(ItacraftModItems.MICROPLASTIC.get());
		} else if (tabData.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
			tabData.accept(ItacraftModBlocks.BLOCK_OF_BYGG_54.get().asItem());
			tabData.accept(ItacraftModBlocks.EKTE_GUMMITRE_DOOR.get().asItem());
			tabData.accept(ItacraftModBlocks.EKTE_GUMMITRE_WOOD.get().asItem());
			tabData.accept(ItacraftModBlocks.EKTE_GUMMITRE_SLAB.get().asItem());
			tabData.accept(ItacraftModBlocks.EKTE_GUMMITRE_TRAPDOOR.get().asItem());
			tabData.accept(ItacraftModBlocks.EKTE_GUMMITRE_PRESSURE_PLATE.get().asItem());
			tabData.accept(ItacraftModBlocks.EKTE_GUMMITRE_LOG.get().asItem());
			tabData.accept(ItacraftModBlocks.STRIPPED_EKTE_GUMMITRE_LOG.get().asItem());
			tabData.accept(ItacraftModBlocks.EKTE_GUMMITRE_PLANKS.get().asItem());
			tabData.accept(ItacraftModBlocks.EKTE_GUMMITRE_BUTTON.get().asItem());
			tabData.accept(ItacraftModBlocks.EKTE_GUMMITRE_FENCE.get().asItem());
			tabData.accept(ItacraftModBlocks.EKTE_GUMMITRE_FENCE_GATE.get().asItem());
			tabData.accept(ItacraftModBlocks.STRIPPED_EKTE_GUMMITRE_WOOD.get().asItem());
			tabData.accept(ItacraftModBlocks.EKTE_GUMMITRE_STAIRS.get().asItem());
		} else if (tabData.getTabKey() == CreativeModeTabs.INGREDIENTS) {
			tabData.accept(ItacraftModItems.PLASTIC.get());
			tabData.accept(ItacraftModItems.WHITE_COLORED_PLASTIC.get());
			tabData.accept(ItacraftModItems.RED_COLORED_PLASTIC.get());
			tabData.accept(ItacraftModItems.TRANSPARENT_PLASTIC.get());
			tabData.accept(ItacraftModItems.PLASTIC_CIRCUIT_BOARD_MOLD.get());
			tabData.accept(ItacraftModItems.RUBBER.get());
			tabData.accept(ItacraftModItems.LATEX.get());
		} else if (tabData.getTabKey() == CreativeModeTabs.OP_BLOCKS) {
			if (tabData.hasPermissions()) {
				tabData.accept(ItacraftModBlocks.RUSSIA_BLOCK.get().asItem());
			}
		} else if (tabData.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
			tabData.accept(ItacraftModBlocks.RUSSIA_BLOCK.get().asItem());
			tabData.accept(ItacraftModBlocks.SOLDERING_TABLE.get().asItem());
		} else if (tabData.getTabKey() == CreativeModeTabs.REDSTONE_BLOCKS) {
			tabData.accept(ItacraftModBlocks.VIPPSMINIBANK.get().asItem());
		} else if (tabData.getTabKey() == CreativeModeTabs.COMBAT) {
			tabData.accept(ItacraftModItems.LEAD_ARMOR_CHESTPLATE.get());
			tabData.accept(ItacraftModItems.LEAD_ARMOR_LEGGINGS.get());
			tabData.accept(ItacraftModItems.BESJ.get());
			tabData.accept(ItacraftModItems.CONDOME.get());
		} else if (tabData.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
			tabData.accept(ItacraftModItems.LEAD.get());
			tabData.accept(ItacraftModBlocks.LEAD_ORE.get().asItem());
			tabData.accept(ItacraftModBlocks.MARIJUANA.get().asItem());
			tabData.accept(ItacraftModBlocks.EKTE_GUMMITRE_LEAVES.get().asItem());
		}
	}
}