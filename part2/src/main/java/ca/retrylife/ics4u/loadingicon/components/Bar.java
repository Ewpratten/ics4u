package ca.retrylife.ics4u.loadingicon.components;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.awt.Color;
import java.awt.Graphics;

@Data
public class Bar {
    @Setter @Getter 
    private int x, y, width, height;

    public Bar(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }


    public void draw(@NonNull Graphics g) {
        g.setColor(Color.black);
        g.fillRect(x, y, width, height);
        // g.fillOval(x - (height/2), y, height, height);
        // g.fillOval(x - (height/2) + width, y, height, height);
    
    }
}