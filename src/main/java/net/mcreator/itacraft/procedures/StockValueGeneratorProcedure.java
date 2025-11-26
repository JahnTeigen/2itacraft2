package net.mcreator.itacraft.procedures;

import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;

public class StockValueGeneratorProcedure {
	public static String execute() {
		double stockValue = 0;
		stockValue = Mth.nextDouble(RandomSource.create(), 1, 1500);
		return new java.text.DecimalFormat("##.##").format(stockValue);
	}
}