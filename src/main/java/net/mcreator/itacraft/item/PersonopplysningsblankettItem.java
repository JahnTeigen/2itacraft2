package net.mcreator.itacraft.item;

public class PersonopplysningsblankettItem extends Item {
	public PersonopplysningsblankettItem(Item.Properties properties) {
		super(properties.rarity(Rarity.RARE).stacksTo(1).fireResistant()
				.attributes(ItemAttributeModifiers.builder().add(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ID, 9998, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
						.add(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_ID, -2.4, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND).build())
				.enchantable(15));
	}

	@Override
	public float getDestroySpeed(ItemStack itemstack, BlockState state) {
		return 0.1f;
	}

	@Override
	public boolean isFoil(ItemStack itemstack) {
		return true;
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		super.useOn(context);
		return KongsbergDimensionIgniterProcedure.execute(context.getLevel(), context.getClickedPos().getX(), context.getClickedPos().getY(), context.getClickedPos().getZ(), context.getClickedFace());
	}
}