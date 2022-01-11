package zimnycat.crackedlogin.utils;

import org.apache.commons.lang3.ArrayUtils;
import zimnycat.crackedlogin.CommandBase;
import zimnycat.crackedlogin.commands.AddCMD;
import zimnycat.crackedlogin.commands.RemoveCMD;

import java.util.Arrays;
import java.util.List;

public class CommandUtil {

    static List<CommandBase> cmdList = Arrays.asList(
            new AddCMD(),
            new RemoveCMD()
    );

    public static void runCMD(String msg) {
        String[] args = msg.replace(CommandBase.cmdPrefix, "").split(" ");
        cmdList.forEach(cmd -> {
            if (cmd.getName().equals(args[0])) {
                cmd.run(ArrayUtils.remove(args, 0));
            }
        });
    }

}
