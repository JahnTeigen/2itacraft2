package net.mcreator.itacraft.item;

public class RadiatedCumItem extends Item {
	public RadiatedCumItem(Item.Properties properties) {
		super(properties.food((new FoodProperties.Builder()).nutrition(4).saturationModifier(0.3f).alwaysEdible().build()));
	}

	@Override
	public boolean isFoil(ItemStack itemstack) {
		return true;
	}

	@Override
	public ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {
		ItemStack retval = super.finishUsingItem(itemstack, world, entity);
		RadiatedCumPlayerFinishesUsingItemProcedure.execute(entity);
		return retval;
	}
}