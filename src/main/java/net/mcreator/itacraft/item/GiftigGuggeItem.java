package net.mcreator.itacraft.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;

import net.mcreator.itacraft.procedures.GiftigGuggeRightclickedProcedure;
import net.mcreator.itacraft.procedures.GiftigGuggePlayerFinishesUsingItemProcedure;

public class GiftigGuggeItem extends Item {
	public GiftigGuggeItem(Item.Properties properties) {
		super(properties.rarity(Rarity.UNCOMMON).stacksTo(16).fireResistant().food((new FoodProperties.Builder()).nutrition(-5).saturationModifier(0.3f).alwaysEdible().build(), Consumables.defaultFood().consumeSeconds(3F).build()));
	}

	@Override
	public InteractionResult use(Level world, Player entity, InteractionHand hand) {
		InteractionResult ar = super.use(world, entity, hand);
		GiftigGuggeRightclickedProcedure.execute();
		return ar;
	}

	@Override
	public ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {
		ItemStack retval = super.finishUsingItem(itemstack, world, entity);
		GiftigGuggePlayerFinishesUsingItemProcedure.execute(world, entity);
		return retval;
	}
}