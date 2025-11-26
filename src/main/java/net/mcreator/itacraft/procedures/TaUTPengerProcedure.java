package net.mcreator.itacraft.procedures;

import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.component.DataComponents;

import net.mcreator.itacraft.init.ItacraftModMenus;
import net.mcreator.itacraft.init.ItacraftModItems;

public class TaUTPengerProcedure {
    public static void execute(Entity entity) {
        if (entity == null)
            return;
        
        // CHECK IF SLOT 2 HAS A HAWKPHONE
        ItemStack phoneItem = (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof ItacraftModMenus.MenuAccessor _menu ? _menu.getSlots().get(2).getItem() : ItemStack.EMPTY);
        
        if (phoneItem.getItem() != ItacraftModItems.HAWK_PHONE.get()) {
            return; // Exit if it's not a HawkPhone
        }
        
        // Get current money from phone
        double currentMoney = phoneItem.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("money").orElse(0.0);
        
        if (currentMoney <= 0) {
            return; // No money to withdraw
        }
        
        // Withdraw all money
        withdrawAllMoney(entity, phoneItem, currentMoney);
    }
    
    // WITHDRAW ALL MONEY FROM PHONE
    private static void withdrawAllMoney(Entity entity, ItemStack phoneItem, double amount) {
        // Calculate which bills/coins to give
        int remaining = (int) amount;
        double decimalRemaining = amount - remaining;
        
        // Start with largest denominations
        int tusen = remaining / 1000;
        remaining %= 1000;
        
        int femhundre = remaining / 500;
        remaining %= 500;
        
        int tohundre = remaining / 200;
        remaining %= 200;
        
        int hundre = remaining / 100;
        remaining %= 100;
        
        int femti = remaining / 50;
        remaining %= 50;
        
        int tjue = remaining / 20;
        remaining %= 20;
        
        int ti = remaining / 10;
        remaining %= 10;
        
        int fem = remaining / 5;
        remaining %= 5;
        
        int enKrone = remaining;
        
        // Handle decimal part (øre)
        int tiOre = (int) (decimalRemaining / 0.10);
        decimalRemaining -= tiOre * 0.10;
        
        int ettOre = (int) Math.round(decimalRemaining / 0.01);
        
        // Give items to player
        if (entity instanceof Player _player) {
            if (tusen > 0) giveItem(_player, new ItemStack(ItacraftModItems.ETT_TUSEN_KRONER.get(), tusen));
            if (femhundre > 0) giveItem(_player, new ItemStack(ItacraftModItems.FEM_HUNDRE_KRONER.get(), femhundre));
            if (tohundre > 0) giveItem(_player, new ItemStack(ItacraftModItems.TO_HUNDRE_KRONER.get(), tohundre));
            if (hundre > 0) giveItem(_player, new ItemStack(ItacraftModItems.HUNDRE_KRONER.get(), hundre));
            if (femti > 0) giveItem(_player, new ItemStack(ItacraftModItems.FEMTI_KRONER.get(), femti));
            if (tjue > 0) giveItem(_player, new ItemStack(ItacraftModItems.TJUE_KRONER.get(), tjue));
            if (ti > 0) giveItem(_player, new ItemStack(ItacraftModItems.TI_KRONER.get(), ti));
            if (fem > 0) giveItem(_player, new ItemStack(ItacraftModItems.FEM_KRONER.get(), fem));
            if (enKrone > 0) giveItem(_player, new ItemStack(ItacraftModItems.EN_KRONE.get(), enKrone));
            if (tiOre > 0) giveItem(_player, new ItemStack(ItacraftModItems.TI_ORE.get(), tiOre));
            if (ettOre > 0) giveItem(_player, new ItemStack(ItacraftModItems.ETT_ORE.get(), ettOre));
        }
        
        // Set phone money to 0
        CustomData.update(DataComponents.CUSTOM_DATA, phoneItem, tag -> tag.putDouble("money", 0.0));
    }
    
    // Helper method to give item to player
    private static void giveItem(Player player, ItemStack stack) {
        if (!player.getInventory().add(stack)) {
            player.drop(stack, false);
        }
    }
}