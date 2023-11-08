package net.gamingtavern.output;

import net.gamingtavern.output.Color;

public class Output {
    private static final Color defaultColor = Color.white;

    public static void newln() {
        System.out.println();
    }

    public static void println(String line) {
        System.out.println(defaultColor + line);
    }

    public static void print(String line) {
        System.out.print(defaultColor + line);
    }
}
