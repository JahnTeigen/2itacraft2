package net.mcreator.itacraft.client.renderer;

public class RetardedGamerGirlRenderer extends HumanoidMobRenderer<RetardedGamerGirlEntity, HumanoidRenderState, HumanoidModel<HumanoidRenderState>> {
	private RetardedGamerGirlEntity entity = null;

	public RetardedGamerGirlRenderer(EntityRendererProvider.Context context) {
		super(context, new HumanoidModel<HumanoidRenderState>(context.bakeLayer(ModelLayers.PLAYER)), 0.5f);
		this.addLayer(new HumanoidArmorLayer(this, new HumanoidModel(context.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)), new HumanoidModel(context.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)), context.getEquipmentRenderer()));
	}

	@Override
	public HumanoidRenderState createRenderState() {
		return new HumanoidRenderState();
	}

	@Override
	public void extractRenderState(RetardedGamerGirlEntity entity, HumanoidRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		this.entity = entity;
	}

	@Override
	public ResourceLocation getTextureLocation(HumanoidRenderState state) {
		return ResourceLocation.parse("itacraft:textures/entities/retardedgamergirl.png");
	}
}