package net.mcreator.itacraft.item;

import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.Item;
import net.minecraft.world.food.FoodProperties;

public class FetusItem extends Item {
	public FetusItem(Item.Properties properties) {
		super(properties.rarity(Rarity.RARE).stacksTo(1).food((new FoodProperties.Builder()).nutrition(10).saturationModifier(1f).build(), Consumables.defaultFood().animation(ItemUseAnimation.NONE).consumeSeconds(0.5F).build()));
	}
}