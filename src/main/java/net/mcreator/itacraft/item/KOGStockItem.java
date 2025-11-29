package net.mcreator.itacraft.item;

public class KOGStockItem extends Item {
	public KOGStockItem(Item.Properties properties) {
		super(properties.rarity(Rarity.UNCOMMON).stacksTo(1).fireResistant()
				.attributes(ItemAttributeModifiers.builder().add(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ID, 66, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
						.add(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_ID, -2.4, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND).build()));
	}

	@Override
	public ItemUseAnimation getUseAnimation(ItemStack itemstack) {
		return ItemUseAnimation.BLOCK;
	}

	@Override
	public boolean isFoil(ItemStack itemstack) {
		return true;
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Item.TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> componentConsumer, TooltipFlag flag) {
		super.appendHoverText(itemstack, context, tooltipDisplay, componentConsumer, flag);
		Entity entity = itemstack.getEntityRepresentation() != null ? itemstack.getEntityRepresentation() : ItacraftMod.clientPlayer();
		String hoverText = StockValueGeneratorProcedure.execute();
		if (hoverText != null) {
			for (String line : hoverText.split("\n")) {
				componentConsumer.accept(Component.literal(line));
			}
		}
	}

	public record PriceProperty() implements RangeSelectItemModelProperty {
		public static final MapCodec<PriceProperty> MAP_CODEC = MapCodec.unit(new PriceProperty());

		@Override
		public float get(ItemStack itemStackToRender, @Nullable ClientLevel clientWorld, @Nullable LivingEntity entity, int seed) {
			return (float) KOGStockPropertyValueProviderProcedure.execute();
		}

		@Override
		public MapCodec<PriceProperty> type() {
			return MAP_CODEC;
		}
	}
}