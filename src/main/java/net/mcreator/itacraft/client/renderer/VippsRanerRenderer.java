package net.mcreator.itacraft.client.renderer;

public class VippsRanerRenderer extends MobRenderer<VippsRanerEntity, LivingEntityRenderState, ModelGamerGirl> {
	private VippsRanerEntity entity = null;

	public VippsRanerRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelGamerGirl(context.bakeLayer(ModelGamerGirl.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	public void extractRenderState(VippsRanerEntity entity, LivingEntityRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		this.entity = entity;
	}

	@Override
	public ResourceLocation getTextureLocation(LivingEntityRenderState state) {
		return ResourceLocation.parse("itacraft:textures/entities/vipps_raner.png");
	}
}