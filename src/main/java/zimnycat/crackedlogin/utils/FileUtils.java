package zimnycat.crackedlogin.utils;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Formatting;

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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> readLoginData() {
        try {
            return Files.readAllLines(dataPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static void appendLoginData(String newData, String oldData) {
        try {
            FileWriter fw = new FileWriter(dataPath.toFile());
            fw.write(oldData + newData);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
