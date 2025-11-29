package net.mcreator.itacraft.item;

public class HeroinItem extends Item {
	public HeroinItem(Item.Properties properties) {
		super(properties.rarity(Rarity.UNCOMMON).food((new FoodProperties.Builder()).nutrition(-2).saturationModifier(-0.6f).build()));
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Item.TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> componentConsumer, TooltipFlag flag) {
		super.appendHoverText(itemstack, context, tooltipDisplay, componentConsumer, flag);
		componentConsumer.accept(Component.translatable("item.itacraft.heroin.description_0"));
		componentConsumer.accept(Component.translatable("item.itacraft.heroin.description_1"));
		componentConsumer.accept(Component.translatable("item.itacraft.heroin.description_2"));
	}

	@Override
	public ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {
		ItemStack retval = super.finishUsingItem(itemstack, world, entity);
		HeroinPlayerFinishesUsingItemProcedure.execute(entity);
		return retval;
	}
}