package zimnycat.crackedlogin;

public abstract class CommandBase {

    public static String cmdPrefix = "./";

    public abstract String getName();

    public abstract void run(String[] args);

}
