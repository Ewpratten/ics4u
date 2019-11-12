package ca.retrylife.libics.math;

public class MathUtils{

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

    public static boolean inRange(double val, double min, double max) {
        return (val > min) && (val < max);
    }
}