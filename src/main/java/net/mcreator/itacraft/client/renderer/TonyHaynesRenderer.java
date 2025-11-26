package net.mcreator.itacraft.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.mcreator.itacraft.entity.TonyHaynesEntity;
import net.mcreator.itacraft.client.model.ModelTonyHaynes;

public class TonyHaynesRenderer extends MobRenderer<TonyHaynesEntity, LivingEntityRenderState, ModelTonyHaynes> {
	private TonyHaynesEntity entity = null;

	public TonyHaynesRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelTonyHaynes(context.bakeLayer(ModelTonyHaynes.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	public void extractRenderState(TonyHaynesEntity entity, LivingEntityRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		this.entity = entity;
	}

	@Override
	public ResourceLocation getTextureLocation(LivingEntityRenderState state) {
		return ResourceLocation.parse("itacraft:textures/entities/haynes.png");
	}
}