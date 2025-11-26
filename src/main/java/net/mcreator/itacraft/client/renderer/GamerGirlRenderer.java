package net.mcreator.itacraft.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.mcreator.itacraft.entity.GamerGirlEntity;
import net.mcreator.itacraft.client.model.ModelGamerGirl;

public class GamerGirlRenderer extends MobRenderer<GamerGirlEntity, LivingEntityRenderState, ModelGamerGirl> {
	private GamerGirlEntity entity = null;

	public GamerGirlRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelGamerGirl(context.bakeLayer(ModelGamerGirl.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	public void extractRenderState(GamerGirlEntity entity, LivingEntityRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		this.entity = entity;
	}

	@Override
	public ResourceLocation getTextureLocation(LivingEntityRenderState state) {
		return ResourceLocation.parse("itacraft:textures/entities/gamergirl.png");
	}
}