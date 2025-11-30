package net.mcreator.itacraft.item;

import net.minecraft.network.chat.Component;

public class OilItem extends BucketItem {

	public OilItem(Item.Properties properties) {
		super(ItacraftModFluids.OIL.get(), properties.craftRemainder(Items.BUCKET).stacksTo(1).rarity(Rarity.UNCOMMON));
	}

}