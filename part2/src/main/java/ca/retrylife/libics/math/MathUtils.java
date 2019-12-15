package ca.retrylife.libics.math;

public class MathUtils {

    /**
     * Returns value clamped between low and high boundaries.
     *
     * @param value Value to clamp.
     * @param low   The lower boundary to which to clamp value.
     * @param high  The higher boundary to which to clamp value.
     */
    public static double clamp(double value, double low, double high) {
        return Math.max(low, Math.min(value, high));
    }

    /**
     * Determine if a value is within a range
     * 
     * @param val Value
     * @param min Minimum value - 1
     * @param max Maximum value + 1
     * @return Is in range
     */
    public static boolean inRange(double val, double min, double max) {
        return (val >= min) && (val <= max);
    }

    /**
     * Map a value linearly from one range to another
     * 
     * @param x       Value
     * @param in_min  Lowest expected input
     * @param in_max  Highest expected input
     * @param out_min Lowest output
     * @param out_max Highest output
     * @return Output
     */
    public static double map(double x, double in_min, double in_max, double out_min, double out_max) {
        return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
    }
}