package net.mcreator.itacraft.network;

import net.mcreator.itacraft.ItacraftMod;

@EventBusSubscriber
public record PoopShortcutMessage(int eventType, int pressedms) implements CustomPacketPayload {

	public static final Type<PoopShortcutMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(ItacraftMod.MODID, "key_poop_shortcut"));

	public static final StreamCodec<RegistryFriendlyByteBuf, PoopShortcutMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, PoopShortcutMessage message) -> {
		buffer.writeInt(message.eventType);
		buffer.writeInt(message.pressedms);
	}, (RegistryFriendlyByteBuf buffer) -> new PoopShortcutMessage(buffer.readInt(), buffer.readInt()));

	@Override
	public Type<PoopShortcutMessage> type() {
		return TYPE;
	}

	public static void handleData(final PoopShortcutMessage message, final IPayloadContext context) {
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

			PoopShortcutOnKeyPressedProcedure.execute(world, x, y, z);
		}

	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		ItacraftMod.addNetworkMessage(PoopShortcutMessage.TYPE, PoopShortcutMessage.STREAM_CODEC, PoopShortcutMessage::handleData);
	}

}