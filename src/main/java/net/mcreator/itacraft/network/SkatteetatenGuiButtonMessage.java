package net.mcreator.itacraft.network;

@EventBusSubscriber
public record SkatteetatenGuiButtonMessage(int buttonID, int x, int y, int z) implements CustomPacketPayload {

	public static final Type<SkatteetatenGuiButtonMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(ItacraftMod.MODID, "skatteetaten_gui_buttons"));

	public static final StreamCodec<RegistryFriendlyByteBuf, SkatteetatenGuiButtonMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, SkatteetatenGuiButtonMessage message) -> {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}, (RegistryFriendlyByteBuf buffer) -> new SkatteetatenGuiButtonMessage(buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt()));

	@Override
	public Type<SkatteetatenGuiButtonMessage> type() {
		return TYPE;
	}

	public static void handleData(final SkatteetatenGuiButtonMessage message, final IPayloadContext context) {
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

		if (buttonID == 0) {

			SkatteetatenBruhProcedure.execute(entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		ItacraftMod.addNetworkMessage(SkatteetatenGuiButtonMessage.TYPE, SkatteetatenGuiButtonMessage.STREAM_CODEC, SkatteetatenGuiButtonMessage::handleData);
	}

}