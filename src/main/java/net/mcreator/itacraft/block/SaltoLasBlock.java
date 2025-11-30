package net.mcreator.itacraft.block;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class SaltoLasBlock extends Block {
	public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;

	public SaltoLasBlock(BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.GRAVEL).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state) {
		return true;
	}

	@Override
	public int getLightBlock(BlockState state) {
		return 0;
	}

	@Override
	public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return Shapes.empty();
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return switch (state.getValue(FACING)) {
			default -> Shapes.or(box(7, 1.5, 0.5, 12.5, 2.5, 1), box(7, 4.5, 0.5, 8.5, 6.5, 1), box(6, 0, 0, 9.5, 7, 0.5));
			case NORTH -> Shapes.or(box(3.5, 1.5, 15, 9, 2.5, 15.5), box(7.5, 4.5, 15, 9, 6.5, 15.5), box(6.5, 0, 15.5, 10, 7, 16));
			case EAST -> Shapes.or(box(0.5, 1.5, 3.5, 1, 2.5, 9), box(0.5, 4.5, 7.5, 1, 6.5, 9), box(0, 0, 6.5, 0.5, 7, 10));
			case WEST -> Shapes.or(box(15, 1.5, 7, 15.5, 2.5, 12.5), box(15, 4.5, 7, 15.5, 6.5, 8.5), box(15.5, 0, 6, 16, 7, 9.5));
		};
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		if (context.getClickedFace().getAxis() == Direction.Axis.Y)
			return super.getStateForPlacement(context).setValue(FACING, Direction.NORTH);
		return super.getStateForPlacement(context).setValue(FACING, context.getClickedFace());
	}

	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
	}
}