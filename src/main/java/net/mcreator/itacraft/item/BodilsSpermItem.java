package net.mcreator.itacraft.item;

import net.minecraft.network.chat.Component;

public class BodilsSpermItem extends BucketItem {

	public BodilsSpermItem(Item.Properties properties) {
		super(ItacraftModFluids.BODILS_SPERM.get(), properties.craftRemainder(Items.BUCKET).stacksTo(1)

		);
	}

}