package net.mcreator.itacraft.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BucketItem;

import net.mcreator.itacraft.init.ItacraftModFluids;

public class OilItem extends BucketItem {
	public OilItem(Item.Properties properties) {
		super(ItacraftModFluids.OIL.get(), properties.craftRemainder(Items.BUCKET).stacksTo(1).rarity(Rarity.UNCOMMON));
	}
}