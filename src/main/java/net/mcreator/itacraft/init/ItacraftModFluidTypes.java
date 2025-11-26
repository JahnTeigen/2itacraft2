/*
 * MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.itacraft.init;

import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.fluids.FluidType;

import net.mcreator.itacraft.fluid.types.OilFluidType;
import net.mcreator.itacraft.ItacraftMod;

public class ItacraftModFluidTypes {
	public static final DeferredRegister<FluidType> REGISTRY = DeferredRegister.create(NeoForgeRegistries.FLUID_TYPES, ItacraftMod.MODID);
	public static final DeferredHolder<FluidType, FluidType> OIL_TYPE = REGISTRY.register("oil", () -> new OilFluidType());
}