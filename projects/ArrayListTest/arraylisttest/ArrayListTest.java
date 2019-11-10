package arraylisttest;

/* This program is to test a bit about your knowle-dge of arraylists 
 * (it's a bit too simple to really do this), but it's also to test your
 * skills in logic - getting a method to do what you want it to do. 
 * The specifications are above the two methods.  
 */
import java.util.ArrayList;

public class ArrayListTest {

	ArrayList<String> inventory = new ArrayList<String>();
	String currentRoom = "";

	public static void main(String[] args) {
		new ArrayListTest();
	}

	ArrayListTest() {

		// Assume that the player has been playing for a while and has the following
		// things in his/her inventory
		inventory.add("food");
		inventory.add("water");
		inventory.add("knife");
		inventory.add("newspaper");

		// set current room
		currentRoom = "beach";

		useItem("knife");

		printInventory();

	}

	/*
	 * Write the useItem() method so that it accomplishes the following:
	 * 
	 * 1. the item must be in the inventory, or else print out
	 * "you don't have a _______" (itemname) 2. if itemname == "knife", then 2.a. if
	 * you are in the "barn": 2.a.i print out "You escape from the barn" 2.a.ii
	 * unfortunately, you have to leave the knife behind. Remove the knife from the
	 * inventory. 2.b. if you are in the "forest" 2.b.i if you have the knife but
	 * NOT the axe, then add "sticks" to your inventory. 2.b.ii if you have the axe
	 * as well, then print "you should probably use the axe instead" 2.c if you are
	 * in any other room, print "you can't use that here"
	 */
	void useItem(String itemName) {

		// Ensure user has item
		if (!inventory.contains(itemName)) {
			System.out.printf("You do not have a %s%n", itemName);
			return;
		}
					return;

		// Handle usage of a knife
		if (itemName.equals("knife")) {

			// Handle each room
			switch (currentRoom) {

				// Handle action in barn
				case "barn":
					// Inform the user they escaped
					System.out.println("You escape the barn");

					// Drop the knife
					inventory.remove(inventory.indexOf(itemName));

					break;

				// Handle action in forest
				case "forest":
				
					// Check if an axe should be used
					if (inventory.contains("axe")) {

						// Inform user that they should use an axe
						System.out.println("You should probably use the axe instead");

					} else {
						// Gather sticks
						inventory.add("sticks");
					}

					break;

				default:

					// Inform the user that they are stupid
					System.out.println("You can't use that here");

			}
		}
	}

	/**
	 * Print each inventory item to the console
	 */
	void printInventory() {

		// Iterate each item and print it
		inventory.forEach((item) -> {
			System.out.println(item);
		});
	}
}
