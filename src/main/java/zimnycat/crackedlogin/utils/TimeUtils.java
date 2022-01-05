package zimnycat.crackedlogin.utils;

public class TimeUtils {

    public static long joinTime = 0;

    public static boolean isTimeDiffSmall() {
        return System.currentTimeMillis() - joinTime < 10000;
    }

}
