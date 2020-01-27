package exam2a;

import java.util.ArrayList;
import java.util.Comparator;

import exam2a.Cow.CowColor;

public class CowTest {

    // All cows
    private ArrayList<Cow> cows = new ArrayList<>();

    public static void main(String[] args) {
        new CowTest();
    }

    private CowTest() {

        // Create 8 cows
        cows.add(new Cow("Moo", 800, CowColor.WHITE, true));
        cows.add(new Cow("Meow", 1250, CowColor.BLACK, false));
        cows.add(new Cow("Carter", 1280, CowColor.BLACK, true));
        cows.add(new Cow("Willa", 1000, CowColor.VANTABLACK, true));
        cows.add(new Cow("Dinner", 1400, CowColor.RAINBOW, true));
        cows.add(new Cow("Donald trump", 1150, CowColor.INVISIBLE, false));
        cows.add(new Cow("Tweeet", 1110, CowColor.WHITE, false));
        cows.add(new Cow("Moo 2", 1240, CowColor.BROWN, true));

        // Print cow stats
        System.out.println("Stats:");
        cows.forEach((cow) -> {
            System.out.println(cow);
        });

        // List all un-vaccinated cows
        System.out.println("\nUn-vaccinated cows:");
        for (Cow cow : cows) {
            if (!cow.isVaccinated()) {
                System.out.println(cow.getName());
            }
        }

        // Find and exterminate the first white cow
        for (Cow cow : cows) {
            if (cow.getColor().equals(CowColor.WHITE)) {
                cows.remove(cow);
                break;
            }
        }

        // Build a comparator for comparing cows
        Comparator<Cow> cowCompare = new Comparator<Cow>() {
            public int compare(Cow a, Cow b) {

                // Determine which cow is heavier
                return (int) Math.signum(a.getWeight() - b.getWeight()) * -1;
            }
        };

        // Sort the cows by weight
        cows.sort(cowCompare);

        // Remove the fat cow
        cows.remove(0);

        // Print cow stats
        System.out.println("\nNew stats:");
        cows.forEach((cow) -> {
            System.out.println(cow);
        });

    }

}