package net.mcreator.itacraft.item;

public class DeftonesCDItem extends Item {
	public DeftonesCDItem(Item.Properties properties) {
		super(properties.rarity(Rarity.UNCOMMON).stacksTo(1).jukeboxPlayable(ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(ItacraftMod.MODID, "deftones_cd"))));
	}
}