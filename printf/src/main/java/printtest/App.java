/**
 * ISC4U - PrintF
 * By: Evan Pratten
 */

package printtest;

import java.util.ArrayList;

public class App {

    // Item object
    private class Item {
        String name;
        double price;

        /**
         * Build an item with specific attributes
         * @param name Name of item
         * @param price Cost of item (to 2 decimals)
         */
        public Item(String name, double price) {
            this.name = name;
            this.price = price;
        }
    }

    // List of items we have
    ArrayList<Item> items = new ArrayList<Item>();

    public static void main(String[] args) {
        new App();
    }

    private App() {

        // Push some items with various costs to the item list
        items.add(new Item("Calculator", 117.32));
        items.add(new Item("Sneakers", 33.01));

        // List all items
        listItems(18, 8);

    }

    private void listItems(int item_width, int cost_width) {
        // Calculate width strings to pass to printf
        String header_cfg = "%-" + item_width + "s %-" + cost_width + "s\n";
        String item_cfg = "%-" + item_width + "s $%-" + (cost_width - 2) + ".2f%n";

        // Print out header
        System.out.printf(header_cfg, "Item", "Cost");
        System.out.println("-".repeat(item_width + cost_width + 2));

        // List each item
        for (Item i : items) {
            System.out.printf(item_cfg, i.name, i.price);
        }
    }
}
