package net.mcreator.itacraft.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;

public class SkatteetatenOnEntityTickProcedure {
    public static void execute(LevelAccessor world, Entity entity) {
        if (entity == null) return;
        SkattemanOnInitialEntitySpawnProcedure.onEntityTick(world, entity);
    }
}