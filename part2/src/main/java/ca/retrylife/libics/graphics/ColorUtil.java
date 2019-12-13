package ca.retrylife.libics.graphics;

import java.util.Random;
import java.awt.Color;

public class ColorUtil {
    static Random rand = new Random();

    public static Color genRandColor() {
        return new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
    }
}