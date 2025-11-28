package net.mcreator.itacraft.item;

@EventBusSubscriber
public class NalMedNoeRartIItem extends Item {
	public NalMedNoeRartIItem(Item.Properties properties) {
		super(properties.rarity(Rarity.UNCOMMON).food((new FoodProperties.Builder()).nutrition(0).saturationModifier(0f).build(), Consumables.defaultFood().animation(ItemUseAnimation.CROSSBOW).consumeSeconds(1.5F).build()));
	}

	@SubscribeEvent
	public static void modifyItemComponents(ModifyDefaultComponentsEvent event) {
		event.modify(ItacraftModItems.NAL_MED_NOE_RART_I.get(), builder -> builder.set(DataComponents.USE_REMAINDER, new UseRemainder(new ItemStack(ItacraftModItems.TOM_NAL.get()))));
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Item.TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> componentConsumer, TooltipFlag flag) {
		super.appendHoverText(itemstack, context, tooltipDisplay, componentConsumer, flag);
		componentConsumer.accept(Component.translatable("item.itacraft.nal_med_noe_rart_i.description_0"));
	}

	@Override
	public boolean releaseUsing(ItemStack itemstack, Level world, LivingEntity entity, int time) {
		NalMedNoeRartIOnPlayerStoppedUsingProcedure.execute(entity);
		return super.releaseUsing(itemstack, world, entity, time);
	}
}