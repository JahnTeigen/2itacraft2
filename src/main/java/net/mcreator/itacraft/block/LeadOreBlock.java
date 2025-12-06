package net.mcreator.itacraft.block;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class LeadOreBlock extends Block {
	public LeadOreBlock(BlockBehaviour.Properties properties) {
		super(properties.strength(1.8f, 13f).requiresCorrectToolForDrops().instrument(NoteBlockInstrument.BASEDRUM));
	}

	@Override
	public int getLightBlock(BlockState state) {
		return 15;
	}
}