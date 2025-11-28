package net.mcreator.itacraft.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.mcreator.itacraft.entity.GeirHaoyEntity;
import net.mcreator.itacraft.client.model.ModelGeirHaoy;

public class GeirHaoyRenderer extends MobRenderer<GeirHaoyEntity, LivingEntityRenderState, ModelGeirHaoy> {
	private GeirHaoyEntity entity = null;

	public GeirHaoyRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelGeirHaoy(context.bakeLayer(ModelGeirHaoy.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	public void extractRenderState(GeirHaoyEntity entity, LivingEntityRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		this.entity = entity;
	}

	@Override
	public ResourceLocation getTextureLocation(LivingEntityRenderState state) {
		return ResourceLocation.parse("itacraft:textures/entities/skinbase.png");
	}
}