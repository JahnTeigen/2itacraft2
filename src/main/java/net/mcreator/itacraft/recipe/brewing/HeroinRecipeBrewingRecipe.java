package net.mcreator.itacraft.recipe.brewing;

@EventBusSubscriber
public class HeroinRecipeBrewingRecipe implements IBrewingRecipe {

	@SubscribeEvent
	public static void init(RegisterBrewingRecipesEvent event) {
		event.getBuilder().addRecipe(new HeroinRecipeBrewingRecipe());
	}

	@Override
	public boolean isInput(ItemStack input) {
		return Ingredient.of(ItacraftModItems.TOM_NAL.get()).test(input);
	}

	@Override
	public boolean isIngredient(ItemStack ingredient) {
		return Ingredient.of(ItacraftModItems.HEROIN.get()).test(ingredient);
	}

	@Override
	public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
		if (isInput(input) && isIngredient(ingredient)) {
			return new ItemStack(ItacraftModItems.NAL_MED_NOE_RART_I.get());
		}
		return ItemStack.EMPTY;
	}

}