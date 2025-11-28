package net.mcreator.itacraft.block;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class AndreasBlock extends Block {
	public AndreasBlock(BlockBehaviour.Properties properties) {
		super(properties.mapColor(MapColor.FIRE).sound(SoundType.SHROOMLIGHT).strength(2.85f, 20f).lightLevel(s -> 11).requiresCorrectToolForDrops().pushReaction(PushReaction.DESTROY).instrument(NoteBlockInstrument.BIT));
	}

	@Override
	public Integer getBeaconColorMultiplier(BlockState state, LevelReader world, BlockPos pos, BlockPos beaconPos) {
		return ARGB.opaque(-10066432);
	}

	@Override
	public int getLightBlock(BlockState state) {
		return 15;
	}

	@Override
	public InteractionResult useWithoutItem(BlockState blockstate, Level world, BlockPos pos, Player entity, BlockHitResult hit) {
		super.useWithoutItem(blockstate, world, pos, entity, hit);
		if (entity instanceof ServerPlayer player) {
			player.openMenu(new MenuProvider() {
				@Override
				public Component getDisplayName() {
					return Component.literal("andreas");
				}

				@Override
				public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
					return new PatchTableGUIMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(pos));
				}
			}, pos);
		}
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		double hitX = hit.getLocation().x;
		double hitY = hit.getLocation().y;
		double hitZ = hit.getLocation().z;
		Direction direction = hit.getDirection();
		AndreasSinHemmelighetProcedure.execute(world, x, y, z);
		return InteractionResult.SUCCESS;
	}
}