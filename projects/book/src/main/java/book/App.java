/**
 * ICS3U - char frequency
 * By: Evan Pratten
 * 
 *  - Open a book, and count the letters
 */
package book;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;
import java.util.Map;

public class App {

    HashMap<Character, Integer> freq_mappings = new HashMap<Character, Integer>();

    public static void main(String[] args) {
        new App();
    }

    private App() {

        String book_file = "";

        // Load the file (or exit)
        try {
            book_file = readFile("pg32613.txt");
        } catch (IOException e) {
            System.exit(1);
        }

        // Compute frequency
        computeFrequency(book_file);

        // Sort
        LinkedHashMap<Character, Integer> sorted_map = sortMapping();
        Object[] char_linkages;


        // Print top 5 chars
        System.out.println("-- Top 5 chars --");
        char_linkages = sorted_map.entrySet().toArray();
        for (int i = sorted_map.size() - 1; i >= sorted_map.size() - 5; i--) {
            System.out.println(char_linkages[i].toString());
        }

        // Print bottom 5 chars
        System.out.println("-- Bottom 5 chars --");
        char_linkages = sorted_map.entrySet().toArray();
        for (int i = 0; i <= 5; i++) {
            System.out.println(char_linkages[i].toString());
        }

    }

    private LinkedHashMap<Character, Integer> sortMapping() {
        return freq_mappings.entrySet()
        .stream()
        .sorted(Map.Entry.comparingByValue())
        .collect(
            Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2,
                LinkedHashMap::new));
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
