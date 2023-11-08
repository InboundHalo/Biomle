package net.gamingtavern;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static String[] headers;
    public static Map<String, String[]> biomes;
    public static ArrayList<String> keys;

    public static void main(String[] args) {
        File file = new File("src/main/resources/biomes.csv");
        Scanner scanner;

        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.err.println("The file could not be found!");
            return;
        }

        biomes = new HashMap<>();

        headers = scanner.nextLine().split(",");

        while (scanner.hasNext()) {
            String next = scanner.nextLine();

            String[] strings = next.split(",");

            if (strings.length != headers.length) {
                System.err.println("String " + next + " invalid!");
                continue;
            }

            String key = strings[0];

            biomes.put(key, strings);
        }

        Random random = new Random();

        keys = new ArrayList<>(biomes.keySet());

        int randomNumber = random.nextInt(keys.size());

        gameLoop(biomes.get(keys.get(randomNumber)));
    }

    private static void gameLoop(String[] answer) {
        String[] guess;

        Scanner scanner = new Scanner(System.in);

        boolean correct = false;

        do {
            guess = getGuess(scanner);

            if (Arrays.equals(guess, answer))
                correct = true;

            try {
                printCard(guess, answer);
            } catch (Exception e) {
                System.err.println("Length was incorrect!");
                e.printStackTrace();
            }
        } while (!correct);
    }

    private static void printCard(String[] card1, String[] card2) throws Exception {
        if (card1.length != card2.length || card1.length != headers.length) {
            throw new Exception();
        }

        Output.println("---------------------");

        for (int i = 0; i < headers.length; i++) {
            Color color = Color.red;

            String value = card1[i];

            if (value.equals(card2[i])) {
                color = Color.green;
            }

            Output.println(
                    headers[i]
                    + ": "
                    + color
                    + value
            );
        }

        Output.println("---------------------");
    }



    private static String[] getGuess(Scanner scanner) {
        String guess;

        while (true) {
            Output.print("Type your guess: ");
            guess = scanner.next();

            if (biomes.containsKey(guess)) {
                return biomes.get(guess);
            }

            Output.println(Color.red + "Invalid Biome!");
        }
    }
}