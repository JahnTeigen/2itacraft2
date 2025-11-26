package net.mcreator.itacraft.network;

@EventBusSubscriber
public record VippsMiniBankGUIButtonMessage(int buttonID, int x, int y, int z) implements CustomPacketPayload {

	public static final Type<VippsMiniBankGUIButtonMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(ItacraftMod.MODID, "vipps_mini_bank_gui_buttons"));

	public static final StreamCodec<RegistryFriendlyByteBuf, VippsMiniBankGUIButtonMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, VippsMiniBankGUIButtonMessage message) -> {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}, (RegistryFriendlyByteBuf buffer) -> new VippsMiniBankGUIButtonMessage(buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt()));

	@Override
	public Type<VippsMiniBankGUIButtonMessage> type() {
		return TYPE;
	}

	public static void handleData(final VippsMiniBankGUIButtonMessage message, final IPayloadContext context) {
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

			VippsMiniBankProcedureProcedure.execute(entity);
		}
		if (buttonID == 1) {

			TaUTPengerProcedure.execute();
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		ItacraftMod.addNetworkMessage(VippsMiniBankGUIButtonMessage.TYPE, VippsMiniBankGUIButtonMessage.STREAM_CODEC, VippsMiniBankGUIButtonMessage::handleData);
	}

}