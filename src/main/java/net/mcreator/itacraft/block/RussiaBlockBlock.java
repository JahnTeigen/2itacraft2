package net.mcreator.itacraft.block;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class RussiaBlockBlock extends Block {
	public RussiaBlockBlock(BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.LODESTONE).strength(1f, 20f).requiresCorrectToolForDrops().friction(1f).speedFactor(1.1f).jumpFactor(1.1f).instrument(NoteBlockInstrument.BASS));
	}

	@Override
	public int getLightBlock(BlockState state) {
		return 15;
	}

	@Override
	public boolean isSignalSource(BlockState state) {
		return true;
	}

	@Override
	public int getSignal(BlockState blockstate, BlockGetter blockAccess, BlockPos pos, Direction direction) {
		return 1;
	}

	@Override
	public int getExpDrop(BlockState state, LevelAccessor level, BlockPos pos, BlockEntity blockEntity, Entity breaker, ItemStack tool) {
		return Mth.randomBetweenInclusive(level.getRandom(), 6, 7);
	}

	@Override
	public boolean canConnectRedstone(BlockState state, BlockGetter world, BlockPos pos, Direction side) {
		return true;
	}
}