package ca.retrylife.libics.graphics.sprites;

import ca.retrylife.libics.math.MathUtils;

public class AnimatedSprite {

    private Sprite[] frames;
    private int iteration = 0;
    
    public AnimatedSprite(Sprite... frames) {
        this.frames = frames;
    }

    public int getAnimationLength() {
        return frames.length;
    }
    
    public void iter() {
        iteration++;
        iteration %= getAnimationLength();
    }

    public Sprite getCurrentFrame() {
        return frames[iteration];
    }

    public void repose(int iteration) {
        this.iteration = (int)MathUtils.clamp(iteration, 0, getAnimationLength() - 1);
    }


}