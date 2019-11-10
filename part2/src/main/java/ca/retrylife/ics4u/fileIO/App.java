/**
 * FileIO assignment
 * By: Evan Pratten
 * 
 *  - read from keyboard, write to file. Multiple lines
 *  - append text to a file
 *  - read multiple lines from file
 *  - read a webpage, see if "Canada" is in the text
 */

package ca.retrylife.ics4u.fileIO;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import ca.retrylife.libics.files.FileUtils;
import ca.retrylife.libics.files.STDIO;
import ca.retrylife.libics.frameworks.Assignment;

public class App extends Assignment {
    /* Configuration constants */
    private final String folderName = "fileIO";
    private final String consoleLogFileName = "consolelog.txt";
    private final String wordFile = "words.txt";
    private final String remoteURL = "https://retrylife.ca";
    private final String remoteWord = "Canada";

    /* Vars */
    private BufferedReader stdin;
    private FileWriter consoleFile; // This is a wrapper for PrintWriter.. Don't be mad plz
    private String consoleFilePath;
    private URL remoteFile;

    public static void main(String[] args) {
        (new App()).run();
    }

    public App() {
        // Register this assignment
        register("FileIO");

        // Get the input buffer
        stdin = STDIO.getInputBuffer();

        /* Create writer for console file */

        // Construct platform-independent filepath
        consoleFilePath = FileUtils.constructPathString(folderName, consoleLogFileName);

        // Build directory structure to ensure file can be safely created
        FileUtils.mkStructure(FileUtils.constructPathString(FileUtils.getResourcePath(), consoleFilePath), false);

        // Try to create writer for this file
        try {
            // Create the file writer
            consoleFile = new FileWriter(FileUtils.getResource(consoleFilePath));
        } catch (IOException e) {
            // Display error, and return (the assignment relies on this file, so there is no
            // use continuing)
            System.out.printf("Cannot write to %s. Due to:%n%s%n", consoleFilePath, e);
            return;
        }

    }

    @Override
    public void run() {

        // Run each part in it's own scope
        part1();
        part2();
        part3();
    }

    /* Read user input to file */
    private void part1() {

        System.out.println("Enter text to be written to a file");
        System.out.println("Entering \".\" by itself on a line will stop reading, and move on");

        String input = "";
        while (true) {
            // Print input indicator
            System.out.print("> ");

            // Try to read user input, or break out of loop
            try {
                input = stdin.readLine();
            } catch (IOException e) {
                System.out.println("Could not read input. Skipping task");
                break;
            }

            // Break out of loop if end-of-input is found
            if (input.equals(".")) {
                break;
            }

            try {
                // Write line to file (platform safe)
                consoleFile.write(String.format("%s%n", input));
            } catch (IOException e) {
                System.out.println("Failed to write to console file!");
                break;
            }

        }

        // Write some lines to the file programmatically
        String footer = "\n\n---\nThis file was created by the fileIO program\nThis is a new line to fill out the requirements";
        try {
            // Write line to file (platform safe)
            consoleFile.write(String.format("%s%n", footer));
        } catch (IOException e) {
            System.out.println("Failed to write to console file!");
        }

        // Close console file
        try {
            consoleFile.close();
        } catch (IOException e) {
            System.out.printf("Failed to close console file with error: %n%s%n", e);
        }

        // Inform the user where the file can be found
        System.out.printf("The console file can be found at: %s/%s%n", FileUtils.getResourcePath(), consoleFilePath);

    }

    /* Read file, line-by-line, and print it */
    private void part2() {

        System.out.println("\nHere is a file. Read line-by-line:");

        // Read file, or skip
        String[] lines;
        try {
            String filePath = FileUtils.constructPathString(FileUtils.getResourcePath(), folderName, wordFile);
            lines = FileUtils.readLines(filePath);
        } catch (IOException e) {
            System.out.printf("Could not read words list due to: %n%s%n", e);
            return;
        }

        // Print each line
        for (String line : lines) {
            System.out.printf("%s%n", line);
        }
    }

    private void part3() {
        System.out.printf("%nReading from remote: %s%n", remoteURL);

        // Try to load URL for remote page
        try {
            // Create the URL
            remoteFile = new URL(remoteURL);
        } catch (MalformedURLException e) {
            System.out.printf("Unable to retrieve remote resource due to: %n%s%n", e);
            return;
        }

        // Load resource to buffer
        BufferedReader file;
        try {
            file = new BufferedReader(new InputStreamReader(remoteFile.openStream()));
        } catch (IOException e) {
            System.out.printf("Unable to buffer remote resource due to: %n%s%n", e);
            return;
        }

        // Parse buffer for the word "Canada"
        String line;
        while ((line = readLineOrNull(file)) != null) {

            // Split line into words
            String[] words = line.split(" ");

            // Iter each word and check for match
            for (String word : words) {
                if (word.equals(remoteWord)) {
                    System.out.printf("Found \"%s\" in %s%n", remoteWord, remoteURL);
                    return;
                }
            }
        }

        System.out.printf("Could not find \"%s\" in remote file%n", remoteWord);

    }

    /**
     * Wrapper to BufferedReader.readLine that will just return null if there is a
     * read error
     * 
     * @param reader {@link BufferedReader}
     * @return Line
     */
    private String readLineOrNull(BufferedReader reader) {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }
}