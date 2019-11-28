package ca.retrylife.ics4u.loadingicon.components;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.awt.Color;
import java.awt.Graphics;

import org.graalvm.compiler.loop.MathUtil;

import ca.retrylife.libics.math.MathUtils;

@Data
public class Container {
    @Setter @Getter 
    private int x, y, width, height;
    private int opacity = 100;

    public Container(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void setOpacity(double percent) {
        opacity = (int) Math.round(MathUtils.map(percent, 0, 100, 0, 255));
    }



    public void draw(@NonNull Graphics g) {
        g.setColor(new Color(255,255,255,opacity));
        g.fillRect(x, y, width, height);
        // g.fillOval(x - (height/2), y, height, height);
        // g.fillOval(x - (height/2) + width, y, height, height);
    
    }
}