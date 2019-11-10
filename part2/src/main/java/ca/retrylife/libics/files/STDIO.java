package ca.retrylife.libics.files;

import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStreamReader;
import java.util.Scanner;

import ca.retrylife.libics.exceptions.NonInteractivityException;

public class STDIO {

    /**
     * Get Scanner for standard input
     * 
     * @return Scanner
     */
    public static Scanner getInputScanner() {
        return new Scanner(System.in);
    }

    /**
     * Get standard input buffer
     * 
     * @return Input buffer
     */
    public static BufferedReader getInputBuffer() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Get standard input through console (only works in interactive environments)
     * 
     * @return Input console
     * @throws NonInteractivityException
     */
    public static Console getInputConsole() throws NonInteractivityException {
        // Try to create a console
        Console console = System.console();

        // Throw exception if console cannot read from user
        if (console == null) {
            throw new NonInteractivityException();
        }

        return console;

    }
}