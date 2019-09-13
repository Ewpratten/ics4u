package book;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class App {

    HashMap<Character, Integer> freq_mappings = new HashMap<Character, Integer>();

    public static void main(String[] args) {
        new App();
    }

    private App() {

        String book_file = "";

        try {
            book_file = readFile("pg32613.txt");
        } catch (IOException e) {
            System.exit(1);
        }

        computeFrequency(book_file);

        // System.out.println(book_file);
        dumpMap();

    }

    private void computeFrequency(String data) {
        data = data.toUpperCase();

        for (char x : data.toCharArray()) {
            // Check if a new hash needs to be added
            if (!freq_mappings.containsKey(x)) {
                freq_mappings.put(x, 1);
            } else {
                int last_freq = freq_mappings.get(x);
                freq_mappings.put(x, last_freq + 1);
            }
        }
    }

    private void dumpMap() {
        freq_mappings.entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + " " + entry.getValue());
        });
    }

    /**
     * Reads a file, and returns it's contents as a UTF8 string
     * 
     * @param path File path
     * @return File contents
     */
    private static String readFile(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded);
    }
}
