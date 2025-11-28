package net.mcreator.itacraft.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlotGroup;

import net.mcreator.itacraft.procedures.CumitemPlayerFinishesUsingItemProcedure;

public class CumitemItem extends Item {
	public CumitemItem(Item.Properties properties) {
		super(properties.stacksTo(1).fireResistant().food((new FoodProperties.Builder()).nutrition(0).saturationModifier(0f).alwaysEdible().build(), Consumables.defaultDrink().consumeSeconds(0.05F).build())
				.attributes(ItemAttributeModifiers.builder().add(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ID, -0.33, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
						.add(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_ID, -2.4, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND).build()));
	}

	@Override
	public ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {
		ItemStack retval = super.finishUsingItem(itemstack, world, entity);
		CumitemPlayerFinishesUsingItemProcedure.execute(world, entity.getX(), entity.getY(), entity.getZ(), entity);
		return retval;
	}
}