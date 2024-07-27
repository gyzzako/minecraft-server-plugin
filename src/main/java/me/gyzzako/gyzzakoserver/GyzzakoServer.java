package me.gyzzako.gyzzakoserver;

import lombok.Getter;
import me.gyzzako.gyzzakoserver.command.HelloCommand;
import me.gyzzako.gyzzakoserver.command.manager.CommandManager;
import me.gyzzako.gyzzakoserver.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Set;

import static me.gyzzako.gyzzakoserver.logging.LoggerConstants.LOGGER_PREFIX;

public final class GyzzakoServer extends JavaPlugin {

    @Getter
    private static GyzzakoServer instance;
    @Getter
    private static Logger defaultLogger;

    @Override
    public void onEnable() {
        setupGlobalVariables();

        try {
            CommandManager.createCoreCommand(this, "gyzzako", "A test core command with some sub commands",
                    "/gyzzako", null, List.of("gyz", "gp"), Set.of(HelloCommand.class));
        } catch (final NoSuchFieldException | IllegalAccessException e) {
            getDefaultLogger().severe("Error while trying to create the test core command.");
            e.printStackTrace();
        }

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
