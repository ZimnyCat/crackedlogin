package zimnycat.crackedlogin.utils;

import net.minecraft.client.MinecraftClient;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static Path dataPath = Paths.get(MinecraftClient.getInstance().runDirectory.getPath()).resolve("loginData.txt");

    public static void init() {
        try {
            if (!dataPath.toFile().exists()) Files.createFile(dataPath);
        } catch (IOException ignored) {
        }
    }

    public static List<String> readLoginData() {
        try {
            return Files.readAllLines(dataPath);
        } catch (IOException ignored) {
        }
        return new ArrayList<>();
    }

    public static void appendLoginData(String[] arr, String data) {
        try {
            FileWriter fw = new FileWriter(dataPath.toFile());
            fw.write(data + arr[0] + " " + arr[1] + " " + arr[2] + "\n");
            fw.close();
        } catch (IOException ignored) {
        }
    }

}
