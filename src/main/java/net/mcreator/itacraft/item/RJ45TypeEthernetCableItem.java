package net.mcreator.itacraft.item;

public class RJ45TypeEthernetCableItem extends Item {
	public RJ45TypeEthernetCableItem(Item.Properties properties) {
		super(properties.rarity(Rarity.UNCOMMON));
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Item.TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> componentConsumer, TooltipFlag flag) {
		super.appendHoverText(itemstack, context, tooltipDisplay, componentConsumer, flag);
		componentConsumer.accept(Component.translatable("item.itacraft.rj_45_type_ethernet_cable.description_0"));
	}
}