package net.mcreator.itacraft.recipe.brewing;

import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.common.brewing.IBrewingRecipe;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;

import net.mcreator.itacraft.init.ItacraftModPotions;
import net.mcreator.itacraft.init.ItacraftModItems;

@EventBusSubscriber
public class RussiaPotionRecipeBrewingRecipe implements IBrewingRecipe {
	@SubscribeEvent
	public static void init(RegisterBrewingRecipesEvent event) {
		event.getBuilder().addRecipe(new RussiaPotionRecipeBrewingRecipe());
	}

	@Override
	public boolean isInput(ItemStack input) {
		return Ingredient.of(Items.GLASS_BOTTLE).test(input);
	}

	@Override
	public boolean isIngredient(ItemStack ingredient) {
		return Ingredient.of(ItacraftModItems.COMMUNISM.get()).test(ingredient);
	}

	@Override
	public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
		if (isInput(input) && isIngredient(ingredient)) {
			return PotionContents.createItemStack(Items.POTION, ItacraftModPotions.RUSSIA_POTION);
		}
		return ItemStack.EMPTY;
	}
}