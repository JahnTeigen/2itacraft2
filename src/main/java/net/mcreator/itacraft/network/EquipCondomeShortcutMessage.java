package net.mcreator.itacraft.network;

import net.mcreator.itacraft.ItacraftMod;

@EventBusSubscriber
public record EquipCondomeShortcutMessage(int eventType, int pressedms) implements CustomPacketPayload {

	public static final Type<EquipCondomeShortcutMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(ItacraftMod.MODID, "key_equip_condome_shortcut"));

	public static final StreamCodec<RegistryFriendlyByteBuf, EquipCondomeShortcutMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, EquipCondomeShortcutMessage message) -> {
		buffer.writeInt(message.eventType);
		buffer.writeInt(message.pressedms);
	}, (RegistryFriendlyByteBuf buffer) -> new EquipCondomeShortcutMessage(buffer.readInt(), buffer.readInt()));

	@Override
	public Type<EquipCondomeShortcutMessage> type() {
		return TYPE;
	}

	public static void handleData(final EquipCondomeShortcutMessage message, final IPayloadContext context) {
		if (context.flow() == PacketFlow.SERVERBOUND) {
			context.enqueueWork(() -> {
				pressAction(context.player(), message.eventType, message.pressedms);
			}).exceptionally(e -> {
				context.connection().disconnect(Component.literal(e.getMessage()));
				return null;
			});
		}
	}

	public static void pressAction(Player entity, int type, int pressedms) {
		Level world = entity.level();
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();

		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(entity.blockPosition()))
			return;

		if (type == 0) {

			EquipCondomeShortcutOnKeyPressedProcedure.execute(world, x, y, z, entity);
		}

	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		ItacraftMod.addNetworkMessage(EquipCondomeShortcutMessage.TYPE, EquipCondomeShortcutMessage.STREAM_CODEC, EquipCondomeShortcutMessage::handleData);
	}

}