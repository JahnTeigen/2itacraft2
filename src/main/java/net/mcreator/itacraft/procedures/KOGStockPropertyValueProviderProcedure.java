package net.mcreator.itacraft.procedures;

import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;

public class KOGStockPropertyValueProviderProcedure {
	public static double execute() {
		return Mth.nextInt(RandomSource.create(), 240, 255);
	}
}