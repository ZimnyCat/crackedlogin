package zimnycat.crackedlogin.commands;

import net.minecraft.util.Formatting;
import zimnycat.crackedlogin.CommandBase;
import zimnycat.crackedlogin.utils.FileUtils;
import zimnycat.crackedlogin.utils.MessageUtils;

public class RemoveCMD extends CommandBase {

    @Override
    public String getName() {
        return "remove";
    }

    @Override
    public void run(String[] args) {
        String newData = "";

        if (args.length != 2) {
            MessageUtils.info("Command format: " + Formatting.WHITE + "./remove <server> <name>");
            return;
        }

        for (String str : FileUtils.readLoginData()) {
            String[] split = str.split(" ");
            if (!args[0].equals(split[0]) || !args[1].equals(split[1])) newData += str + "\n";
            else MessageUtils.info("A password for server " + args[0] + " and name " + args[1] + " removed");
        }
        FileUtils.appendLoginData(newData, "");
    }
}
