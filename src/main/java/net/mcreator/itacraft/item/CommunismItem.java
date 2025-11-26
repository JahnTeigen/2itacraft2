package net.mcreator.itacraft.item;

public class CommunismItem extends Item {
	private static final ToolMaterial TOOL_MATERIAL = new ToolMaterial(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 670, 100f, 0, 10, TagKey.create(Registries.ITEM, ResourceLocation.parse("itacraft:communism_repair_items")));

	public CommunismItem(Item.Properties properties) {
		super(properties.pickaxe(TOOL_MATERIAL, 9f, 11f).fireResistant().setNoCombineRepair());
	}

	@Override
	public ItemStack getCraftingRemainder(ItemStack itemstack) {
		return new ItemStack(this);
	}
}