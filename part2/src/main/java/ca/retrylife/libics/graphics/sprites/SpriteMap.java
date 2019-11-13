package ca.retrylife.libics.graphics.sprites;

import java.awt.image.BufferedImage;
import java.awt.Dimension;

public class SpriteMap {

    Dimension gridSize;
    Dimension spriteSize;
    BufferedImage image;

    public SpriteMap(BufferedImage image, Dimension spriteSize) {

        // Set locals
        this.spriteSize = spriteSize;
        this.image = image;

        // Set the grid size
        this.gridSize = new Dimension((int) (image.getWidth() / spriteSize.getWidth()),
                (int) (image.getHeight() / spriteSize.getHeight()));

    }

    public Sprite getSprite(int x, int y) {
        return new Sprite(image.getSubimage((int) (x * spriteSize.getWidth()),
                (int) (y * spriteSize.getHeight()), (int) spriteSize.getWidth(), (int) spriteSize.getHeight()));
    }

}