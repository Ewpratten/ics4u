package ca.retrylife.libics.graphics;

import java.awt.Dimension;
import java.awt.Toolkit;

public class ScreenTools {
    
    public static Dimension getScreenSize() {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }
}