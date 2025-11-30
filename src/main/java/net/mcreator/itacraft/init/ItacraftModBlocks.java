/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.itacraft.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredBlock;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

import net.mcreator.itacraft.block.*;
import net.mcreator.itacraft.ItacraftMod;

import java.util.function.Function;

public class ItacraftModBlocks {
	public static final DeferredRegister.Blocks REGISTRY = DeferredRegister.createBlocks(ItacraftMod.MODID);
	public static final DeferredBlock<Block> ANDREAS;
	public static final DeferredBlock<Block> KONGSBERG_GRUPPEN_PORTAL;
	public static final DeferredBlock<Block> BLOCK_OF_BYGG_54;
	public static final DeferredBlock<Block> OIL;
	public static final DeferredBlock<Block> HP_WORKSTATION;
	public static final DeferredBlock<Block> HP_MONITOR;
	public static final DeferredBlock<Block> RUSSIA_BLOCK;
	public static final DeferredBlock<Block> VIPPSMINIBANK;
	public static final DeferredBlock<Block> RADIOACTIVE_WASTE_BARREL;
	public static final DeferredBlock<Block> LEAD_ORE;
	public static final DeferredBlock<Block> MARIJUANA;
	public static final DeferredBlock<Block> SOLDERING_TABLE;
	public static final DeferredBlock<Block> SECURITAS_KEYPAD;
	public static final DeferredBlock<Block> EKTE_GUMMITRE_LOG;
	public static final DeferredBlock<Block> EKTE_GUMMITRE_WOOD;
	public static final DeferredBlock<Block> STRIPPED_EKTE_GUMMITRE_LOG;
	public static final DeferredBlock<Block> STRIPPED_EKTE_GUMMITRE_WOOD;
	public static final DeferredBlock<Block> EKTE_GUMMITRE_PLANKS;
	public static final DeferredBlock<Block> EKTE_GUMMITRE_LEAVES;
	public static final DeferredBlock<Block> EKTE_GUMMITRE_STAIRS;
	public static final DeferredBlock<Block> EKTE_GUMMITRE_SLAB;
	public static final DeferredBlock<Block> EKTE_GUMMITRE_FENCE;
	public static final DeferredBlock<Block> EKTE_GUMMITRE_FENCE_GATE;
	public static final DeferredBlock<Block> EKTE_GUMMITRE_DOOR;
	public static final DeferredBlock<Block> EKTE_GUMMITRE_TRAPDOOR;
	public static final DeferredBlock<Block> EKTE_GUMMITRE_PRESSURE_PLATE;
	public static final DeferredBlock<Block> EKTE_GUMMITRE_BUTTON;
	static {
		ANDREAS = register("andreas", AndreasBlock::new);
		KONGSBERG_GRUPPEN_PORTAL = register("kongsberg_gruppen_portal", KongsbergGruppenPortalBlock::new);
		BLOCK_OF_BYGG_54 = register("block_of_bygg_54", BlockOfBygg54Block::new);
		OIL = register("oil", OilBlock::new);
		HP_WORKSTATION = register("hp_workstation", HPWorkstationBlock::new);
		HP_MONITOR = register("hp_monitor", HPMonitorBlock::new);
		RUSSIA_BLOCK = register("russia_block", RussiaBlockBlock::new);
		VIPPSMINIBANK = register("vippsminibank", VippsminibankBlock::new);
		RADIOACTIVE_WASTE_BARREL = register("radioactive_waste_barrel", RadioactiveWasteBarrelBlock::new);
		LEAD_ORE = register("lead_ore", LeadOreBlock::new);
		MARIJUANA = register("marijuana", MarijuanaBlock::new);
		SOLDERING_TABLE = register("soldering_table", SolderingTableBlock::new);
		SECURITAS_KEYPAD = register("securitas_keypad", SecuritasKeypadBlock::new);
		EKTE_GUMMITRE_LOG = register("ekte_gummitre_log", EkteGummitreLogBlock::new);
		EKTE_GUMMITRE_WOOD = register("ekte_gummitre_wood", EkteGummitreWoodBlock::new);
		STRIPPED_EKTE_GUMMITRE_LOG = register("stripped_ekte_gummitre_log", StrippedEkteGummitreLogBlock::new);
		STRIPPED_EKTE_GUMMITRE_WOOD = register("stripped_ekte_gummitre_wood", StrippedEkteGummitreWoodBlock::new);
		EKTE_GUMMITRE_PLANKS = register("ekte_gummitre_planks", EkteGummitrePlanksBlock::new);
		EKTE_GUMMITRE_LEAVES = register("ekte_gummitre_leaves", EkteGummitreLeavesBlock::new);
		EKTE_GUMMITRE_STAIRS = register("ekte_gummitre_stairs", EkteGummitreStairsBlock::new);
		EKTE_GUMMITRE_SLAB = register("ekte_gummitre_slab", EkteGummitreSlabBlock::new);
		EKTE_GUMMITRE_FENCE = register("ekte_gummitre_fence", EkteGummitreFenceBlock::new);
		EKTE_GUMMITRE_FENCE_GATE = register("ekte_gummitre_fence_gate", EkteGummitreFenceGateBlock::new);
		EKTE_GUMMITRE_DOOR = register("ekte_gummitre_door", EkteGummitreDoorBlock::new);
		EKTE_GUMMITRE_TRAPDOOR = register("ekte_gummitre_trapdoor", EkteGummitreTrapdoorBlock::new);
		EKTE_GUMMITRE_PRESSURE_PLATE = register("ekte_gummitre_pressure_plate", EkteGummitrePressurePlateBlock::new);
		EKTE_GUMMITRE_BUTTON = register("ekte_gummitre_button", EkteGummitreButtonBlock::new);
	}

	// Start of user code block custom blocks
	// End of user code block custom blocks
	private static <B extends Block> DeferredBlock<B> register(String name, Function<BlockBehaviour.Properties, ? extends B> supplier) {
		return REGISTRY.registerBlock(name, supplier);
	}
}