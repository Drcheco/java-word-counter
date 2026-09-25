import java.util.*;
import java.io.*;

public class WordCounter {

    public void run(String[] args) {
        Scanner input = new Scanner(System.in);
        String text = "";

        // Read from file or user input
        if (args.length > 0) {
            try {
                System.out.println("Reading from file: " + args[0]);
                Scanner fileScanner = new Scanner(new File(args[0]));

                while (fileScanner.hasNextLine()) {
                    text = text + fileScanner.nextLine() + " ";
                }

                fileScanner.close();
            } catch (Exception e) {
                System.out.println("Error reading file.");
                return;
            }
        } else {
            System.out.print("Enter text: ");
            text = input.nextLine();
        }

        // Clean text
        text = text.toLowerCase();
        text = text.replaceAll("[^a-zA-Z\\s]", "");

        // Store words
        String[] splitWords = text.split("\\s+");
        ArrayList<String> words = new ArrayList<String>();

        for (int i = 0; i < splitWords.length; i++) {
            if (!splitWords[i].equals("")) {
                words.add(splitWords[i]);
            }
        }

        System.out.println("\nWord list: " + words);
        System.out.println("Total words: " + words.size());

        // Count frequencies
        HashMap<String, Integer> counts = new HashMap<String, Integer>();

        for (int i = 0; i < words.size(); i++) {
            String w = words.get(i);

            if (counts.containsKey(w)) {
                int current = counts.get(w);
                counts.put(w, current + 1);
            } else {
                counts.put(w, 1);
            }
        }

        System.out.println("\nWord counts:");
        for (String key : counts.keySet()) {
            System.out.println("  " + key + ": " + counts.get(key));
        }

        System.out.println("\nUnique words: " + counts.size());

        // Top N words
        System.out.print("\nHow many top words to show? ");
        int n = input.nextInt();
        input.nextLine();

        ArrayList<String> keys = new ArrayList<String>(counts.keySet());

        // Simple selection sort
        for (int i = 0; i < keys.size(); i++) {
            int maxIndex = i;

            for (int j = i + 1; j < keys.size(); j++) {
                if (counts.get(keys.get(j)) > counts.get(keys.get(maxIndex))) {
                    maxIndex = j;
                }
            }

            String temp = keys.get(i);
            keys.set(i, keys.get(maxIndex));
            keys.set(maxIndex, temp);
        }

        System.out.println("\nTop " + n + " words:");

        for (int i = 0; i < n && i < keys.size(); i++) {
            String word = keys.get(i);
            int count = counts.get(word);

            System.out.print((i + 1) + ". " + word + " (" + count + ") ");

            for (int j = 0; j < count; j++) {
                System.out.print("#");
            }

            System.out.println();
        }

        // Lookup loop
        while (true) {
            System.out.print("\nLook up a word (or quit): ");
            String search = input.nextLine().toLowerCase();

            if (search.equals("quit")) {
                System.out.println("Goodbye!");
                break;
            }

            if (counts.containsKey(search)) {
                System.out.println("\"" + search + "\" appears " + counts.get(search) + " times");
            } else {
                System.out.println("\"" + search + "\" not found");
            }
        }
    }
}