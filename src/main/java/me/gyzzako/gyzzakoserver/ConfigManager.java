package me.gyzzako.gyzzakoserver;

import me.gyzzako.gyzzakoserver.logging.LogLevel;
import me.gyzzako.gyzzakoserver.utils.StreamUtils;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;

import static me.gyzzako.gyzzakoserver.Constants.CONFIG_FILE_FULL_NAME;

public class ConfigManager {
    private static final ConfigManager instance = new ConfigManager(CONFIG_FILE_FULL_NAME);

    private final YamlConfiguration config;

    public static ConfigManager getConfig() {
        return instance;
    }

    private ConfigManager(final String fileName) {
        config = getConfigFile(fileName);
    }

    /**
     * Creates the config file is it does not exist yet, and load it as YamlConfiguration
     *
     * @param fileName The config file full name.
     * @return The YamlConfiguration
     */
    private YamlConfiguration getConfigFile(final String fileName) {
        if (!GyzzakoServer.getInstance().getDataFolder().exists()) {
            GyzzakoServer.getInstance().getDataFolder().mkdir();
        }

        final File file = new File(GyzzakoServer.getInstance().getDataFolder(), fileName);

        if (!file.exists()) {
            try {
                file.createNewFile();
                StreamUtils.copyStream(Objects.requireNonNull(GyzzakoServer.getInstance().getResource(fileName)), Files.newOutputStream(file.toPath()));
            } catch (final IOException e) {
                e.printStackTrace();
                GyzzakoServer.getDefaultLogger().severe("Creating config file failed. Disabling plugin...");
                GyzzakoServer.getInstance().getPluginLoader().disablePlugin(GyzzakoServer.getInstance());
            }
        }

        return YamlConfiguration.loadConfiguration(file);
    }

    public LogLevel getLogLevel() {
        return config.getObject(ConfigPath.LOG_LEVEL.getValue(), LogLevel.class, LogLevel.INFO);
    }
}
