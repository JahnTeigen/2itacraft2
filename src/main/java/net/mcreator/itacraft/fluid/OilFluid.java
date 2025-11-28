package net.mcreator.itacraft.fluid;

public abstract class OilFluid extends BaseFlowingFluid {
	public static final BaseFlowingFluid.Properties PROPERTIES = new BaseFlowingFluid.Properties(() -> ItacraftModFluidTypes.OIL_TYPE.get(), () -> ItacraftModFluids.OIL.get(), () -> ItacraftModFluids.FLOWING_OIL.get()).explosionResistance(100f)
			.tickRate(40).slopeFindDistance(5).bucket(() -> ItacraftModItems.OIL_BUCKET.get()).block(() -> (LiquidBlock) ItacraftModBlocks.OIL.get());

	private OilFluid() {
		super(PROPERTIES);
	}

	public static class Source extends OilFluid {
		public int getAmount(FluidState state) {
			return 8;
		}

		public boolean isSource(FluidState state) {
			return true;
		}
	}

	public static class Flowing extends OilFluid {
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