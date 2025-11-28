package net.mcreator.itacraft.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.mcreator.itacraft.entity.MonicaEntity;
import net.mcreator.itacraft.client.model.ModelBodil;

public class MonicaRenderer extends MobRenderer<MonicaEntity, LivingEntityRenderState, ModelBodil> {
	private MonicaEntity entity = null;

	public MonicaRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelBodil(context.bakeLayer(ModelBodil.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	public void extractRenderState(MonicaEntity entity, LivingEntityRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		this.entity = entity;
	}

	@Override
	public ResourceLocation getTextureLocation(LivingEntityRenderState state) {
		return ResourceLocation.parse("itacraft:textures/entities/monica_texture.png");
	}

	@Override
	protected boolean isShaking(LivingEntityRenderState state) {
		return true;
	}
}