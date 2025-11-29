package net.mcreator.itacraft.block;

import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.LiquidBlock;

import net.mcreator.itacraft.init.ItacraftModFluids;

public class OilBlock extends LiquidBlock {
	public OilBlock(BlockBehaviour.Properties properties) {
		super(ItacraftModFluids.OIL.get(), properties.mapColor(MapColor.FIRE).strength(100f).noCollission().noLootTable().liquid().pushReaction(PushReaction.DESTROY).sound(SoundType.EMPTY).replaceable());
	}
}