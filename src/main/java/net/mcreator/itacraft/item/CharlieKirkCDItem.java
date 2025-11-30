package net.mcreator.itacraft.item;

public class CharlieKirkCDItem extends Item {
	public CharlieKirkCDItem(Item.Properties properties) {
		super(properties.rarity(Rarity.UNCOMMON).stacksTo(1).jukeboxPlayable(ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(ItacraftMod.MODID, "charlie_kirk_cd"))));
	}
}