package game;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

final class FileUtils {
    /**
     * Read the text from the file with given filename.
     * @param pathname Like /Users/al/foo/bar.txt
     * @return All the content of given file as one String
     */
    static String readFile(final String pathname) {
        try {
            return new String(Files.readAllBytes(Paths.get(pathname)));
        } catch (IOException e) {
            System.err.println("malformed read operation: pathname=\"" + pathname + "\"");
            return null;
        }
    }

    /**
     * Save the given text to the given filename.
     * @param pathname Like /Users/al/foo/bar.txt
     * @param text All the text you want to save to the file as one String
     */
    static void writeFile(final String pathname, final String text) {
        try {
            var out = new BufferedWriter(new FileWriter(new File(pathname)));
            out.write(text);
            out.close();
        } catch (IOException e) {
            System.err.println("malformed write operation: pathname=\"" + pathname + "\", text=\"" + text + "\"");
        }
    }

    /**
     * Check if file exists on drive
     * @param pathname Like /Users/al/foo/bar.txt
     * @return true if file does exists, false otherwise
     */
    static boolean fileExists(final String pathname) {
        var file = new File(pathname);
        return file.exists() && !file.isDirectory();
    }

    /**
     * Deletes file or empty directory from drive
     * @param pathname Like /Users/al/foo/bar.txt
     */
    static void deleteFile(final String pathname) {
        try {
            Files.delete(Paths.get(pathname));
        } catch (IOException e) {
            System.err.println("malformed delete operation: pathname=\"" + pathname + "\"");
        }
    }

    private FileUtils() { }
}
