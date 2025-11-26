package net.mcreator.itacraft.item;

import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;

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