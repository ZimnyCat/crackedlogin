package zimnycat.crackedlogin.utils;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import zimnycat.crackedlogin.CrackedLogin;

public class MessageUtils {

    static MinecraftClient mc = MinecraftClient.getInstance();

    public static boolean isLoginMessage(String msg) {
        return msg != null && (msg.contains("/login") || msg.contains("/l"));
    }

    public static boolean isLoginCommand(String msg) {
        return msg != null && (msg.startsWith("login") || msg.startsWith("l")) && msg.split(" ").length == 2;
    }

    public static void info(String msg) {
        CrackedLogin.LOGGER.info(msg);
        mc.inGameHud.getChatHud().addMessage(Text.of(Formatting.DARK_AQUA + "[CL] " + msg));
    }

    public static String[] getServerAndName(MinecraftClient mc) {
        return new String[]{mc.getCurrentServerEntry() == null ? "localhost" : mc.getCurrentServerEntry().address, mc.player.getName().getString()};
    }

    public static String cum(String name) {
        return Formatting.WHITE + name + Formatting.DARK_AQUA;
    }

}
