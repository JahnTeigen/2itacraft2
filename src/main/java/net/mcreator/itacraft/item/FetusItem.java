package net.mcreator.itacraft.item;

import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;
import net.minecraft.world.food.FoodProperties;

public class FetusItem extends Item {
	public FetusItem(Item.Properties properties) {
		super(properties.rarity(Rarity.RARE).stacksTo(1).food((new FoodProperties.Builder()).nutrition(8).saturationModifier(0.5f).build(), Consumables.defaultFood().consumeSeconds(0.5F).build()));
	}
}