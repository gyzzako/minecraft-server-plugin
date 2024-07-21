package me.gyzzako.gyzzakoserver.logging;

import org.bukkit.Bukkit;

public class Logger {
    private final String prefix;
    private final LogLevel configuredLogLevel;

    /**
     * Creates an instance of Logger.
     *
     * @param prefix             This is added before all messages
     * @param configuredLogLevel The logging level
     */
    public Logger(final String prefix, final LogLevel configuredLogLevel) {
        this.prefix = prefix;
        this.configuredLogLevel = configuredLogLevel;
    }

    /**
     * Display a info message to the console.
     *
     * @param msg Message
     */
    public void info(final String msg) {
        if (!isLoggable(LogLevel.INFO)) {
            return;
        }
        Bukkit.getLogger().info(prefix + " " + msg);
    }

    /**
     * Display a debug message to the console if debug mode is enabled.
     *
     * @param msg Message
     */
    public void debug(final String msg) {
        if (!isLoggable(LogLevel.DEBUG)) {
            return;
        }
        Bukkit.getLogger().info(prefix + "[Debug] " + msg);
    }

    /**
     * Display a severe message to the console.
     *
     * @param msg Message
     */
    public void severe(final String msg) {
        if (!isLoggable(LogLevel.SEVERE)) {
            return;
        }
        Bukkit.getLogger().severe(prefix + " " + msg);
    }

    /**
     * Display a warning message to the console.
     *
     * @param msg Message
     */
    public void warning(final String msg) {
        if (!isLoggable(LogLevel.WARNING)) {
            return;
        }
        Bukkit.getLogger().warning(prefix + " " + msg);
    }

    private boolean isLoggable(final LogLevel logLevel) {
        final int configuredLogLevelValue = configuredLogLevel.getValue();
        return logLevel.getValue() >= configuredLogLevelValue && configuredLogLevelValue != LogLevel.OFF.getValue();
    }
}
