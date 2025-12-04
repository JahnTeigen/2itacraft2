package net.mcreator.itacraft.block;

import org.checkerframework.checker.units.qual.s;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.InteractionResult;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.mcreator.itacraft.world.inventory.SecuritasGUIMenu;

import io.netty.buffer.Unpooled;

public class SecuritasKeypadBlock extends Block {
	public static final EnumProperty<Direction> FACING = DirectionalBlock.FACING;

	public SecuritasKeypadBlock(BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.BONE_BLOCK).strength(0f, 10f).lightLevel(s -> 2).noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
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
			default -> Shapes.or(box(2, 2, 11, 14, 18, 16), box(10, 12, 10, 12, 14, 12), box(7, 12, 10, 9, 14, 12), box(4, 12, 10, 6, 14, 12), box(10, 9, 10, 12, 11, 12), box(7, 9, 10, 9, 11, 12), box(4, 9, 10, 6, 11, 12), box(10, 6, 10, 12, 8, 12),
					box(7, 3, 10, 9, 5, 12), box(7, 6, 10, 9, 8, 12), box(4, 6, 10, 6, 8, 12));
			case NORTH -> Shapes.or(box(2, 2, 0, 14, 18, 5), box(4, 12, 4, 6, 14, 6), box(7, 12, 4, 9, 14, 6), box(10, 12, 4, 12, 14, 6), box(4, 9, 4, 6, 11, 6), box(7, 9, 4, 9, 11, 6), box(10, 9, 4, 12, 11, 6), box(4, 6, 4, 6, 8, 6),
					box(7, 3, 4, 9, 5, 6), box(7, 6, 4, 9, 8, 6), box(10, 6, 4, 12, 8, 6));
			case EAST -> Shapes.or(box(11, 2, 2, 16, 18, 14), box(10, 12, 4, 12, 14, 6), box(10, 12, 7, 12, 14, 9), box(10, 12, 10, 12, 14, 12), box(10, 9, 4, 12, 11, 6), box(10, 9, 7, 12, 11, 9), box(10, 9, 10, 12, 11, 12), box(10, 6, 4, 12, 8, 6),
					box(10, 3, 7, 12, 5, 9), box(10, 6, 7, 12, 8, 9), box(10, 6, 10, 12, 8, 12));
			case WEST -> Shapes.or(box(0, 2, 2, 5, 18, 14), box(4, 12, 10, 6, 14, 12), box(4, 12, 7, 6, 14, 9), box(4, 12, 4, 6, 14, 6), box(4, 9, 10, 6, 11, 12), box(4, 9, 7, 6, 11, 9), box(4, 9, 4, 6, 11, 6), box(4, 6, 10, 6, 8, 12),
					box(4, 3, 7, 6, 5, 9), box(4, 6, 7, 6, 8, 9), box(4, 6, 4, 6, 8, 6));
			case UP -> Shapes.or(box(2, 11, 2, 14, 16, 18), box(4, 10, 12, 6, 12, 14), box(7, 10, 12, 9, 12, 14), box(10, 10, 12, 12, 12, 14), box(4, 10, 9, 6, 12, 11), box(7, 10, 9, 9, 12, 11), box(10, 10, 9, 12, 12, 11), box(4, 10, 6, 6, 12, 8),
					box(7, 10, 3, 9, 12, 5), box(7, 10, 6, 9, 12, 8), box(10, 10, 6, 12, 12, 8));
			case DOWN -> Shapes.or(box(2, 0, -2, 14, 5, 14), box(4, 4, 2, 6, 6, 4), box(7, 4, 2, 9, 6, 4), box(10, 4, 2, 12, 6, 4), box(4, 4, 5, 6, 6, 7), box(7, 4, 5, 9, 6, 7), box(10, 4, 5, 12, 6, 7), box(4, 4, 8, 6, 6, 10),
					box(7, 4, 11, 9, 6, 13), box(7, 4, 8, 9, 6, 10), box(10, 4, 8, 12, 6, 10));
		};
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return super.getStateForPlacement(context).setValue(FACING, context.getNearestLookingDirection().getOpposite());
	}

	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
	}

	@Override
	public InteractionResult useWithoutItem(BlockState blockstate, Level world, BlockPos pos, Player entity, BlockHitResult hit) {
		super.useWithoutItem(blockstate, world, pos, entity, hit);
		if (entity instanceof ServerPlayer player) {
			player.openMenu(new MenuProvider() {
				@Override
				public Component getDisplayName() {
					return Component.literal("Securitas Keypad");
				}

				@Override
				public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
					return new SecuritasGUIMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(pos));
				}
			}, pos);
		}
		return InteractionResult.SUCCESS;
	}
}