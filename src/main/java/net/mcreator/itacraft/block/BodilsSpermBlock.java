package net.mcreator.itacraft.block;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class BodilsSpermBlock extends LiquidBlock {
	public BodilsSpermBlock(BlockBehaviour.Properties properties) {
		super(ItacraftModFluids.BODILS_SPERM.get(), properties.mapColor(MapColor.FIRE).strength(100f).noCollission().noLootTable().liquid().pushReaction(PushReaction.DESTROY).sound(SoundType.EMPTY).replaceable());
	}
}