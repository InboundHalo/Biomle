package net.gamingtavern;

import net.gamingtavern.output.Color;
import net.gamingtavern.output.Output;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static String[] headers;
    public static Map<String, String[]> biomes;
    public static ArrayList<String> keys;

    public static void main(String[] args) {
        try {
            loadFile();
        } catch (FileNotFoundException e) {
            System.err.println("The file could not be found!");
            return;
        }

        gameLoop();
    }

    private static void loadFile() throws FileNotFoundException{
        File file = new File("src/main/resources/biomes.csv");
        Scanner scanner = new Scanner(file);

        biomes = new HashMap<>();
        headers = scanner.nextLine().split(","); // 12 ms

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

        scanner.close();

        keys = new ArrayList<>(biomes.keySet());
    }

    private static void gameLoop() {
        Scanner scanner = new Scanner(System.in);

        String input;

        do {
            Random random = new Random();
            int randomNumber = random.nextInt(keys.size());

            String biomeName = keys.get(randomNumber);
            int guesses = guessLoop(scanner, biomes.get(biomeName));

            Output.newln();
            Output.println(Color.green + "Congratulations!");
            Output.println("You guessed " + biomeName + " in " + guesses + " guesses!"); // 12 ms
            Output.newln();
            Output.print("Type yes to play again: ");

            input = scanner.next();
        } while (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes"));

        scanner.close();
    }

    private static int guessLoop(Scanner scanner, String[] answer) {
        String[] guess;

        boolean correct = false;
        int guesses = 0;

        do {
            guess = getGuess(scanner);
            guesses++;

            if (Arrays.equals(guess, answer))
                correct = true;

            try {
                printCard(guess, answer);
            } catch (Exception e) {
                System.err.println("Length was incorrect!");
                e.printStackTrace();
            }
        } while (!correct);

        return guesses;
    }

    private static String[] getGuess(Scanner scanner) {
        String guess;

        while (true) {
            Output.print("Type your guess: ");
            guess = scanner.nextLine();

            if (biomes.containsKey(guess)) {
                return biomes.get(guess);
            }

            Output.println(Color.red + "Invalid " + keys.get(0) + "!");
        }
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
}