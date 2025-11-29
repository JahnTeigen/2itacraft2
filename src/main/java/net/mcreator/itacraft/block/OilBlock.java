package net.mcreator.itacraft.block;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class OilBlock extends LiquidBlock {
	public OilBlock(BlockBehaviour.Properties properties) {
		super(ItacraftModFluids.OIL.get(), properties.mapColor(MapColor.FIRE).strength(100f).noCollission().noLootTable().liquid().pushReaction(PushReaction.DESTROY).sound(SoundType.EMPTY).replaceable());
	}
}