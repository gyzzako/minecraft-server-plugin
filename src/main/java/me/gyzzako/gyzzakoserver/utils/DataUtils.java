package me.gyzzako.gyzzakoserver.utils;

import lombok.experimental.UtilityClass;

import java.util.Collection;

@UtilityClass
public class DataUtils {
    public static boolean isEmpty(final Collection<?> coll) {
        return coll == null || coll.isEmpty();
    }

    public static boolean isNotEmpty(final Collection<?> coll) {
        return !isEmpty(coll);
    }
}
