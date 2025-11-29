package net.mcreator.itacraft.item;

public class IronManCDItem extends Item {
	public IronManCDItem(Item.Properties properties) {
		super(properties.rarity(Rarity.UNCOMMON).stacksTo(1).jukeboxPlayable(ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(ItacraftMod.MODID, "iron_man_cd"))));
	}
}