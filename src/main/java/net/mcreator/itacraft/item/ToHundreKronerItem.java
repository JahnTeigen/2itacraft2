package net.mcreator.itacraft.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class ToHundreKronerItem extends Item {
	public ToHundreKronerItem(Item.Properties properties) {
		super(properties.rarity(Rarity.UNCOMMON));
	}
}