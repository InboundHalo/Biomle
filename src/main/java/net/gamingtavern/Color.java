package net.gamingtavern;

public enum Color {
    white("97"),
    green("32"),
    red("31");

    private final String color;

    Color(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "\033[" + color + "m";
    }
}
