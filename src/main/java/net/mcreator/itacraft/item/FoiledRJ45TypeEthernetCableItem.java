package net.mcreator.itacraft.item;

public class FoiledRJ45TypeEthernetCableItem extends Item {
	public FoiledRJ45TypeEthernetCableItem(Item.Properties properties) {
		super(properties.rarity(Rarity.UNCOMMON));
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Item.TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> componentConsumer, TooltipFlag flag) {
		super.appendHoverText(itemstack, context, tooltipDisplay, componentConsumer, flag);
		componentConsumer.accept(Component.translatable("item.itacraft.foiled_rj_45_type_ethernet_cable.description_0"));
	}
}