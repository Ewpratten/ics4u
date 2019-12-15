package ca.retrylife.libics.math.noise;

/**
 * A 1D NoiseMap
 */
public class NoisePlane extends NoiseMap {

    /**
     * Create a NoisePlane of a specified size
     * 
     * @param size Plane size
     */
    public NoisePlane(int size) {
        super(size, 1);
    }
}