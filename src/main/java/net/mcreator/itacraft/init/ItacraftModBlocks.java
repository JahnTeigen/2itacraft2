/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.itacraft.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredBlock;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

import net.mcreator.itacraft.block.OilBlock;
import net.mcreator.itacraft.block.KongsbergGruppenPortalBlock;
import net.mcreator.itacraft.block.HPWorkstationBlock;
import net.mcreator.itacraft.block.BlockOfBygg54Block;
import net.mcreator.itacraft.block.AndreasBlock;
import net.mcreator.itacraft.ItacraftMod;

import java.util.function.Function;

public class ItacraftModBlocks {
	public static final DeferredRegister.Blocks REGISTRY = DeferredRegister.createBlocks(ItacraftMod.MODID);
	public static final DeferredBlock<Block> ANDREAS;
	public static final DeferredBlock<Block> KONGSBERG_GRUPPEN_PORTAL;
	public static final DeferredBlock<Block> BLOCK_OF_BYGG_54;
	public static final DeferredBlock<Block> OIL;
	public static final DeferredBlock<Block> HP_WORKSTATION;
	static {
		ANDREAS = register("andreas", AndreasBlock::new);
		KONGSBERG_GRUPPEN_PORTAL = register("kongsberg_gruppen_portal", KongsbergGruppenPortalBlock::new);
		BLOCK_OF_BYGG_54 = register("block_of_bygg_54", BlockOfBygg54Block::new);
		OIL = register("oil", OilBlock::new);
		HP_WORKSTATION = register("hp_workstation", HPWorkstationBlock::new);
	}

	// Start of user code block custom blocks
	// End of user code block custom blocks
	private static <B extends Block> DeferredBlock<B> register(String name, Function<BlockBehaviour.Properties, ? extends B> supplier) {
		return REGISTRY.registerBlock(name, supplier);
	}
}