package net.gamingtavern;

public class Output {
    private static final Color defaultColor = Color.white;

    public static void println(String line) {
        System.out.println(defaultColor + line);
    }

    public static void print(String line) {
        System.out.print(defaultColor + line);
    }
}
