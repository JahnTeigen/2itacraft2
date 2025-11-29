package net.mcreator.itacraft.procedures;

import net.neoforged.bus.api.Event;

public class CondomeDisplayConditionProcedure {
public static boolean execute(
LevelAccessor world ) {
if () {if (world instanceof ServerLevel _level) {
_level.getServer().getPlayerList().broadcastSystemMessage(Component.literal("Con dome is equipped")
.withStyle(ChatFormatting.UNDERLINE)
, false);
}return
true;}if (world instanceof ServerLevel _level) {
_level.getServer().getPlayerList().broadcastSystemMessage(Component.literal("Con dome is not equipped")
, false);
}return
false;
}
}