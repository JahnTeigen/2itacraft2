package net.mcreator.itacraft.item;

public class MicroplasticItem extends Item {
	public MicroplasticItem(Item.Properties properties) {
		super(properties.food((new FoodProperties.Builder()).nutrition(0).saturationModifier(0f).alwaysEdible().build(), Consumables.defaultFood().consumeSeconds(0.5F).build()));
	}
}