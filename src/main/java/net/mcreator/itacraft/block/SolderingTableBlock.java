package net.mcreator.itacraft.block;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class SolderingTableBlock extends Block {
	public SolderingTableBlock(BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.ANVIL).strength(1.4f, 6.5f).lightLevel(s -> 3));
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
					return Component.literal("Soldering Table");
				}

				@Override
				public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
					return new SolderingTableGUIMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(pos));
				}
			}, pos);
		}
		return InteractionResult.SUCCESS;
	}
}