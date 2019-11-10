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

import ca.retrylife.libics.files.FileUtils;
import ca.retrylife.libics.files.STDIO;
import ca.retrylife.libics.frameworks.Assignment;

public class App extends Assignment {
    /* Configuration constants */
    private final String folderName = "fileIO";
    private final String consoleLogFileName = "consolelog.txt";

    /* Vars */
    BufferedReader stdin;
    FileWriter consoleFile; // This is a wrapper for PrintWriter.. Don't be mad plz

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
        String consoleFilePath = FileUtils.constructPathString(folderName, consoleLogFileName);

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

    }
}