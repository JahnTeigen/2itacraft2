package net.mcreator.itacraft.network;

@EventBusSubscriber
public record NSMRequestInterfaceButtonMessage(int buttonID, int x, int y, int z) implements CustomPacketPayload {

	public static final Type<NSMRequestInterfaceButtonMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(ItacraftMod.MODID, "nsm_request_interface_buttons"));

	public static final StreamCodec<RegistryFriendlyByteBuf, NSMRequestInterfaceButtonMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, NSMRequestInterfaceButtonMessage message) -> {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}, (RegistryFriendlyByteBuf buffer) -> new NSMRequestInterfaceButtonMessage(buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt()));

	@Override
	public Type<NSMRequestInterfaceButtonMessage> type() {
		return TYPE;
	}

	public static void handleData(final NSMRequestInterfaceButtonMessage message, final IPayloadContext context) {
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

			GetPOBProcedure.execute(world, x, y, z, entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		ItacraftMod.addNetworkMessage(NSMRequestInterfaceButtonMessage.TYPE, NSMRequestInterfaceButtonMessage.STREAM_CODEC, NSMRequestInterfaceButtonMessage::handleData);
	}

}