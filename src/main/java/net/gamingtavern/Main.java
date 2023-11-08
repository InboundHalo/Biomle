package net.gamingtavern;

import net.gamingtavern.biome.Biome;
import net.gamingtavern.biome.BiomeType;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static ArrayList<Biome> biomes;


    public static void main(String[] args) {


        //System.out.println(Color.green + "Hello " + Color.red + "world!");

        File file = new File("src/main/resources/biomes.csv");
        Scanner scanner;

        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.err.println("The file could not be found!");
            return;
        }

        biomes = new ArrayList<>();

        scanner.next();

        while (scanner.hasNext()) {
            String next = scanner.next();

            String[] strings = next.split(",");

            String biomeName = strings[0];
            String biomeType = strings[1];

            BiomeType type = BiomeType.valueOf(biomeType);

            Biome biome = new Biome(biomeName, type);

            biomes.add(biome);
        }

        for (Biome biome : biomes) {
            Output.println("The biome of " + biome.getName() + " and its a " + biome.getBiomeType());
        }

        Random random = new Random();

        int randomNumber = random.nextInt(biomes.size());

        gameLoop(biomes.get(randomNumber));
    }

    private static void gameLoop(Biome correctBiome) {
        Biome guess;

        Scanner scanner = new Scanner(System.in);

        while (true) {
            guess = getGuess(scanner);

            if (guess.equals(correctBiome))
                break;

            Output.println("---------------------");

            Output.println("Biome: " + Color.red + guess.getName());

            Color biomeTypeColor;

            if (guess.getBiomeType() == correctBiome.getBiomeType()) {
                biomeTypeColor = Color.green;
            } else {
                biomeTypeColor = Color.red;
            }

            Output.println("Biome Type: " + biomeTypeColor + guess.getBiomeType());

            Output.println("---------------------");

        }

        Output.println("YAY");
    }



    private static Biome getGuess(Scanner scanner) {
        String guess;

        while (true) {
            Output.print("Type your guess: ");
            guess = scanner.next();

            for (Biome biome : biomes) {
                if (biome.getName().equals(guess)) {
                    return biome;
                }
            }

            Output.println(Color.red + "Invalid Biome!");
        }
    }
}