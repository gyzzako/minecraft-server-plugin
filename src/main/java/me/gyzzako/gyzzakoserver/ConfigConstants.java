package me.gyzzako.gyzzakoserver;

import lombok.experimental.UtilityClass;
import me.gyzzako.gyzzakoserver.logging.LogLevel;

@UtilityClass
public class ConfigConstants {
    public static final String CONFIG_FILE_FULL_NAME = "config.yml";
    public static final LogLevel DEFAULT_LOG_LEVEL = LogLevel.INFO;
}
