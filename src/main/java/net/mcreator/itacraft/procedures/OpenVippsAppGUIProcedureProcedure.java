package net.mcreator.itacraft.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import io.netty.buffer.Unpooled;

public class OpenVippsAppGUIProcedureProcedure {
    public static void execute(Entity entity) {
        if (entity == null)
            return;
        
        if (entity instanceof ServerPlayer _player) {
            
            _player.openMenu(new net.minecraft.world.MenuProvider() {
                @Override
                public Component getDisplayName() {
                    return Component.literal("Vipps");
                }
                
                @Override
                public net.minecraft.world.inventory.AbstractContainerMenu createMenu(int id, net.minecraft.world.entity.player.Inventory inv, Player player) {
                    FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
                    buf.writeBlockPos(_player.blockPosition());
                    return new net.mcreator.itacraft.world.inventory.VippsAppGuiMenu(id, inv, buf);
                }
            });
        }
    }
}