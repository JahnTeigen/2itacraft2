package net.mcreator.itacraft.mixin;

import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.core.Holder;

import net.mcreator.itacraft.init.ItacraftModBiomes;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;

@Mixin(NoiseGeneratorSettings.class)
public class NoiseGeneratorSettingsMixin implements ItacraftModBiomes.ItacraftModNoiseGeneratorSettings {
	@Unique
	private Holder<DimensionType> itacraft_dimensionTypeReference;

	@WrapMethod(method = "surfaceRule")
	public SurfaceRules.RuleSource surfaceRule(Operation<SurfaceRules.RuleSource> original) {
		SurfaceRules.RuleSource retval = original.call();
		if (this.itacraft_dimensionTypeReference != null) {
			retval = ItacraftModBiomes.adaptSurfaceRule(retval, this.itacraft_dimensionTypeReference);
		}
		return retval;
	}

	@Override
	public void setitacraftDimensionTypeReference(Holder<DimensionType> dimensionType) {
		this.itacraft_dimensionTypeReference = dimensionType;
	}
}