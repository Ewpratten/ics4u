package exam2a;

import javax.annotation.Nonnull;

/**
 * A very moo-ey cow. (They go moo)
 */
public class Cow {

    /**
     * Colors of cows
     */
    public enum CowColor {
        WHITE, BROWN, RAINBOW, BLACK, INVISIBLE, VANTABLACK
    }

    // Cow's name
    private String name;

    // Cow's weight in Kg
    private double weight;

    // Cow's color
    private CowColor color;

    // Has the cow been vaccinated?
    private boolean vaccinated;

    public Cow(String name, double weight, @Nonnull CowColor color, boolean vaccinated) {
        // Set locals
        this.name = name;
        this.color = color;
        this.vaccinated = vaccinated;

        // Ensure valid weight
        this.weight = (weight > 0) ? weight : 0;
    }

    @Override
    public String toString() {
        return String.format("Cow<Name: \"%s\", Weight: %.2f, Color: %s, Vaccinated: %s>", this.name, this.weight,
                this.color.toString(), (this.vaccinated) ? "YES" : "NO");
    }

    /**
     * Get if the cow has been vaccinated
     * 
     * @return Is vaccinated?
     */
    public boolean isVaccinated() {
        return this.vaccinated;
    }

    /**
     * Get the cow's name
     * 
     * @return Name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get the cow's color
     * 
     * @return Color
     */
    public CowColor getColor() {
        return this.color;
    }

    /**
     * Get the cow's weight
     * 
     * @return Cow weight
     */
    public double getWeight() {
        return this.weight;
    }

}