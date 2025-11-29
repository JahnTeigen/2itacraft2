package net.mcreator.itacraft.client.renderer;

public class GudrunRenderer extends MobRenderer<GudrunEntity, LivingEntityRenderState, ModelBodil> {
	private GudrunEntity entity = null;

	public GudrunRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelBodil(context.bakeLayer(ModelBodil.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	public void extractRenderState(GudrunEntity entity, LivingEntityRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		this.entity = entity;
	}

	@Override
	public ResourceLocation getTextureLocation(LivingEntityRenderState state) {
		return ResourceLocation.parse("itacraft:textures/entities/bodil_texture.png");
	}
}