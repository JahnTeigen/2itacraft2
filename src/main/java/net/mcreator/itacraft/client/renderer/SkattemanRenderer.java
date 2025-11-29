package net.mcreator.itacraft.client.renderer;

public class SkattemanRenderer extends MobRenderer<SkattemanEntity, LivingEntityRenderState, ModelGamerGirl> {
	private SkattemanEntity entity = null;

	public SkattemanRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelGamerGirl(context.bakeLayer(ModelGamerGirl.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	public void extractRenderState(SkattemanEntity entity, LivingEntityRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		this.entity = entity;
	}

	@Override
	public ResourceLocation getTextureLocation(LivingEntityRenderState state) {
		return ResourceLocation.parse("itacraft:textures/entities/2024_01_15_tax-collector-22260226.png");
	}
}