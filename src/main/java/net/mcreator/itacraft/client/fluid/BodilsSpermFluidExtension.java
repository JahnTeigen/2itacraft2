package net.mcreator.itacraft.client.fluid;

import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.resources.ResourceLocation;

import net.mcreator.itacraft.init.ItacraftModFluidTypes;

@EventBusSubscriber(Dist.CLIENT)
public class BodilsSpermFluidExtension {
	@SubscribeEvent
	public static void registerFluidTypeExtensions(RegisterClientExtensionsEvent event) {
		event.registerFluidType(new IClientFluidTypeExtensions() {
			private static final ResourceLocation STILL_TEXTURE = ResourceLocation.parse("itacraft:block/bodilspermstill");
			private static final ResourceLocation FLOWING_TEXTURE = ResourceLocation.parse("itacraft:block/bodilsspermflowing");

			@Override
			public ResourceLocation getStillTexture() {
				return STILL_TEXTURE;
			}

			@Override
			public ResourceLocation getFlowingTexture() {
				return FLOWING_TEXTURE;
			}
		}, ItacraftModFluidTypes.BODILS_SPERM_TYPE.get());
	}
}