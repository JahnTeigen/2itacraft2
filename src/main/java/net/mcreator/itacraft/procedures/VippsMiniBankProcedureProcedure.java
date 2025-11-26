package net.mcreator.itacraft.procedures;

import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.component.DataComponents;

import net.mcreator.itacraft.init.ItacraftModMenus;
import net.mcreator.itacraft.init.ItacraftModItems;

public class VippsMiniBankProcedureProcedure {
    public static void execute(Entity entity) {
        if (entity == null)
            return;
        
        // Check if both slots have items
        if (getAmountInGUISlot(entity, 1) > 0 && getAmountInGUISlot(entity, 2) > 0) {
            
            // Get the items from both slots
            ItemStack moneyItem = (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof ItacraftModMenus.MenuAccessor _menu ? _menu.getSlots().get(1).getItem() : ItemStack.EMPTY);
            ItemStack phoneItem = (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof ItacraftModMenus.MenuAccessor _menu ? _menu.getSlots().get(2).getItem() : ItemStack.EMPTY);
            
            // CHECK IF SLOT 2 IS ACTUALLY A HAWKPHONE
            if (phoneItem.getItem() != ItacraftModItems.HAWK_PHONE.get()) {
                return; // Exit if it's not a HawkPhone
            }
            
            // Get the COUNT of money items (how many in the stack)
            int moneyCount = moneyItem.getCount();
            
            // Determine the value of ONE money item
            double moneyValuePerItem = 0;
            
            // Check which money item it is and set the value
            if (moneyItem.getItem() == ItacraftModItems.ETT_ORE.get()) {
                moneyValuePerItem = 0.01;
            } else if (moneyItem.getItem() == ItacraftModItems.TI_ORE.get()) {
                moneyValuePerItem = 0.10;
            } else if (moneyItem.getItem() == ItacraftModItems.FEM_KRONER.get()) {
                moneyValuePerItem = 5;
            } else if (moneyItem.getItem() == ItacraftModItems.TI_KRONER.get()) {
                moneyValuePerItem = 10;
            } else if (moneyItem.getItem() == ItacraftModItems.TJUE_KRONER.get()) {
                moneyValuePerItem = 20;
            } else if (moneyItem.getItem() == ItacraftModItems.FEMTI_KRONER.get()) {
                moneyValuePerItem = 50;
            } else if (moneyItem.getItem() == ItacraftModItems.HUNDRE_KRONER.get()) {
                moneyValuePerItem = 100;
            } else if (moneyItem.getItem() == ItacraftModItems.TO_HUNDRE_KRONER.get()) {
                moneyValuePerItem = 200;
            } else if (moneyItem.getItem() == ItacraftModItems.FEM_HUNDRE_KRONER.get()) {
                moneyValuePerItem = 500;
            } else if (moneyItem.getItem() == ItacraftModItems.ETT_TUSEN_KRONER.get()) {
                moneyValuePerItem = 1000;
            }
            
            if (moneyValuePerItem > 0) {
                // Calculate TOTAL money value (count × value per item)
                double totalMoneyValue = moneyCount * moneyValuePerItem;
                
                // Get current money value from phone (default to 0 if tag doesn't exist)
                double currentMoney = phoneItem.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("money").orElse(0.0);
                
                // Calculate new money value
                double newMoney = currentMoney + totalMoneyValue;
                
                // Update the phone's money tag
                {
                    final String _tagName = "money";
                    final double _tagValue = newMoney;
                    CustomData.update(DataComponents.CUSTOM_DATA, phoneItem, tag -> tag.putDouble(_tagName, _tagValue));
                }
                
                // Remove ALL the money items from slot 1
                if (entity instanceof Player _player && _player.containerMenu instanceof ItacraftModMenus.MenuAccessor _menu) {
                    _menu.getSlots().get(1).set(ItemStack.EMPTY);
                }
            }
        }
    }

    private static int getAmountInGUISlot(Entity entity, int sltid) {
        if (entity instanceof Player player && player.containerMenu instanceof ItacraftModMenus.MenuAccessor menuAccessor) {
            ItemStack stack = menuAccessor.getSlots().get(sltid).getItem();
            if (stack != null)
                return stack.getCount();
        }
        return 0;
    }
}