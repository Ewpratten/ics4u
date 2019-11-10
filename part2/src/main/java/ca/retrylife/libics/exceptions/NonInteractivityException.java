package ca.retrylife.libics.exceptions;

/**
 * Exception for programs that cannot read from the console, but try to anyways
 */
public class NonInteractivityException extends Exception {
    private static final long serialVersionUID = 8037794123567742616L;

    public NonInteractivityException() {
        super("Program is not interactive, but input was requested");
    }
}