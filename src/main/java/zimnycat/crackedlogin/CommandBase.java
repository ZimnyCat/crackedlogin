package zimnycat.crackedlogin;

import net.minecraft.client.MinecraftClient;

public abstract class CommandBase {

    public static MinecraftClient mc = MinecraftClient.getInstance();

    public static String cmdPrefix = "cl/";

    public abstract String getName();

    public abstract String getErrorMSG();

    public abstract void run(String[] args);

}
