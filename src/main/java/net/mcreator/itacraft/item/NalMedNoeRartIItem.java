package net.mcreator.itacraft.item;

public class NalMedNoeRartIItem extends Item {
	public NalMedNoeRartIItem(Item.Properties properties) {
		super(properties.rarity(Rarity.UNCOMMON));
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Item.TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> componentConsumer, TooltipFlag flag) {
		super.appendHoverText(itemstack, context, tooltipDisplay, componentConsumer, flag);
		componentConsumer.accept(Component.translatable("item.itacraft.nal_med_noe_rart_i.description_0"));
	}
}