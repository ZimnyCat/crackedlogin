package zimnycat.crackedlogin.commands;

import net.minecraft.util.Formatting;
import zimnycat.crackedlogin.CommandBase;
import zimnycat.crackedlogin.utils.FileUtils;
import zimnycat.crackedlogin.utils.MessageUtils;

import java.io.IOException;
import java.nio.file.Files;

public class AddCMD extends CommandBase {

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getErrorMSG() {
        return "Command format: " + Formatting.WHITE + "./add <password>";
    }

    @Override
    public void run(String[] args) {
        String[] parts = MessageUtils.getServerAndName(mc);
        String newData = parts[0] + " " + parts[1] + " " + args[0];

        try {
            FileUtils.appendLoginData(newData, new String(Files.readAllBytes(FileUtils.dataPath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
