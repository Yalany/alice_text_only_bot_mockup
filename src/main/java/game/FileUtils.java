package game;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

final class FileUtils {
    private static final Logger log = LogManager.getLogger(LoggingLog4j.class);
    private FileUtils() { }

    /**
     * @param pathname in form /Users/al/foo/bar.txt
     * @return All the content of given file as one String
     */
    static String readFile(final String pathname) {
        try {
            return new String(Files.readAllBytes(Paths.get(pathname)));
        } catch (IOException e) {
            logger.log(Level.WARNING, "malformed read operation: pathname=\"" + pathname + "\" ", e);
            return null;
        }
    }

    /**
     * @param pathname in form /Users/al/foo/bar.txt
     * @param text String to be saved
     */
    static void writeFile(final String pathname, final String text) {
        try {
            var out = new BufferedWriter(new FileWriter(new File(pathname)));
            out.write(text);
            out.close();
        } catch (IOException e) {
            logger.log(Level.WARNING, "malformed write operation: pathname=\"" + pathname + "\", text=\"" + text + "\" ", e);
        }
    }

    /**
     * @param pathname in form /Users/al/foo/bar.txt
     * @return true if file does exists, false otherwise
     */
    static boolean fileExists(final String pathname) {
        var file = new File(pathname);
        return file.exists() && !file.isDirectory();
    }

    /**
     * Removes a file or empty directory from disk
     * @param pathname in form /Users/al/foo/bar.txt
     */
    static void deleteFile(final String pathname) {
        try {
            Files.delete(Paths.get(pathname));
        } catch (IOException e) {
            logger.log(Level.WARNING, "malformed delete operation: pathname=\"" + pathname + "\"", e);
        }
    }
}
