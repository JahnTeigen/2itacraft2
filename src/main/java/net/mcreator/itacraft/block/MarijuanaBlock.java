package net.mcreator.itacraft.block;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class MarijuanaBlock extends FlowerBlock {
	public MarijuanaBlock(BlockBehaviour.Properties properties) {
		super(MobEffects.SPEED, 100, properties.mapColor(MapColor.PLANT).sound(SoundType.GRASS).instabreak().noCollission().offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY));
	}

	@Override
	public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
		return 100;
	}

	@Override
	public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
		return 60;
	}
}