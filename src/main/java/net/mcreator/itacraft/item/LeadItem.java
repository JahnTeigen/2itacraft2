package net.mcreator.itacraft.item;

import net.minecraft.world.item.Item;

public class LeadItem extends Item {
	public LeadItem(Item.Properties properties) {
		super(properties.fireResistant());
	}
}