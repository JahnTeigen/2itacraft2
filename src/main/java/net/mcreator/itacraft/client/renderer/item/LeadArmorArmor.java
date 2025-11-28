package net.mcreator.itacraft.client.renderer.item;

import net.minecraft.client.model.Model;

@EventBusSubscriber(Dist.CLIENT)
public class LeadArmorArmor {

	@SubscribeEvent
	public static void registerItemExtensions(RegisterClientExtensionsEvent event) {

		event.registerItem(new IClientItemExtensions() {

			@Override
			public ResourceLocation getArmorTexture(ItemStack stack, EquipmentClientInfo.LayerType type, EquipmentClientInfo.Layer layer, ResourceLocation _default) {
				return ResourceLocation.parse("itacraft:textures/models/armor/lead_armour_layer_1.png");
			}
		}, ItacraftModItems.LEAD_ARMOR_CHESTPLATE.get());

		event.registerItem(new IClientItemExtensions() {

			@Override
			public ResourceLocation getArmorTexture(ItemStack stack, EquipmentClientInfo.LayerType type, EquipmentClientInfo.Layer layer, ResourceLocation _default) {
				return ResourceLocation.parse("itacraft:textures/models/armor/lead_armour_layer_2.png");
			}
		}, ItacraftModItems.LEAD_ARMOR_LEGGINGS.get());

	}

}