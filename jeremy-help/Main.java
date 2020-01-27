import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class FileIO {

	public static void main(String[] args) {

		//Buffered Writer to append text to file, must be declared globally
		PrintWriter pwKeyToFile = null;

		//KEYBOARD reader
		BufferedReader brKey = new BufferedReader (new InputStreamReader (System.in));  //make Buffered Reader

		//URL and URLConnection declared globally to be referenced/used later
		URL page = null;
		URLConnection pageConnection = null;

		
		try {
			page = new URL("https://uwaterloo.ca/math/future-undergraduates/programs");
		} catch (MalformedURLException e2) {
			e2.printStackTrace();
			System.out.println("Error");
			System.exit(0);
		}
		
		
		try {
			pageConnection = page.openConnection();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		
		try {	//Initializes printWriter
			pwKeyToFile = new PrintWriter(new BufferedWriter(new FileWriter ("Text.txt", true)), true);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		
		System.out.println("Type something to add to the file, then type quit when you are done.");


		while(true) {	//Processes keyboard input and appends to file.
			String s = "";
			try {
				s = brKey.readLine ();
			} catch (IOException e) {
				e.printStackTrace();
			} 

			if(s.equals("quit")) {	//this is for keyboard entry
				break;
			}

			pwKeyToFile.println(s);
		}


		//FILE reader
		BufferedReader brFile = null;

		try {	//Initializes FileReader
			brFile = new BufferedReader (new FileReader ("Text.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}


		System.out.println("Current File:");


		while(true) {	//Prints file to console
			String s = "";
			try {
				s = brFile.readLine ();
			} catch (IOException e) {
				e.printStackTrace();
			} 

			if(s == null) {
				System.out.println("End reached (for files, webpages, ...)");
				break;
			}

			System.out.println(s);
		}

		BufferedReader brWeb = null;

		//WEBPAGE reader
		try {  //make Buffered Reader
			brWeb = new BufferedReader (new InputStreamReader (pageConnection.getInputStream()));
		} catch (IOException e) {
			System.out.println("Invalid URL");
		}

		while(true) {
			String s = "";
			try {
				String text = brWeb.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}

			String[] words = s.split(" ");
			for(String word:words) {
				if(word.toLowerCase().equals("canada")) {
					System.out.println("The word \"Canada\" is in the webpage.");
				}
			}
			if(s.equals("")) {
				System.out.println("End reached (for files, webpages, ...)");
				break;
			}
		}

	}

}