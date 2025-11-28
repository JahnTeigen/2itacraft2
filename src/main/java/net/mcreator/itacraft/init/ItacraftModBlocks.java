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
	}

	// Start of user code block custom blocks
	// End of user code block custom blocks
	private static <B extends Block> DeferredBlock<B> register(String name, Function<BlockBehaviour.Properties, ? extends B> supplier) {
		return REGISTRY.registerBlock(name, supplier);
	}
}