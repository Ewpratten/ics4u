/**
 * ICS4U - Split an integer
 * By: Evan Pratten
 * 
 * Requirements:
 *  - Take a two digit integer and separate the digits (into ints)
 *    e.g. if int n = 83; then make int a = 8 and int b = 3;
 *  - Try and think of 3 different ways to do this (one will involve %)
 */
package splitint;

public class App {

    /**
     * re-implementing the Tuple in java
     */
    private class Tuple {
        int a;
        int b;

        public Tuple(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    public static void main(String[] args) {
        new App();
    }

    private App() {
        // Define the number to work with
        int n = 85;

        // Split the number with strings
        Tuple str_split = splitIntWithStrings(n);

        // Split the number with mod
        Tuple mod_split = splitWithMod(n);

    }

    /**
     * Split an int with strings
     * 
     * @param x 2 digit int
     * @return Tuple with output
     */
    private Tuple splitIntWithStrings(int x) {
        // Build a char array from the int
        String stringy_int = Integer.toString(x);

        // Build and return a tuple with each index of the char array
        return new Tuple(Integer.parseInt(Character.toString(stringy_int.charAt(0))), Integer.parseInt(Character.toString(stringy_int.charAt(1))));

    }

    private Tuple splitWithMod(int x) {
        return new Tuple((int)(x / 10), x % 10);
    }

}
