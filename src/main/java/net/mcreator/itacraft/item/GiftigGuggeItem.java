package net.mcreator.itacraft.item;

public class GiftigGuggeItem extends Item {
	public GiftigGuggeItem(Item.Properties properties) {
		super(properties.rarity(Rarity.UNCOMMON).stacksTo(16).fireResistant().food((new FoodProperties.Builder()).nutrition(-5).saturationModifier(0.3f).alwaysEdible().build(), Consumables.defaultFood().consumeSeconds(3F).build()));
	}

	@Override
	public ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {
		ItemStack retval = super.finishUsingItem(itemstack, world, entity);
		GiftigGuggePlayerFinishesUsingItemProcedure.execute(world, entity);
		return retval;
	}
}