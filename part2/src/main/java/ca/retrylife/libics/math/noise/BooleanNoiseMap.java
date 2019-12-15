package ca.retrylife.libics.math.noise;

public class BooleanNoiseMap extends NoiseMap {

    private int threshold;

    /**
     * Create a BooleanNoiseMap with a specific size, and threshold
     * 
     * @param width     Map width
     * @param height    Map height
     * @param threshold int -> boolean threshold
     */
    public BooleanNoiseMap(int width, int height, int threshold) {
        super(width, height);

        this.threshold = threshold;
    }

    @Override
    public void compute(int n) {
        super.compute(n);

        // Filter down to 1s and 0s
        filter();
    }

    /**
     * Filter internal map from distances to 1s and 0s based on the threshold
     */
    private void filter() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {

                // Set index value based on threshols
                map[i][j] = (map[i][j] <= threshold) ? 1 : 0;
            }
        }
    }

}