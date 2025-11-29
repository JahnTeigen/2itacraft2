package net.mcreator.itacraft.item;

public class FetusItem extends Item {
	public FetusItem(Item.Properties properties) {
		super(properties.rarity(Rarity.RARE).stacksTo(1).food((new FoodProperties.Builder()).nutrition(8).saturationModifier(0.5f).build(), Consumables.defaultFood().consumeSeconds(0.5F).build()));
	}
}