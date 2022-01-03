package zimnycat.crackedlogin.mixin;

import io.netty.channel.ChannelHandlerContext;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import zimnycat.crackedlogin.utils.FileUtils;
import zimnycat.crackedlogin.utils.MessageUtils;

import java.io.IOException;
import java.nio.file.Files;

@Mixin(ClientConnection.class)
public class MClientConnection {
    @Inject(method = "channelRead0", at = @At("HEAD"), cancellable = true)
    public void channelRead0(ChannelHandlerContext channelHandlerContext_1, Packet<?> packet_1, CallbackInfo ci) {
        MinecraftClient mc = MinecraftClient.getInstance();

        if (packet_1 instanceof GameMessageS2CPacket) {
            if (!MessageUtils.isLoginMsgS2C(((GameMessageS2CPacket) packet_1).getMessage().getString())) return;

            FileUtils.readLoginData().forEach(str -> {
                String[] split = str.split(" ");
                if (split[0].equals(mc.getCurrentServerEntry().address) && split[1].equals(mc.player.getName().getString())) {
                    mc.player.sendChatMessage("/login " + split[2]);
                    return;
                }
            });
        }
    }

    @Inject(method = "send(Lnet/minecraft/network/Packet;Lio/netty/util/concurrent/GenericFutureListener;)V", at = @At("HEAD"), cancellable = true)
    private void send(Packet<?> packet, GenericFutureListener<? extends Future<? super Void>> packetCallback, CallbackInfo ci) {
        MinecraftClient mc = MinecraftClient.getInstance();

        if (packet instanceof ChatMessageC2SPacket) {
            String msg = ((ChatMessageC2SPacket) packet).getChatMessage();
            if (!MessageUtils.isLoginMsgC2S(msg)) return;

            for (String str : FileUtils.readLoginData()) {
                String[] split = str.split(" ");
                if (split[0].equals(mc.getCurrentServerEntry().address) && split[1].equals(mc.player.getName().getString())) {
                    return;
                }
            }

            try {
                FileUtils.appendLoginData(MessageUtils.createInfoArray(msg), new String(Files.readAllBytes(FileUtils.dataPath)));
            } catch (IOException ignored) {
            }
        }
    }
}
