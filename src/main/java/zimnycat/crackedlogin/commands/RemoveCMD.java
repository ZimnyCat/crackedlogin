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
    public String getErrorMSG() {
        return "Command format: " + Formatting.WHITE + "./remove <server> <name>  OR  ./remove this";
    }

    @Override
    public void run(String[] args) {
        if (args[0].equals("this")) {
            String[] parts = MessageUtils.getServerAndName(mc);
            removeLine(parts[0], parts[1]);
            return;
        }

        removeLine(args[0], args[1]);
    }

    void removeLine(String first, String second) {
        String newData = "";

        for (String str : FileUtils.readLoginData()) {
            String[] split = str.split(" ");
            if (!first.equals(split[0]) || !second.equals(split[1])) newData += str + "\n";
            else MessageUtils.info("A password for server " + first + " and name " + second + " removed");
        }
        FileUtils.appendLoginData(newData, "");
    }
}
