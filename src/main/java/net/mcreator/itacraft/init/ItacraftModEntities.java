/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.itacraft.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.EventPriority;

import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;

import net.mcreator.itacraft.entity.*;
import net.mcreator.itacraft.ItacraftMod;

@EventBusSubscriber
public class ItacraftModEntities {
	public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(Registries.ENTITY_TYPE, ItacraftMod.MODID);
	public static final DeferredHolder<EntityType<?>, EntityType<GeirHaoyEntity>> GEIR_HAOY = register("geir_haoy",
			EntityType.Builder.<GeirHaoyEntity>of(GeirHaoyEntity::new, MobCategory.CREATURE).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).fireImmune()

					.sized(0.6f, 1.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<JudeEntity>> JUDE = register("jude",
			EntityType.Builder.<JudeEntity>of(JudeEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.6f, 1.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<GudrunEntity>> BODIL = register("bodil",
			EntityType.Builder.<GudrunEntity>of(GudrunEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.6f, 1.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<GamerGirlEntity>> GAMER_GIRL = register("gamer_girl",
			EntityType.Builder.<GamerGirlEntity>of(GamerGirlEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.6f, 1.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<TonyHaynesEntity>> TONY_HAYNES = register("tony_haynes",
			EntityType.Builder.<TonyHaynesEntity>of(TonyHaynesEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.6f, 1.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<FireballEntity>> FIREBALL = register("fireball",
			EntityType.Builder.<FireballEntity>of(FireballEntity::new, MobCategory.MISC).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final DeferredHolder<EntityType<?>, EntityType<MonicaEntity>> MONICA = register("monica",
			EntityType.Builder.<MonicaEntity>of(MonicaEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.6f, 1.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<VippsRanerEntity>> VIPPS_RANER = register("vipps_raner",
			EntityType.Builder.<VippsRanerEntity>of(VippsRanerEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(100).setUpdateInterval(3)

					.sized(0.6f, 1.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<SkatteetatenEntity>> SKATTEETATEN = register("skatteetaten",
			EntityType.Builder.<SkatteetatenEntity>of(SkatteetatenEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).fireImmune()

					.sized(0.6f, 1.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<RetardedGamerGirlEntity>> RETARDED_GAMER_GIRL = register("retarded_gamer_girl",
			EntityType.Builder.<RetardedGamerGirlEntity>of(RetardedGamerGirlEntity::new, MobCategory.CREATURE).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.ridingOffset(-0.6f).sized(0.6f, 1.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<HomingMissileEntityEntity>> HOMING_MISSILE_ENTITY = register("homing_missile_entity",
			EntityType.Builder.<HomingMissileEntityEntity>of(HomingMissileEntityEntity::new, MobCategory.MISC).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));

	// Start of user code block custom entities
	// End of user code block custom entities
	private static <T extends Entity> DeferredHolder<EntityType<?>, EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		return REGISTRY.register(registryname, () -> (EntityType<T>) entityTypeBuilder.build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(ItacraftMod.MODID, registryname))));
	}

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void registerCapabilities(RegisterCapabilitiesEvent event) {
		event.registerEntity(Capabilities.ItemHandler.ENTITY, JUDE.get(), (living, context) -> living.getCombinedInventory());
		event.registerEntity(Capabilities.ItemHandler.ENTITY, BODIL.get(), (living, context) -> living.getCombinedInventory());
		event.registerEntity(Capabilities.ItemHandler.ENTITY, SKATTEETATEN.get(), (living, context) -> living.getCombinedInventory());
	}

	@SubscribeEvent
	public static void init(RegisterSpawnPlacementsEvent event) {
		GeirHaoyEntity.init(event);
		JudeEntity.init(event);
		GudrunEntity.init(event);
		GamerGirlEntity.init(event);
		TonyHaynesEntity.init(event);
		MonicaEntity.init(event);
		VippsRanerEntity.init(event);
		SkatteetatenEntity.init(event);
		RetardedGamerGirlEntity.init(event);
	}

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(GEIR_HAOY.get(), GeirHaoyEntity.createAttributes().build());
		event.put(JUDE.get(), JudeEntity.createAttributes().build());
		event.put(BODIL.get(), GudrunEntity.createAttributes().build());
		event.put(GAMER_GIRL.get(), GamerGirlEntity.createAttributes().build());
		event.put(TONY_HAYNES.get(), TonyHaynesEntity.createAttributes().build());
		event.put(MONICA.get(), MonicaEntity.createAttributes().build());
		event.put(VIPPS_RANER.get(), VippsRanerEntity.createAttributes().build());
		event.put(SKATTEETATEN.get(), SkatteetatenEntity.createAttributes().build());
		event.put(RETARDED_GAMER_GIRL.get(), RetardedGamerGirlEntity.createAttributes().build());
	}
}