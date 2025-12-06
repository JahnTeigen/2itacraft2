package net.mcreator.itacraft.item;

public class FemtiKronerItem extends Item {
	public FemtiKronerItem(Item.Properties properties) {
		super(properties.food((new FoodProperties.Builder()).nutrition(1).saturationModifier(0.1f).build()));
	}

	@Override
	public boolean isPiglinCurrency(ItemStack stack) {
		return true;
	}

	@Override
	public float getDestroySpeed(ItemStack itemstack, BlockState state) {
		return 0.1f;
	}
}