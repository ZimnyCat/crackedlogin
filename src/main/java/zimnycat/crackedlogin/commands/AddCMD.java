package zimnycat.crackedlogin.commands;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Formatting;
import zimnycat.crackedlogin.CommandBase;
import zimnycat.crackedlogin.utils.FileUtils;
import zimnycat.crackedlogin.utils.MessageUtils;

import java.io.IOException;
import java.nio.file.Files;

public class AddCMD extends CommandBase {

    static MinecraftClient mc = MinecraftClient.getInstance();

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public void run(String[] args) {
        if (args.length != 1) {
            MessageUtils.info("Command format: " + Formatting.WHITE + "./add <password>");
            return;
        }

        String[] parts = new String[]{mc.getCurrentServerEntry() == null ? "localhost" : mc.getCurrentServerEntry().address, mc.player.getName().getString()};

        try {
            String newData = parts[0] + " " + parts[1] + " " + args[0];
            FileUtils.appendLoginData(newData, new String(Files.readAllBytes(FileUtils.dataPath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
