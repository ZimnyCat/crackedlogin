package zimnycat.crackedlogin.commands;

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
        return "Command format: " + MessageUtils.cum(cmdPrefix + "remove <server> <name>") + " OR " + MessageUtils.cum(cmdPrefix + "remove this");
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
            if (!first.equals(split[0]) || !second.equals(split[1])) {
                if (!str.equals("\n")) newData += str + "\n";
            } else MessageUtils.info("A password for server " + MessageUtils.cum(first) + " and name " + MessageUtils.cum(second) + " removed");
        }
        FileUtils.appendLoginData(newData, "");
    }
}
