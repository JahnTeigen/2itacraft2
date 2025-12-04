/*
 * MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.itacraft.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.client.renderer.ItemBlockRenderTypes;

import net.mcreator.itacraft.fluid.OilFluid;
import net.mcreator.itacraft.fluid.BodilsSpermFluid;
import net.mcreator.itacraft.ItacraftMod;

public class ItacraftModFluids {
	public static final DeferredRegister<Fluid> REGISTRY = DeferredRegister.create(BuiltInRegistries.FLUID, ItacraftMod.MODID);
	public static final DeferredHolder<Fluid, FlowingFluid> OIL = REGISTRY.register("oil", () -> new OilFluid.Source());
	public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_OIL = REGISTRY.register("flowing_oil", () -> new OilFluid.Flowing());
	public static final DeferredHolder<Fluid, FlowingFluid> BODILS_SPERM = REGISTRY.register("bodils_sperm", () -> new BodilsSpermFluid.Source());
	public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_BODILS_SPERM = REGISTRY.register("flowing_bodils_sperm", () -> new BodilsSpermFluid.Flowing());

	@EventBusSubscriber(Dist.CLIENT)
	public static class FluidsClientSideHandler {
		@SubscribeEvent
		public static void clientSetup(FMLClientSetupEvent event) {
			ItemBlockRenderTypes.setRenderLayer(OIL.get(), ChunkSectionLayer.TRANSLUCENT);
			ItemBlockRenderTypes.setRenderLayer(FLOWING_OIL.get(), ChunkSectionLayer.TRANSLUCENT);
			ItemBlockRenderTypes.setRenderLayer(BODILS_SPERM.get(), ChunkSectionLayer.TRANSLUCENT);
			ItemBlockRenderTypes.setRenderLayer(FLOWING_BODILS_SPERM.get(), ChunkSectionLayer.TRANSLUCENT);
		}
	}
}