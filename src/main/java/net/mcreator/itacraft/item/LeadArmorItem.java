package net.mcreator.itacraft.item;

import java.util.Map;

public abstract class LeadArmorItem extends Item {

	public static ArmorMaterial ARMOR_MATERIAL = new ArmorMaterial(30, Map.of(ArmorType.BOOTS, 2, ArmorType.LEGGINGS, 3, ArmorType.CHESTPLATE, 4, ArmorType.HELMET, 2, ArmorType.BODY, 4), 9,
			BuiltInRegistries.SOUND_EVENT.wrapAsHolder(SoundEvents.EMPTY), 0f, 1f, TagKey.create(Registries.ITEM, ResourceLocation.parse("itacraft:lead_armor_repair_items")),
			ResourceKey.create(EquipmentAssets.ROOT_ID, ResourceLocation.parse("itacraft:lead_armor")));

	private LeadArmorItem(Item.Properties properties) {
		super(properties);
	}

	public static class Chestplate extends LeadArmorItem {

		public Chestplate(Item.Properties properties) {
			super(properties.humanoidArmor(ARMOR_MATERIAL, ArmorType.CHESTPLATE));
		}

		@Override
		public void inventoryTick(ItemStack itemstack, ServerLevel world, Entity entity, @Nullable EquipmentSlot equipmentSlot) {
			super.inventoryTick(itemstack, world, entity, equipmentSlot);
			if (entity instanceof Player player && (equipmentSlot != null && equipmentSlot.getType() == EquipmentSlot.Type.HUMANOID_ARMOR)) {
				LeadArmorChestplateTickEventProcedure.execute(entity);
			}
		}
	}

	public static class Leggings extends LeadArmorItem {

		public Leggings(Item.Properties properties) {
			super(properties.humanoidArmor(ARMOR_MATERIAL, ArmorType.LEGGINGS));
		}

		@Override
		public void inventoryTick(ItemStack itemstack, ServerLevel world, Entity entity, @Nullable EquipmentSlot equipmentSlot) {
			super.inventoryTick(itemstack, world, entity, equipmentSlot);
			if (entity instanceof Player player && (equipmentSlot != null && equipmentSlot.getType() == EquipmentSlot.Type.HUMANOID_ARMOR)) {
				LeadArmorLeggingsTickEventProcedure.execute(entity);
			}
		}
	}

}