package zimnycat.crackedlogin.utils;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;
import zimnycat.crackedlogin.CrackedLogin;

public class MessageUtils {

    static MinecraftClient mc = MinecraftClient.getInstance();

    public static boolean isLoginMsgS2C(String msg) {
        return msg != null && (msg.contains("/login") || msg.contains("/l"));
    }

    public static boolean isLoginMsgC2S(String msg) {
        return msg != null && (msg.startsWith("/login") || msg.startsWith("/l")) && msg.split(" ").length == 2;
    }

    public static String[] createInfoArray(String msg) {
        return new String[]{mc.getCurrentServerEntry().address, mc.player.getName().getString(), msg.split(" ")[1]};
    }

    public static void info(String msg) {
        CrackedLogin.LOGGER.info(msg);
        mc.inGameHud.getChatHud().addMessage(new LiteralText(Formatting.DARK_AQUA + "[CL] " + msg));
    }

}
