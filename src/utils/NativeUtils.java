package utils;

import java.io.*;
import java.nio.file.*;

public class NativeUtils {

    public static void loadLibraryFromJar(String path) throws IOException {

        // Extract the library to a temporary file
        Path temp = Files.createTempFile("lib", null);

        try (InputStream is = NativeUtils.class.getResourceAsStream(path);
                OutputStream os = Files.newOutputStream(temp)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        }
        // Load the library
        System.load(temp.toAbsolutePath().toString());
    }
}
