package net.mcreator.itacraft.procedures;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.nbt.CompoundTag;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SkattemanOnInitialEntitySpawnProcedure {
    
    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
        if (entity == null || world == null || !(world instanceof ServerLevel _level))
            return;
        
        // Find nearest player
        List<Player> players = _level.players().stream()
            .map(p -> (Player) p)
            .collect(Collectors.toList());
            
        if (players.isEmpty()) {
            entity.discard();
            return;
        }
        
        final Player nearestPlayer = players.stream()
            .min(Comparator.comparingDouble(p -> p.distanceToSqr(x, y, z)))
            .orElse(null);
        
        if (nearestPlayer == null) {
            entity.discard();
            return;
        }
        
        // Send warning message
        nearestPlayer.displayClientMessage(Component.literal("§c§lSKATTEETATEN: You have 120 seconds to pay your taxes or face consequences!"), false);
        
        // Initialize timer in entity's NBT data
        CompoundTag entityData = entity.getPersistentData();
        entityData.putInt("taxTimer", 0);
        entityData.putString("targetPlayer", nearestPlayer.getStringUUID());
        entityData.putBoolean("timerActive", true);
    }
    
    // Call this procedure from an "On Entity Tick Update" trigger
    public static void onEntityTick(LevelAccessor world, Entity entity) {
        if (entity == null || world == null) return;
        
        CompoundTag entityData = entity.getPersistentData();
        
        // Check if timer is active - use Optional methods properly
        if (entityData.contains("timerActive") && entityData.getBoolean("timerActive").orElse(false)) {
            int currentTimer = entityData.getInt("taxTimer").orElse(0);
            
            // Increment timer by 1 each tick
            entityData.putInt("taxTimer", currentTimer + 1);
            
            // Check if 120 seconds (2400 ticks) have passed
            if (currentTimer >= 2400) {
                executePenalty(world, entity, entityData);
            }
        }
    }
    
    private static void executePenalty(LevelAccessor world, Entity entity, CompoundTag entityData) {
        // Reset timer
        entityData.putBoolean("timerActive", false);
        entityData.putInt("taxTimer", 0);
        
        if (!entity.isAlive()) return;
        
        // Find the target player - use Optional methods properly
        String playerUUID = entityData.getString("targetPlayer").orElse("");
        Player targetPlayer = null;
        
        if (world instanceof ServerLevel serverLevel && !playerUUID.isEmpty()) {
            // Try to find player by name (since getPlayerByName expects username, not UUID)
            targetPlayer = findNearestPlayer(serverLevel, entity);
        } else if (world instanceof ServerLevel serverLevel) {
            targetPlayer = findNearestPlayer(serverLevel, entity);
        }
        
        if (targetPlayer != null && targetPlayer.isAlive()) {
            // Check payment status
            int paidStatus = targetPlayer.getPersistentData().getInt("payed").orElse(0);
            
            if (paidStatus != 1) {
                targetPlayer.displayClientMessage(Component.literal("§4§lSKATTEETATEN: Time's up! Seizing all assets!"), false);
                targetPlayer.getInventory().clearContent();
                
                // Cast to Level for chest clearing
                if (world instanceof Level) {
                    clearNearbyChests((Level) world, targetPlayer.blockPosition(), 20);
                }
            } else {
                targetPlayer.displayClientMessage(Component.literal("§a§lSKATTEETATEN: Payment confirmed. Have a nice day."), false);
                targetPlayer.getPersistentData().putInt("payed", 0);
            }
        }
        
        // Despawn the entity
        entity.discard();
    }
    
    private static Player findNearestPlayer(ServerLevel level, Entity entity) {
        // Fallback method to find nearest player
        List<Player> players = level.players().stream()
            .map(p -> (Player) p)
            .collect(Collectors.toList());
            
        if (players.isEmpty()) {
            return null;
        }
        
        return players.stream()
            .min(Comparator.comparingDouble(p -> p.distanceToSqr(entity.position())))
            .orElse(null);
    }
    
    private static void clearNearbyChests(Level world, BlockPos centerPos, int radius) {
        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    BlockPos pos = centerPos.offset(x, y, z);
                    BlockEntity blockEntity = world.getBlockEntity(pos);
                    if (blockEntity instanceof RandomizableContainerBlockEntity container) {
                        container.clearContent();
                    }
                }
            }
        }
    }
}