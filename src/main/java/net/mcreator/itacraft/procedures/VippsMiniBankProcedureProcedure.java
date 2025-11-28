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
        
        // CHECK IF SLOT 2 HAS A HAWKPHONE
        ItemStack phoneItem = (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof ItacraftModMenus.MenuAccessor _menu ? _menu.getSlots().get(2).getItem() : ItemStack.EMPTY);
        
        if (phoneItem.getItem() != ItacraftModItems.HAWK_PHONE.get()) {
            return; // Exit if it's not a HawkPhone
        }
        
        // CHECK IF SLOT 1 IS EMPTY
        if (getAmountInGUISlot(entity, 1) == 0) {
            // WITHDRAW: Slot 1 is empty, withdraw money from phone (one stack at a time)
            withdrawMoney(entity, phoneItem);
        } else {
            // DEPOSIT: If slot 1 has money items, deposit them into the phone
            depositMoney(entity, phoneItem);
        }
    }
    
    // WITHDRAW MONEY FROM PHONE (ONE STACK AT A TIME)
    private static void withdrawMoney(Entity entity, ItemStack phoneItem) {
        double currentMoney = phoneItem.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("money").orElse(0.0);
        
        if (currentMoney <= 0) {
            return; // No money to withdraw
        }
        
        // Find the best denomination that fits
        ItemStack cashStack = getBestDenominationStack(currentMoney);
        
        if (!cashStack.isEmpty()) {
            double withdrawnValue = getMoneyValue(cashStack) * cashStack.getCount();
            double remainingMoney = currentMoney - withdrawnValue;
            
            if (entity instanceof Player _player && _player.containerMenu instanceof ItacraftModMenus.MenuAccessor _menu) {
                // Place cash in slot 1
                _menu.getSlots().get(1).set(cashStack);
                
                // Update phone's money (subtract what was withdrawn)
                CustomData.update(DataComponents.CUSTOM_DATA, phoneItem, tag -> tag.putDouble("money", remainingMoney));
            }
        }
    }
    
    // Get the best denomination stack (up to max stack size)
    private static ItemStack getBestDenominationStack(double money) {
        // Try each denomination from largest to smallest
        if (money >= 1000) {
            int count = Math.min(16, (int)(money / 1000)); // ETT_TUSEN max 16
            return new ItemStack(ItacraftModItems.ETT_TUSEN_KRONER.get(), count);
        }
        if (money >= 500) {
            int count = Math.min(64, (int)(money / 500));
            return new ItemStack(ItacraftModItems.FEM_HUNDRE_KRONER.get(), count);
        }
        if (money >= 200) {
            int count = Math.min(64, (int)(money / 200));
            return new ItemStack(ItacraftModItems.TO_HUNDRE_KRONER.get(), count);
        }
        if (money >= 100) {
            int count = Math.min(64, (int)(money / 100));
            return new ItemStack(ItacraftModItems.HUNDRE_KRONER.get(), count);
        }
        if (money >= 50) {
            int count = Math.min(64, (int)(money / 50));
            return new ItemStack(ItacraftModItems.FEMTI_KRONER.get(), count);
        }
        if (money >= 20) {
            int count = Math.min(64, (int)(money / 20));
            return new ItemStack(ItacraftModItems.TJUE_KRONER.get(), count);
        }
        if (money >= 10) {
            int count = Math.min(64, (int)(money / 10));
            return new ItemStack(ItacraftModItems.TI_KRONER.get(), count);
        }
        if (money >= 5) {
            int count = Math.min(64, (int)(money / 5));
            return new ItemStack(ItacraftModItems.FEM_KRONER.get(), count);
        }
        if (money >= 1) {
            int count = Math.min(64, (int)(money / 1));
            return new ItemStack(ItacraftModItems.EN_KRONE.get(), count);
        }
        if (money >= 0.10) {
            int count = Math.min(64, (int)(money / 0.10));
            return new ItemStack(ItacraftModItems.TI_ORE.get(), count);
        }
        if (money >= 0.01) {
            int count = Math.min(64, (int)(money / 0.01));
            return new ItemStack(ItacraftModItems.ETT_ORE.get(), count);
        }
        
        return ItemStack.EMPTY;
    }
    
    // DEPOSIT MONEY INTO PHONE
    private static void depositMoney(Entity entity, ItemStack phoneItem) {
        ItemStack moneyItem = (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof ItacraftModMenus.MenuAccessor _menu ? _menu.getSlots().get(1).getItem() : ItemStack.EMPTY);
        
        int moneyCount = moneyItem.getCount();
        double moneyValuePerItem = getMoneyValue(moneyItem);
        
        if (moneyValuePerItem > 0) {
            double totalMoneyValue = moneyCount * moneyValuePerItem;
            double currentMoney = phoneItem.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("money").orElse(0.0);
            double newMoney = currentMoney + totalMoneyValue;
            
            // Update phone's money tag
            CustomData.update(DataComponents.CUSTOM_DATA, phoneItem, tag -> tag.putDouble("money", newMoney));
            
            // Remove money items from slot 1
            if (entity instanceof Player _player && _player.containerMenu instanceof ItacraftModMenus.MenuAccessor _menu) {
                _menu.getSlots().get(1).set(ItemStack.EMPTY);
            }
        }
    }
    
    // Helper method to get money value
    private static double getMoneyValue(ItemStack moneyItem) {
        if (moneyItem.getItem() == ItacraftModItems.ETT_ORE.get()) return 0.01;
        if (moneyItem.getItem() == ItacraftModItems.TI_ORE.get()) return 0.10;
        if (moneyItem.getItem() == ItacraftModItems.EN_KRONE.get()) return 1;
        if (moneyItem.getItem() == ItacraftModItems.FEM_KRONER.get()) return 5;
        if (moneyItem.getItem() == ItacraftModItems.TI_KRONER.get()) return 10;
        if (moneyItem.getItem() == ItacraftModItems.TJUE_KRONER.get()) return 20;
        if (moneyItem.getItem() == ItacraftModItems.FEMTI_KRONER.get()) return 50;
        if (moneyItem.getItem() == ItacraftModItems.HUNDRE_KRONER.get()) return 100;
        if (moneyItem.getItem() == ItacraftModItems.TO_HUNDRE_KRONER.get()) return 200;
        if (moneyItem.getItem() == ItacraftModItems.FEM_HUNDRE_KRONER.get()) return 500;
        if (moneyItem.getItem() == ItacraftModItems.ETT_TUSEN_KRONER.get()) return 1000;
        return 0;
    }
    
    // Helper method to get amount in GUI slot
    private static int getAmountInGUISlot(Entity entity, int sltid) {
        if (entity instanceof Player player && player.containerMenu instanceof ItacraftModMenus.MenuAccessor menuAccessor) {
            ItemStack stack = menuAccessor.getSlots().get(sltid).getItem();
            if (stack != null)
                return stack.getCount();
        }
        return 0;
    }
}