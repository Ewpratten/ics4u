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
import java.util.List;

import ca.retrylife.libics.files.FileUtils;
import ca.retrylife.libics.files.STDIO;
import ca.retrylife.libics.frameworks.Assignment;

public class App extends Assignment {
    /* Configuration constants */
    private final String folderName = "fileIO";
    private final String consoleLogFileName = "consolelog.txt";
    private final String wordFile = "words.txt";

    /* Vars */
    private BufferedReader stdin;
    private FileWriter consoleFile; // This is a wrapper for PrintWriter.. Don't be mad plz
    private String consoleFilePath;

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
}