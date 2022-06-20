package zimnycat.crackedlogin.commands;

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
        return "Command format: " + MessageUtils.cum(cmdPrefix + "add <password>");
    }

    @Override
    public void run(String[] args) {
        String[] parts = MessageUtils.getServerAndName(mc);
        String newData = parts[0] + " " + parts[1] + " " + args[0] + "\n";

        try {
            FileUtils.appendLoginData(newData, new String(Files.readAllBytes(FileUtils.dataPath)));
            MessageUtils.info("Saved new login data (server: " + MessageUtils.cum(parts[0])+ ", name: " + MessageUtils.cum(parts[1]) + ")"
                    + "\nUse " + MessageUtils.cum("\"" + cmdPrefix + "remove this\"") + " if you entered an incorrect password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
