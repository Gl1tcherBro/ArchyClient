package gl1tch.archyclient.Util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gl1tch.archyclient.ArchyClient;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class ModConfigHandler {
    public static ModConfigOptions readClientConfig() {
        ModConfigOptions configOptions = new ModConfigOptions();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Path configFolderPath = Path.of(System.getProperty("user.dir"), "/config");
        Path configFilePath = Path.of(String.valueOf(configFolderPath), ArchyClient.MOD_ID + ".json");
        String contents;

        if (!Files.exists(configFolderPath)) {
            try {
                Files.createDirectory(configFolderPath);
            } catch (Exception e) {
                ArchyClient.LOGGER.warn("Caught: " + e.toString());
            }
        }

        if (!Files.exists(configFilePath)) {
            ArchyClient.LOGGER.info("CREATING CONFIG");
            contents = gson.toJson(configOptions, ModConfigOptions.class);

            try {
                Files.write(configFilePath, contents.getBytes(), StandardOpenOption.CREATE);
            } catch (Exception e) {
                ArchyClient.LOGGER.warn("Caught: " + e.toString());
            }
        } else {
            ArchyClient.LOGGER.info("READING CONFIG");
            try {
                contents = Files.readString(configFilePath);

                configOptions = gson.fromJson(contents, ModConfigOptions.class);
            } catch (Exception e) {
                ArchyClient.LOGGER.warn("Caught: " + e.toString());
            }
        }

        return configOptions;
    }

    public static void writeClientConfig() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Path configFolderPath = Path.of(System.getProperty("user.dir"), "/config");
        Path configFilePath = Path.of(String.valueOf(configFolderPath), ArchyClient.MOD_ID + ".json");
        String contents;

        if (!Files.exists(configFolderPath)) {
            try {
                Files.createDirectory(configFolderPath);
            } catch (Exception e) {
                ArchyClient.LOGGER.warn("Caught: " + e.toString());
            }
        }

        if (!Files.exists(configFilePath)) {
            ArchyClient.LOGGER.info("CREATING CONFIG");
            contents = gson.toJson(ArchyClient.configOptions, ModConfigOptions.class);

            try {
                Files.write(configFilePath, contents.getBytes(), StandardOpenOption.CREATE);
            } catch (Exception e) {
                ArchyClient.LOGGER.warn("Caught: " + e.toString());
            }
        } else {
            ArchyClient.LOGGER.info("SAVING CONFIG OPTIONS");
            contents = gson.toJson(ArchyClient.configOptions, ModConfigOptions.class);

            try {
                Files.write(configFilePath, contents.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
            } catch (Exception e) {
                ArchyClient.LOGGER.warn("Caught: " + e.toString());
            }
        }
    }
}
