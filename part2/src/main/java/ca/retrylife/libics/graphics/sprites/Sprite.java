package ca.retrylife.libics.graphics.sprites;

import java.awt.image.BufferedImage;

public class Sprite {

    BufferedImage image;
    
    public Sprite(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getImage(){
        return image;
    }
}