package net.mcreator.itacraft.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.FriendlyByteBuf;
import io.netty.buffer.Unpooled;

public class OpenHawkPhoneProcedure {
    public static void execute(Entity entity) {
        if (entity == null)
            return;
        
        if (entity instanceof Player _player) {
            _player.openMenu(new net.minecraft.world.SimpleMenuProvider(
                (id, inv, player) -> {
                    FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
                    buf.writeBlockPos(entity.blockPosition());
                    return new net.mcreator.itacraft.world.inventory.HawkPhoneGUIHomeScreenMenu(id, inv, buf);
                },
                net.minecraft.network.chat.Component.literal("Hawk Phone")
            ), buf -> {
                buf.writeBlockPos(entity.blockPosition());
            });
        }
    }
}