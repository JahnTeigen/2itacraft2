package net.mcreator.itacraft.item;

public class FetusItem extends Item {
	public FetusItem(Item.Properties properties) {
		super(properties.food((new FoodProperties.Builder()).nutrition(10).saturationModifier(1f).build(), Consumables.defaultFood().animation(ItemUseAnimation.NONE).consumeSeconds(0F).build()));
	}
}