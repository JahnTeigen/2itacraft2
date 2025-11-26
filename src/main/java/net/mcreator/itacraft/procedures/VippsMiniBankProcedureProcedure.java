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
        
        // DEPOSIT: If slot 1 has money items, deposit them into the phone
        if (getAmountInGUISlot(entity, 1) > 0) {
            depositMoney(entity, phoneItem);
        }
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