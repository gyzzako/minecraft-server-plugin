package me.gyzzako.gyzzakoserver;

import me.gyzzako.gyzzakoserver.logging.LogLevel;
import me.gyzzako.gyzzakoserver.utils.StreamUtils;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;

import static me.gyzzako.gyzzakoserver.ConfigConstants.CONFIG_FILE_FULL_NAME;
import static me.gyzzako.gyzzakoserver.ConfigConstants.DEFAULT_LOG_LEVEL;

public class ConfigManager {
    private static final YamlConfiguration config = getConfigFile();

    public static LogLevel getLogLevel() {
        return config.getObject(ConfigPath.LOG_LEVEL.getValue(), LogLevel.class, DEFAULT_LOG_LEVEL);
    }

    /**
     * Creates the config file is it does not exist yet, and load it as YamlConfiguration
     *
     * @return The YamlConfiguration
     */
    private static YamlConfiguration getConfigFile() {
        if (!GyzzakoServer.getInstance().getDataFolder().exists()) {
            GyzzakoServer.getInstance().getDataFolder().mkdir();
        }

        final File file = new File(GyzzakoServer.getInstance().getDataFolder(), CONFIG_FILE_FULL_NAME);

        if (!file.exists()) {
            try {
                file.createNewFile();
                StreamUtils.copyStream(Objects.requireNonNull(GyzzakoServer.getInstance().getResource(CONFIG_FILE_FULL_NAME)), Files.newOutputStream(file.toPath()));
            } catch (final IOException e) {
                e.printStackTrace();
                GyzzakoServer.getDefaultLogger().severe("Creating config file failed. Disabling plugin...");
                GyzzakoServer.getInstance().getPluginLoader().disablePlugin(GyzzakoServer.getInstance());
            }
        }

        return YamlConfiguration.loadConfiguration(file);
    }
}
