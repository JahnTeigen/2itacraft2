package net.mcreator.itacraft.procedures;

import net.neoforged.bus.api.Event;

@EventBusSubscriber
public class RandomSkatteetatenSpawnProcedureProcedure {
	@SubscribeEvent
	public static void onPlayerTick(PlayerTickEvent.Post event) {
		execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (getEntityScore("timer_skatte", entity) >= 0) {
			for (int index0 = 0; index0 < 1; index0++) {
				{
					Entity _ent = entity;
					Scoreboard _sc = _ent.level().getScoreboard();
					Objective _so = _sc.getObjective("timer_skatte");
					if (_so == null)
						_so = _sc.addObjective("timer_skatte", ObjectiveCriteria.DUMMY, Component.literal("timer_skatte"), ObjectiveCriteria.RenderType.INTEGER, true, null);
					_sc.getOrCreatePlayerScore(ScoreHolder.forNameOnly(_ent.getScoreboardName()), _so).set(getEntityScore("timer_skatte", entity) + 1);
				}
				if (getEntityScore("timer_skatte", entity) == 72000) {
					{
						final Vec3 _center = new Vec3(x, y, z);
						for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(4 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList()) {
							if (world instanceof ServerLevel _level) {
								Entity entityToSpawn = ItacraftModEntities.SKATTEETATEN.get().spawn(_level, BlockPos.containing(x, y, z), EntitySpawnReason.MOB_SUMMONED);
								if (entityToSpawn != null) {
									entityToSpawn.setDeltaMovement(0, 0, 0);
								}
							}
						}
					}
					ItacraftMod.queueServerWork(2400, () -> {
						{
							Entity _ent = entity;
							Scoreboard _sc = _ent.level().getScoreboard();
							Objective _so = _sc.getObjective("timer_skatte");
							if (_so == null)
								_so = _sc.addObjective("timer_skatte", ObjectiveCriteria.DUMMY, Component.literal("timer_skatte"), ObjectiveCriteria.RenderType.INTEGER, true, null);
							_sc.getOrCreatePlayerScore(ScoreHolder.forNameOnly(_ent.getScoreboardName()), _so).set(1);
						}
					});
				}
			}
		} else {
			{
				Entity _ent = entity;
				Scoreboard _sc = _ent.level().getScoreboard();
				Objective _so = _sc.getObjective("timer_skatte");
				if (_so == null)
					_so = _sc.addObjective("timer_skatte", ObjectiveCriteria.DUMMY, Component.literal("timer_skatte"), ObjectiveCriteria.RenderType.INTEGER, true, null);
				_sc.getOrCreatePlayerScore(ScoreHolder.forNameOnly(_ent.getScoreboardName()), _so).set(0);
			}
		}
	}

	private static int getEntityScore(String score, Entity entity) {
		Scoreboard scoreboard = entity.level().getScoreboard();
		Objective scoreboardObjective = scoreboard.getObjective(score);
		if (scoreboardObjective != null)
			return scoreboard.getOrCreatePlayerScore(ScoreHolder.forNameOnly(entity.getScoreboardName()), scoreboardObjective).get();
		return 0;
	}
}