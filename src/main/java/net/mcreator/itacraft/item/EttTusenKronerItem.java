package net.mcreator.itacraft.item;

public class EttTusenKronerItem extends Item {
	public EttTusenKronerItem(Item.Properties properties) {
		super(properties.rarity(Rarity.EPIC).stacksTo(16).food((new FoodProperties.Builder()).nutrition(1).saturationModifier(0.1f).build()));
	}

	@Override
	public boolean isPiglinCurrency(ItemStack stack) {
		return true;
	}

	@Override
	public float getDestroySpeed(ItemStack itemstack, BlockState state) {
		return 0.3f;
	}
}