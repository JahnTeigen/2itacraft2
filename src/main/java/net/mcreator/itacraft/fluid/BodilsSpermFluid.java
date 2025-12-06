package net.mcreator.itacraft.fluid;

import org.apache.logging.log4j.core.util.Source;

import net.neoforged.neoforge.fluids.BaseFlowingFluid;

import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.LiquidBlock;

import net.mcreator.itacraft.init.ItacraftModItems;
import net.mcreator.itacraft.init.ItacraftModFluids;
import net.mcreator.itacraft.init.ItacraftModFluidTypes;
import net.mcreator.itacraft.init.ItacraftModBlocks;

public abstract class BodilsSpermFluid extends BaseFlowingFluid {
	public static final BaseFlowingFluid.Properties PROPERTIES = new BaseFlowingFluid.Properties(() -> ItacraftModFluidTypes.BODILS_SPERM_TYPE.get(), () -> ItacraftModFluids.BODILS_SPERM.get(), () -> ItacraftModFluids.FLOWING_BODILS_SPERM.get())
			.explosionResistance(100f).bucket(() -> ItacraftModItems.BODILS_SPERM_BUCKET.get()).block(() -> (LiquidBlock) ItacraftModBlocks.BODILS_SPERM.get());

	private BodilsSpermFluid() {
		super(PROPERTIES);
	}

	public static class Source extends BodilsSpermFluid {
		public int getAmount(FluidState state) {
			return 8;
		}

		public boolean isSource(FluidState state) {
			return true;
		}
	}

	public static class Flowing extends BodilsSpermFluid {
		protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
			super.createFluidStateDefinition(builder);
			builder.add(LEVEL);
		}

		public int getAmount(FluidState state) {
			return state.getValue(LEVEL);
		}

		public boolean isSource(FluidState state) {
			return false;
		}
	}
}