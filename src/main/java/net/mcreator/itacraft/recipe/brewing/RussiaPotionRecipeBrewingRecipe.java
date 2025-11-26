package net.mcreator.itacraft.recipe.brewing;

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
			return PotionContents.createItemStack(Items.POTION, ItacraftModPotions.DELETED_MOD_ELEMENT);
		}
		return ItemStack.EMPTY;
	}

}