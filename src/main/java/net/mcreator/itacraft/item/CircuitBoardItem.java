package net.mcreator.itacraft.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class CircuitBoardItem extends Item {
	public CircuitBoardItem(Item.Properties properties) {
		super(properties.rarity(Rarity.UNCOMMON));
	}
}