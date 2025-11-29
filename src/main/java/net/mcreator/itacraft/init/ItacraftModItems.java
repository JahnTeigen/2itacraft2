/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.itacraft.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.fluids.capability.wrappers.FluidBucketWrapper;
import net.neoforged.neoforge.client.event.RegisterRangeSelectItemModelPropertyEvent;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.itacraft.item.*;
import net.mcreator.itacraft.ItacraftMod;

import java.util.function.Function;

public class ItacraftModItems {
	public static final DeferredRegister.Items REGISTRY = DeferredRegister.createItems(ItacraftMod.MODID);
	public static final DeferredItem<Item> GEIR_HAOY_SPAWN_EGG;
	public static final DeferredItem<Item> KOG_STOCK;
	public static final DeferredItem<Item> JUDE_SPAWN_EGG;
	public static final DeferredItem<Item> ANDREAS;
	public static final DeferredItem<Item> FEMTI_KRONER;
	public static final DeferredItem<Item> FEM_HUNDRE_KRONER;
	public static final DeferredItem<Item> ETT_TUSEN_KRONER;
	public static final DeferredItem<Item> PERSONOPPLYSNINGSBLANKETT;
	public static final DeferredItem<Item> BODIL_SPAWN_EGG;
	public static final DeferredItem<Item> HEROIN;
	public static final DeferredItem<Item> BLOCK_OF_BYGG_54;
	public static final DeferredItem<Item> GAMER_GIRL_SPAWN_EGG;
	public static final DeferredItem<Item> TONY_HAYNES_SPAWN_EGG;
	public static final DeferredItem<Item> WIRE;
	public static final DeferredItem<Item> PATCH_CABLE;
	public static final DeferredItem<Item> RJ_45_TYPE_PLUG;
	public static final DeferredItem<Item> MODULAR_PLIERS;
	public static final DeferredItem<Item> RJ_45_TYPE_ETHERNET_CABLE;
	public static final DeferredItem<Item> FOILED_RJ_45_TYPE_ETHERNET_CABLE;
	public static final DeferredItem<Item> OIL_BUCKET;
	public static final DeferredItem<Item> PLASTIC;
	public static final DeferredItem<Item> WHITE_COLORED_PLASTIC;
	public static final DeferredItem<Item> HP_WORKSTATION;
	public static final DeferredItem<Item> EN_KRONE;
	public static final DeferredItem<Item> FEM_KRONER;
	public static final DeferredItem<Item> TI_KRONER;
	public static final DeferredItem<Item> TJUE_KRONER;
	public static final DeferredItem<Item> TO_HUNDRE_KRONER;
	public static final DeferredItem<Item> HUNDRE_KRONER;
	public static final DeferredItem<Item> RED_COLORED_PLASTIC;
	public static final DeferredItem<Item> ETT_ORE;
	public static final DeferredItem<Item> TI_ORE;
	public static final DeferredItem<Item> HAWK_PHONE;
	public static final DeferredItem<Item> HP_MONITOR;
	public static final DeferredItem<Item> RUSSIA_BLOCK;
	public static final DeferredItem<Item> VIPPSMINIBANK;
	public static final DeferredItem<Item> COMMUNISM;
	public static final DeferredItem<Item> FIRESTAFF;
	public static final DeferredItem<Item> TOM_NAL;
	public static final DeferredItem<Item> NAL_MED_NOE_RART_I;
	public static final DeferredItem<Item> GIFTIG_GUGGE;
	public static final DeferredItem<Item> RADIOACTIVE_WASTE_BARREL;
	public static final DeferredItem<Item> LEAD_ARMOR_CHESTPLATE;
	public static final DeferredItem<Item> LEAD_ARMOR_LEGGINGS;
	public static final DeferredItem<Item> LEAD;
	public static final DeferredItem<Item> LEAD_ORE;
	public static final DeferredItem<Item> TRANSPARENT_PLASTIC;
	public static final DeferredItem<Item> MARIJUANA;
	public static final DeferredItem<Item> MONICA_SPAWN_EGG;
	public static final DeferredItem<Item> BESJ;
	public static final DeferredItem<Item> VIPPS_RANER_SPAWN_EGG;
	public static final DeferredItem<Item> CUM;
	public static final DeferredItem<Item> FETUS;
	public static final DeferredItem<Item> HDMI_PLUG;
	public static final DeferredItem<Item> HDMI_CABLE;
	public static final DeferredItem<Item> SOLDERING_TABLE;
	public static final DeferredItem<Item> NAL_MED_CUM;
	public static final DeferredItem<Item> PLASTIC_CIRCUIT_BOARD_MOLD;
	public static final DeferredItem<Item> CIRCUIT_BOARD;
	public static final DeferredItem<Item> MICROPLASTIC;
	public static final DeferredItem<Item> SKATTEETATEN_SPAWN_EGG;
	public static final DeferredItem<Item> RETARDED_GAMER_GIRL_SPAWN_EGG;
	public static final DeferredItem<Item> RADIATED_CUM;
	public static final DeferredItem<Item> CONDOME;
	public static final DeferredItem<Item> DILDO;
	static {
		GEIR_HAOY_SPAWN_EGG = register("geir_haoy_spawn_egg", properties -> new SpawnEggItem(ItacraftModEntities.GEIR_HAOY.get(), properties));
		KOG_STOCK = register("kog_stock", KOGStockItem::new);
		JUDE_SPAWN_EGG = register("jude_spawn_egg", properties -> new SpawnEggItem(ItacraftModEntities.JUDE.get(), properties));
		ANDREAS = block(ItacraftModBlocks.ANDREAS, new Item.Properties().rarity(Rarity.EPIC).fireResistant());
		FEMTI_KRONER = register("femti_kroner", FemtiKronerItem::new);
		FEM_HUNDRE_KRONER = register("fem_hundre_kroner", FemHundreKronerItem::new);
		ETT_TUSEN_KRONER = register("ett_tusen_kroner", EttTusenKronerItem::new);
		PERSONOPPLYSNINGSBLANKETT = register("personopplysningsblankett", PersonopplysningsblankettItem::new);
		BODIL_SPAWN_EGG = register("bodil_spawn_egg", properties -> new SpawnEggItem(ItacraftModEntities.BODIL.get(), properties));
		HEROIN = register("heroin", HeroinItem::new);
		BLOCK_OF_BYGG_54 = block(ItacraftModBlocks.BLOCK_OF_BYGG_54, new Item.Properties().rarity(Rarity.EPIC));
		GAMER_GIRL_SPAWN_EGG = register("gamer_girl_spawn_egg", properties -> new SpawnEggItem(ItacraftModEntities.GAMER_GIRL.get(), properties));
		TONY_HAYNES_SPAWN_EGG = register("tony_haynes_spawn_egg", properties -> new SpawnEggItem(ItacraftModEntities.TONY_HAYNES.get(), properties));
		WIRE = register("wire", WireItem::new);
		PATCH_CABLE = register("patch_cable", PatchCableItem::new);
		RJ_45_TYPE_PLUG = register("rj_45_type_plug", RJ45TypePlugItem::new);
		MODULAR_PLIERS = register("modular_pliers", ModularPliersItem::new);
		RJ_45_TYPE_ETHERNET_CABLE = register("rj_45_type_ethernet_cable", RJ45TypeEthernetCableItem::new);
		FOILED_RJ_45_TYPE_ETHERNET_CABLE = register("foiled_rj_45_type_ethernet_cable", FoiledRJ45TypeEthernetCableItem::new);
		OIL_BUCKET = register("oil_bucket", OilItem::new);
		PLASTIC = register("plastic", PlasticItem::new);
		WHITE_COLORED_PLASTIC = register("white_colored_plastic", WhiteColoredPlasticItem::new);
		HP_WORKSTATION = block(ItacraftModBlocks.HP_WORKSTATION);
		EN_KRONE = register("en_krone", EnKroneItem::new);
		FEM_KRONER = register("fem_kroner", FemKronerItem::new);
		TI_KRONER = register("ti_kroner", TiKronerItem::new);
		TJUE_KRONER = register("tjue_kroner", TjueKronerItem::new);
		TO_HUNDRE_KRONER = register("to_hundre_kroner", ToHundreKronerItem::new);
		HUNDRE_KRONER = register("hundre_kroner", HundreKronerItem::new);
		RED_COLORED_PLASTIC = register("red_colored_plastic", RedColoredPlasticItem::new);
		ETT_ORE = register("ett_ore", EttOreItem::new);
		TI_ORE = register("ti_ore", TiOreItem::new);
		HAWK_PHONE = register("hawk_phone", HawkPhoneItem::new);
		HP_MONITOR = block(ItacraftModBlocks.HP_MONITOR);
		RUSSIA_BLOCK = block(ItacraftModBlocks.RUSSIA_BLOCK, new Item.Properties().stacksTo(1).rarity(Rarity.RARE).fireResistant());
		VIPPSMINIBANK = block(ItacraftModBlocks.VIPPSMINIBANK);
		COMMUNISM = register("communism", CommunismItem::new);
		FIRESTAFF = register("firestaff", FirestaffItem::new);
		TOM_NAL = register("tom_nal", TomNalItem::new);
		NAL_MED_NOE_RART_I = register("nal_med_noe_rart_i", NalMedNoeRartIItem::new);
		GIFTIG_GUGGE = register("giftig_gugge", GiftigGuggeItem::new);
		RADIOACTIVE_WASTE_BARREL = block(ItacraftModBlocks.RADIOACTIVE_WASTE_BARREL, new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON).fireResistant());
		LEAD_ARMOR_CHESTPLATE = register("lead_armor_chestplate", LeadArmorItem.Chestplate::new);
		LEAD_ARMOR_LEGGINGS = register("lead_armor_leggings", LeadArmorItem.Leggings::new);
		LEAD = register("lead", LeadItem::new);
		LEAD_ORE = block(ItacraftModBlocks.LEAD_ORE, new Item.Properties().fireResistant());
		TRANSPARENT_PLASTIC = register("transparent_plastic", TransparentPlasticItem::new);
		MARIJUANA = block(ItacraftModBlocks.MARIJUANA);
		MONICA_SPAWN_EGG = register("monica_spawn_egg", properties -> new SpawnEggItem(ItacraftModEntities.MONICA.get(), properties));
		BESJ = register("besj", BesjItem::new);
		VIPPS_RANER_SPAWN_EGG = register("vipps_raner_spawn_egg", properties -> new SpawnEggItem(ItacraftModEntities.VIPPS_RANER.get(), properties));
		CUM = register("cum", CumitemItem::new);
		FETUS = register("fetus", FetusItem::new);
		HDMI_PLUG = register("hdmi_plug", HDMIPlugItem::new);
		HDMI_CABLE = register("hdmi_cable", HDMICableItem::new);
		SOLDERING_TABLE = block(ItacraftModBlocks.SOLDERING_TABLE, new Item.Properties().rarity(Rarity.UNCOMMON));
		NAL_MED_CUM = register("nal_med_cum", NalMedCumItem::new);
		PLASTIC_CIRCUIT_BOARD_MOLD = register("plastic_circuit_board_mold", PlasticCircuitBoardMoldItem::new);
		CIRCUIT_BOARD = register("circuit_board", CircuitBoardItem::new);
		MICROPLASTIC = register("microplastic", MicroplasticItem::new);
		SKATTEETATEN_SPAWN_EGG = register("skatteetaten_spawn_egg", properties -> new SpawnEggItem(ItacraftModEntities.SKATTEETATEN.get(), properties));
		RETARDED_GAMER_GIRL_SPAWN_EGG = register("retarded_gamer_girl_spawn_egg", properties -> new SpawnEggItem(ItacraftModEntities.RETARDED_GAMER_GIRL.get(), properties));
		RADIATED_CUM = register("radiated_cum", RadiatedCumItem::new);
		CONDOME = register("condome", CondomeItem::new);
		DILDO = register("dildo", DildoItem::new);
	}

	// Start of user code block custom items
	// End of user code block custom items
	private static <I extends Item> DeferredItem<I> register(String name, Function<Item.Properties, ? extends I> supplier) {
		return REGISTRY.registerItem(name, supplier, new Item.Properties());
	}

	private static DeferredItem<Item> block(DeferredHolder<Block, Block> block) {
		return block(block, new Item.Properties());
	}

	private static DeferredItem<Item> block(DeferredHolder<Block, Block> block, Item.Properties properties) {
		return REGISTRY.registerItem(block.getId().getPath(), prop -> new BlockItem(block.get(), prop), properties);
	}

	@SubscribeEvent
	public static void registerCapabilities(RegisterCapabilitiesEvent event) {
		event.registerItem(Capabilities.FluidHandler.ITEM, (stack, context) -> new FluidBucketWrapper(stack), OIL_BUCKET.get());
	}

	@EventBusSubscriber(Dist.CLIENT)
	public static class ItemsClientSideHandler {
		@SubscribeEvent
		public static void registerItemModelProperties(RegisterRangeSelectItemModelPropertyEvent event) {
			event.register(ResourceLocation.parse("itacraft:kog_stock/price"), KOGStockItem.PriceProperty.MAP_CODEC);
		}
	}
}