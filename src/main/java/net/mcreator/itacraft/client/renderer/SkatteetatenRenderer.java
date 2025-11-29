package net.mcreator.itacraft.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.mcreator.itacraft.entity.SkatteetatenEntity;
import net.mcreator.itacraft.client.model.ModelGamerGirl;

public class SkatteetatenRenderer extends MobRenderer<SkatteetatenEntity, LivingEntityRenderState, ModelGamerGirl> {
	private SkatteetatenEntity entity = null;

	public SkatteetatenRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelGamerGirl(context.bakeLayer(ModelGamerGirl.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	public void extractRenderState(SkatteetatenEntity entity, LivingEntityRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		this.entity = entity;
	}

	@Override
	public ResourceLocation getTextureLocation(LivingEntityRenderState state) {
		return ResourceLocation.parse("itacraft:textures/entities/2024_01_15_tax-collector-22260226.png");
	}
}