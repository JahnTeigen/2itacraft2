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

import net.mcreator.itacraft.entity.HomingMissileEntityEntity;

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
        
        // Check if timer is active - use proper NBT checking for Minecraft 1.21.8
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
        
        // Find the target player
        String playerUUID = "";
        if (entityData.contains("targetPlayer")) {
            playerUUID = entityData.getString("targetPlayer").orElse("");
        }
        
        Player targetPlayer = null;
        
        if (world instanceof ServerLevel serverLevel && !playerUUID.isEmpty()) {
            targetPlayer = findPlayerByUUID(serverLevel, playerUUID);
        }
        
        // Fallback to nearest player if UUID method fails
        if (targetPlayer == null && world instanceof ServerLevel serverLevel) {
            targetPlayer = findNearestPlayer(serverLevel, entity);
        }
        
        if (targetPlayer != null && targetPlayer.isAlive()) {
            // Check payment status
            int paidStatus = 0;
            if (targetPlayer.getPersistentData().contains("payed")) {
                paidStatus = targetPlayer.getPersistentData().getInt("payed").orElse(0);
            }
            
            if (paidStatus != 1) {
                targetPlayer.displayClientMessage(Component.literal("§4§lSKATTEETATEN: Time's up! Seizing all assets and launching missile!"), false);
                
                // Spawn homing missile using our custom entity
                spawnHomingMissile(world, targetPlayer);
                
                targetPlayer.getInventory().clearContent();
                
                // Cast to Level for chest clearing
                if (world instanceof Level levelWorld) {
                    clearNearbyChests(levelWorld, targetPlayer.blockPosition(), 20);
                }
            } else {
                targetPlayer.displayClientMessage(Component.literal("§a§lSKATTEETATEN: Payment confirmed. Have a nice day."), false);
                targetPlayer.getPersistentData().putInt("payed", 0);
            }
        }
        
        // Despawn the entity
        entity.discard();
    }
    
    private static void spawnHomingMissile(LevelAccessor world, Player targetPlayer) {
        if (!(world instanceof ServerLevel serverLevel)) return;
        
        // Use our custom homing missile entity
        HomingMissileEntityEntity.spawnHomingMissile(serverLevel, targetPlayer);
    }
    
    private static Player findPlayerByUUID(ServerLevel level, String uuid) {
        return level.players().stream()
            .filter(player -> player.getStringUUID().equals(uuid))
            .findFirst()
            .orElse(null);
    }
    
    private static Player findNearestPlayer(ServerLevel level, Entity entity) {
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