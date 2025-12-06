package net.mcreator.itacraft.network;

@EventBusSubscriber
public record AdPopupButtonMessage(int buttonID, int x, int y, int z) implements CustomPacketPayload {

	public static final Type<AdPopupButtonMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(ItacraftMod.MODID, "ad_popup_buttons"));

	public static final StreamCodec<RegistryFriendlyByteBuf, AdPopupButtonMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, AdPopupButtonMessage message) -> {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}, (RegistryFriendlyByteBuf buffer) -> new AdPopupButtonMessage(buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt()));

	@Override
	public Type<AdPopupButtonMessage> type() {
		return TYPE;
	}

	public static void handleData(final AdPopupButtonMessage message, final IPayloadContext context) {
		if (context.flow() == PacketFlow.SERVERBOUND) {
			context.enqueueWork(() -> handleButtonAction(context.player(), message.buttonID, message.x, message.y, message.z)).exceptionally(e -> {
				context.connection().disconnect(Component.literal(e.getMessage()));
				return null;
			});
		}
	}

	public static void handleButtonAction(Player entity, int buttonID, int x, int y, int z) {
		Level world = entity.level();

		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;

	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		ItacraftMod.addNetworkMessage(AdPopupButtonMessage.TYPE, AdPopupButtonMessage.STREAM_CODEC, AdPopupButtonMessage::handleData);
	}

}