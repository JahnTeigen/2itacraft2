package net.mcreator.itacraft.procedures;

import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;

import net.mcreator.itacraft.init.ItacraftModMenus;
import net.mcreator.itacraft.init.ItacraftModItems;

public class SkatteetatenBruhProcedure {
    public static void execute(Entity entity) {
        if (entity == null)
            return;
        
        // Get the phone from GUI slot 1
        ItemStack phoneItem = (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof ItacraftModMenus.MenuAccessor _menu ? _menu.getSlots().get(1).getItem() : ItemStack.EMPTY);
        
        // Check if it's a Hawk Phone
        if (phoneItem.getItem() == ItacraftModItems.HAWK_PHONE.get()) {
            // Get the money from the ACTUAL phone item in the slot
            double currentMoney = phoneItem.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("money").orElse(0.0);
            
            if (currentMoney >= 1500) {
                // Take 50% of the money from the phone
                double newMoney = currentMoney * 0.5; // 50% remaining
                CustomData.update(DataComponents.CUSTOM_DATA, phoneItem, tag -> tag.putDouble("money", newMoney));
                
                // Mark player as PAID
                entity.getPersistentData().putInt("payed", 1);
                
                if (entity instanceof Player _player) {
                    _player.displayClientMessage(Component.literal("§aTax paid! You're safe."), true);
                }
            } else {
                // Take 50% of random items from player's inventory
                removeRandomItemsFromInventory(entity);
                
                if (entity instanceof Player _player) {
                    _player.displayClientMessage(Component.literal("§cNot enough money! Random items taken as penalty."), true);
                }
            }
        }
    }
    
    // Remove 50% of random items from player's inventory
    private static void removeRandomItemsFromInventory(Entity entity) {
        if (entity instanceof Player _player) {
            for (int i = 0; i < _player.getInventory().getContainerSize(); i++) {
                ItemStack stack = _player.getInventory().getItem(i);
                if (!stack.isEmpty()) {
                    // 50% chance to remove this stack
                    if (Math.random() < 0.5) {
                        _player.getInventory().removeItem(i, stack.getCount());
                    }
                }
            }
        }
    }
}