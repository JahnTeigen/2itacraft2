package net.mcreator.itacraft.item;

public class DildoItem extends Item {
	private static final ToolMaterial TOOL_MATERIAL = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 100, 7f, 0, 2, TagKey.create(Registries.ITEM, ResourceLocation.parse("itacraft:dildo_repair_items")));

	public DildoItem(Item.Properties properties) {
		super(properties.sword(TOOL_MATERIAL, 3f, -3f));
	}
}