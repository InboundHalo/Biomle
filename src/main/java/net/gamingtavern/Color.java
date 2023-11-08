package net.gamingtavern;

public enum Color {
    white("\033[97m"),
    green("\033[32m"),
    red("\033[31m"),
    yellow("\033[33m");

    private final String color;

    Color(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return color;
    }
}
