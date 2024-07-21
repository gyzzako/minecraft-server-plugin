package me.gyzzako.gyzzakoserver;

import lombok.Getter;
import me.gyzzako.gyzzakoserver.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;

import static me.gyzzako.gyzzakoserver.Constants.LOGGER_PREFIX;

public final class GyzzakoServer extends JavaPlugin {

    @Getter
    private static GyzzakoServer instance;
    private static ConfigManager config;
    @Getter
    private static Logger defaultLogger;

    @Override
    public void onEnable() {
        setupGlobalVariables();

        defaultLogger.info("Plugin enabled !");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        defaultLogger.info("Plugin disabled !");
    }

    private void setupGlobalVariables() {
        instance = this;
        config = ConfigManager.getConfig();
        defaultLogger = new Logger(LOGGER_PREFIX, config.getLogLevel());
    }
}
