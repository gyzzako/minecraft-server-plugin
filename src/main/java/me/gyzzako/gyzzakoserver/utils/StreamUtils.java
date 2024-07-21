package me.gyzzako.gyzzakoserver.utils;

import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@UtilityClass
public class StreamUtils {
    private static final int STREAM_BUFFER_SIZE = 8182;

    /**
     * Copies the input stream into the output stream
     */
    public static void copyStream(final InputStream input, final OutputStream output) throws IOException {
        final byte[] buffer = new byte[STREAM_BUFFER_SIZE];
        int read;

        while ((read = input.read(buffer)) != -1) {
            output.write(buffer, 0, read);
        }
    }
}
