package net.mcreator.itacraft.procedures;

import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.component.DataComponents;

import net.mcreator.itacraft.init.ItacraftModMenus;
import net.mcreator.itacraft.init.ItacraftModItems;

public class UpdateSliderMaxProcedure {
    public static double execute(Entity entity) {
        if (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof ItacraftModMenus.MenuAccessor _menu) {
            ItemStack phoneItem = _menu.getSlots().get(2).getItem();
            if (phoneItem.getItem() == ItacraftModItems.HAWK_PHONE.get()) {
                return phoneItem.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("money").orElse(0.0);
            }
        }
        return 0;
    }
}