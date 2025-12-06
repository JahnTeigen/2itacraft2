package net.mcreator.itacraft.block;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class BlockOfBygg54Block extends Block {
	public BlockOfBygg54Block(BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.ANVIL).strength(7.95f, 20f));
	}

	@Override
	public int getLightBlock(BlockState state) {
		return 15;
	}
}