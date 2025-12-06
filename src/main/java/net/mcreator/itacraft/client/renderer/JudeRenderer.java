package net.mcreator.itacraft.client.renderer;

public class JudeRenderer extends MobRenderer<JudeEntity, LivingEntityRenderState, ModelJude> {
	private JudeEntity entity = null;

	public JudeRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelJude(context.bakeLayer(ModelJude.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	public void extractRenderState(JudeEntity entity, LivingEntityRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		this.entity = entity;
	}

	@Override
	public ResourceLocation getTextureLocation(LivingEntityRenderState state) {
		return ResourceLocation.parse("itacraft:textures/entities/villager.png");
	}
}