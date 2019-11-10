package ca.retrylife.libics.files;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.nio.file.Path;

public class FileUtils {
    private static FileUtils instance = null;

    // Hide the FileUtils constructor
    private FileUtils() {
    }

    /**
     * Get the FileUtils instance
     * 
     * @return Current instance
     */
    public static FileUtils getInstance() {
        if (instance == null) {
            instance = new FileUtils();
        }

        return instance;
    }

    /**
     * Get a file from resources folder
     * 
     * @param relpath File path relative to resources folder
     * @return File
     */
    public static File getResource(String relpath) {
        return new File(getInstance().getClass().getClassLoader().getResource(relpath).getFile());
    }

    /**
     * Get a file as an InputStream from resources folder
     * 
     * @param relpath File path relative to resources folder
     * @return File stream
     */
    public static InputStream getResourceStream(String relpath) {
        return getInstance().getClass().getClassLoader().getResourceAsStream(relpath);
    }

    /**
     * Get a file path string from many segments. This works across platforms
     * 
     * @param base     Base segment
     * @param segments Other segments
     * @return File path string
     */
    public static String constructPathString(String base, String... segments) {
        return Paths.get(base, segments).toString();
    }

    /**
     * Get absolute path to resources directory
     * 
     * @return Path to resources directory
     */
    public static String getResourcePath() {
        return (new File(getInstance().getClass().getClassLoader().getResource("").getFile())).getAbsolutePath()
                .toString();
    }

    /**
     * Make all directories in a file path. Yes, File.mkdirs exists.. I spend too
     * long on this to delete it.
     * 
     * @param finalPath   Full file path
     * @param createFinal Should the final segment be treated as a file? Or a
     *                    directory? (true for directory)
     */
    public static void mkStructure(String finalPath, boolean createFinal) {

        // Find all path segments (handles both *nix and Windows environments)
        String[] segments = finalPath.split(Matcher.quoteReplacement(System.getProperty("file.separator")));

        // Storage for path as it is walked
        ArrayList<String> walkedPath = new ArrayList<String>();

        for (int i = 0; i < segments.length; i++) {
            // Ensure we don't make the final segment into a directory unless specified
            if (i == segments.length - 1 && !createFinal) {
                // Try to create final segment as a file. Or fail
                try {
                    (new File(finalPath)).createNewFile();
                } catch (IOException e) {
                    // Do nothing. This is not needed, and should be an impossible case
                }

                // Stop making dirs
                break;
            }

            // Add current segment to the walked path
            walkedPath.add(segments[i]);

            // Construct truncated path from current list of walked segments
            String truncPath = String.join("/", walkedPath.toArray(new String[walkedPath.size()]));

            // Construct file for current segment for use below
            File currentPath = new File(truncPath);

            // Create the folder if it does not exist
            if (!currentPath.exists()) {
                currentPath.mkdir();
            }

        }
    }
}