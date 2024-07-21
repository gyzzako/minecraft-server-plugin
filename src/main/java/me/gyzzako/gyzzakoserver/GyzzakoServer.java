package me.gyzzako.gyzzakoserver;

import lombok.Getter;
import me.gyzzako.gyzzakoserver.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;

import static me.gyzzako.gyzzakoserver.logging.LoggerConstants.LOGGER_PREFIX;

public final class GyzzakoServer extends JavaPlugin {

    @Getter
    private static GyzzakoServer instance;
    @Getter
    private static Logger defaultLogger;

    @Override
    public void onEnable() {
        setupGlobalVariables();

        defaultLogger.info("Plugin enabled!");
    }

    @Override
    public void onDisable() {
        defaultLogger.info("Plugin disabled!");
    }

    private void setupGlobalVariables() {
        instance = this;
        defaultLogger = new Logger(LOGGER_PREFIX, ConfigManager.getLogLevel());
    }
}
