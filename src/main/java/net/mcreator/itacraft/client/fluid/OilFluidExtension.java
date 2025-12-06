package net.mcreator.itacraft.client.fluid;

@EventBusSubscriber(Dist.CLIENT)
public class OilFluidExtension {
	@SubscribeEvent
	public static void registerFluidTypeExtensions(RegisterClientExtensionsEvent event) {
		event.registerFluidType(new IClientFluidTypeExtensions() {
			private static final ResourceLocation STILL_TEXTURE = ResourceLocation.parse("itacraft:block/oil_still");
			private static final ResourceLocation FLOWING_TEXTURE = ResourceLocation.parse("itacraft:block/oil");
			private static final ResourceLocation RENDER_OVERLAY_TEXTURE = ResourceLocation.parse("itacraft:textures/oil.png");

			@Override
			public ResourceLocation getStillTexture() {
				return STILL_TEXTURE;
			}

			@Override
			public ResourceLocation getFlowingTexture() {
				return FLOWING_TEXTURE;
			}

			@Override
			public ResourceLocation getRenderOverlayTexture(Minecraft minecraft) {
				return RENDER_OVERLAY_TEXTURE;
			}

			@Override
			public Vector4f modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector4f fluidFogColor) {
				return new Vector4f(0f, 0f, 0f, fluidFogColor.w);
			}

			@Override
			public void modifyFogRender(Camera camera, @Nullable FogEnvironment environment, float renderDistance, float partialTick, FogData fogData) {
				float nearDistance = fogData.environmentalStart;
				float farDistance = fogData.environmentalEnd;
				Entity entity = camera.getEntity();
				Level world = entity.level();
				fogData.environmentalStart = 0f;
				fogData.environmentalEnd = 5f;
			}
		}, ItacraftModFluidTypes.OIL_TYPE.get());
	}
}