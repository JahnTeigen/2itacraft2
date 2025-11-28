package net.mcreator.itacraft.procedures;

import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.component.DataComponents;

public class GetPhoneMoneyDisplayProcedure {
    public static String execute(Entity entity) {
        if (entity == null)
            return "0.00 kr";
        
        if (entity instanceof Player _player) {
            // Get the item in the player's main hand
            ItemStack phoneItem = _player.getMainHandItem();
            
            // Get the money value from NBT
            double money = phoneItem.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY)
                .copyTag()
                .getDouble("money")
                .orElse(0.0);
            
            // Format it nicely with 2 decimal places
            return String.format("%.2f kr", money);
        }
        
        return "0.00 kr";
    }
}