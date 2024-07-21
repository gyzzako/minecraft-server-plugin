package me.gyzzako.gyzzakoserver;


import lombok.Getter;

@Getter
public enum ConfigPath {
    LOG_LEVEL("logLevel");

    private final String value;

    ConfigPath(final String value) {
        this.value = value;
    }
}
