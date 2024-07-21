package me.gyzzako.gyzzakoserver.logging;

import lombok.Getter;

@Getter
public enum LogLevel {
    ALL(Integer.MIN_VALUE),
    DEBUG(100),
    INFO(200),
    WARNING(300),
    SEVERE(400),
    OFF(Integer.MAX_VALUE);

    private final int value;

    LogLevel(final int value) {
        this.value = value;
    }
}
