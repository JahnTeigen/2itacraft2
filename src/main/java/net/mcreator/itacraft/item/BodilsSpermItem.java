package net.mcreator.itacraft.item;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BucketItem;

import net.mcreator.itacraft.init.ItacraftModFluids;

public class BodilsSpermItem extends BucketItem {
	public BodilsSpermItem(Item.Properties properties) {
		super(ItacraftModFluids.BODILS_SPERM.get(), properties.craftRemainder(Items.BUCKET).stacksTo(1)

		);
	}
}