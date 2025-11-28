package net.mcreator.itacraft.world.dimension;

public class KongsbergGruppenDimension {
	@EventBusSubscriber(Dist.CLIENT)
	public static class KongsbergGruppenSpecialEffectsHandler {
		@SubscribeEvent
		public static void registerDimensionSpecialEffects(RegisterDimensionSpecialEffectsEvent event) {
			DimensionSpecialEffects customEffect = new DimensionSpecialEffects(DimensionSpecialEffects.SkyType.OVERWORLD, false, false) {
				@Override
				public Vec3 getBrightnessDependentFogColor(Vec3 color, float sunHeight) {
					return color;
				}

				@Override
				public boolean isFoggyAt(int x, int y) {
					return false;
				}
			};
			event.register(ResourceLocation.parse("itacraft:kongsberg_gruppen"), customEffect);
		}
	}
}