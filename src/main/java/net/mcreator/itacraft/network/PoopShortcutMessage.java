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
			}).exceptionally(e -> {
				context.connection().disconnect(Component.literal(e.getMessage()));
				return null;
			});
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		ItacraftMod.addNetworkMessage(PoopShortcutMessage.TYPE, PoopShortcutMessage.STREAM_CODEC, PoopShortcutMessage::handleData);
	}

}