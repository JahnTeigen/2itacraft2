package net.mcreator.itacraft.client.model;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.EntityModel;

// Made with Blockbench 5.0.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
public class Modelhoming_missle extends EntityModel<LivingEntityRenderState> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("itacraft", "modelhoming_missle"), "main");
	public final ModelPart bb_main;

	public Modelhoming_missle(ModelPart root) {
		super(root);
		this.bb_main = root.getChild("bb_main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main",
				CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -14.0F, -8.0F, 8.0F, 10.0F, 9.0F, new CubeDeformation(0.0F)).texOffs(34, 19).addBox(-11.0F, -13.0F, -7.0F, 8.0F, 8.0F, 7.0F, new CubeDeformation(0.0F)).texOffs(32, 49)
						.addBox(-14.0F, -12.0F, -6.0F, 8.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(0, 60).addBox(-16.0F, -10.0F, -5.0F, 8.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(0, 19)
						.addBox(1.0F, -14.0F, -8.0F, 8.0F, 10.0F, 9.0F, new CubeDeformation(0.0F)).texOffs(34, 0).addBox(9.0F, -14.0F, -8.0F, 8.0F, 10.0F, 9.0F, new CubeDeformation(0.0F)).texOffs(58, 55)
						.addBox(17.0F, -13.0F, -8.0F, 8.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 38).addBox(17.0F, -14.0F, -8.0F, 8.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)).texOffs(0, 48)
						.addBox(17.0F, -5.0F, -7.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(22, 60).addBox(17.0F, -13.0F, 0.0F, 8.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 34)
						.addBox(18.0F, -13.0F, -7.0F, 8.0F, 8.0F, 7.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 24.0F, 0.0F));
		PartDefinition cube_r1 = bb_main.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(58, 52).addBox(-8.0F, -11.0F, -12.0F, 15.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(24.0F, 2.0F, 0.0F, 1.5572F, -0.6286F, -3.1063F));
		PartDefinition cube_r2 = bb_main.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(58, 49).addBox(-8.0F, -11.0F, -12.0F, 15.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(25.0F, 2.0F, -6.0F, -1.6268F, -0.7679F, -0.0043F));
		PartDefinition cube_r3 = bb_main.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 57).addBox(-8.0F, -11.0F, -12.0F, 15.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(23.0F, -6.0F, 8.0F, 0.0F, 0.0F, -0.4363F));
		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	public void setupAnim(LivingEntityRenderState state) {
		float limbSwing = state.walkAnimationPos;
		float limbSwingAmount = state.walkAnimationSpeed;
		float ageInTicks = state.ageInTicks;
		float netHeadYaw = state.yRot;
		float headPitch = state.xRot;

	}
}