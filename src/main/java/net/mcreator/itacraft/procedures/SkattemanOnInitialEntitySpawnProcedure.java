package net.mcreator.itacraft.procedures;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;

import java.util.Comparator;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class SkattemanOnInitialEntitySpawnProcedure {
    
    // Store data per entity
    private static Map<Entity, Integer> tickCounters = new HashMap<>();
    private static Map<Entity, Entity> targetPlayers = new HashMap<>();
    
    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
        if (entity == null || world == null)
            return;
        
        // Initialize if first time
        if (!tickCounters.containsKey(entity)) {
            tickCounters.put(entity, 0);
            
            // Find nearest player
            if (world instanceof Level _level) {
                List<Player> players = _level.players().stream()
                    .map(p -> (Player) p)
                    .collect(Collectors.toList());
                    
                if (!players.isEmpty()) {
                    Entity nearestPlayer = players.stream()
                        .min(Comparator.comparingDouble(p -> p.distanceToSqr(x, y, z)))
                        .orElse(null);
                    
                    if (nearestPlayer != null) {
                        targetPlayers.put(entity, nearestPlayer);
                        if (nearestPlayer instanceof Player _player) {
                            _player.displayClientMessage(Component.literal("§c§lSKATTEETATEN: You have 120 seconds to pay your taxes or face consequences!"), false);
                        }
                    }
                }
            }
        }
        
        // Get target player for this skatteman
        Entity targetPlayer = targetPlayers.get(entity);
        if (targetPlayer == null || !targetPlayer.isAlive()) {
            // Player died or left - despawn skatteman
            cleanupEntity(entity);
            entity.discard();
            return;
        }
        
        // Check if player paid (payed = 1) - FIXED: Use getInt with default value
        if (targetPlayer.getPersistentData().getInt("payed").orElse(0) == 1) { // ← CORRECT
            // Player paid - just despawn skatteman
            if (targetPlayer instanceof Player _player) {
                _player.displayClientMessage(Component.literal("§a§lSKATTEETATEN: Payment confirmed. Have a nice day."), false);
            }
            // Reset player's payment status
            targetPlayer.getPersistentData().putInt("payed", 0);
            cleanupEntity(entity);
            entity.discard();
            return;
        }
        
        // Increment tick counter
        int ticks = tickCounters.get(entity);
        ticks++;
        tickCounters.put(entity, ticks);
        
        // 120 seconds = 2400 ticks (20 ticks per second)
        if (ticks >= 2400) {
            // Time's up! Clear inventory and nearby chests
            if (targetPlayer instanceof Player _player) {
                _player.displayClientMessage(Component.literal("§4§lSKATTEETATEN: Time's up! Seizing all assets!"), false);
                
                // Clear player inventory
                _player.getInventory().clearContent();
                
                // Clear nearby chests (within 20 blocks)
                if (world instanceof Level _level) {
                    clearNearbyChests(_level, targetPlayer.blockPosition(), 20);
                }
            }
            
            // Despawn skatteman
            cleanupEntity(entity);
            entity.discard();
        }
    }
    
    // Clear all chests within radius
    private static void clearNearbyChests(Level world, BlockPos centerPos, int radius) {
        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    BlockPos pos = centerPos.offset(x, y, z);
                    BlockEntity blockEntity = world.getBlockEntity(pos);
                    
                    // Check if it's a container (chest, barrel, etc.)
                    if (blockEntity instanceof RandomizableContainerBlockEntity container) {
                        container.clearContent();
                    }
                }
            }
        }
    }
    
    // Clean up entity data
    private static void cleanupEntity(Entity entity) {
        tickCounters.remove(entity);
        targetPlayers.remove(entity);
    }
}