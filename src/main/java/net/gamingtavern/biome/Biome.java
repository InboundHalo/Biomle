package net.gamingtavern.biome;

import lombok.Getter;

@Getter
public class Biome {
    String name;
    BiomeType biomeType;

    public Biome(String name, BiomeType biomeType) {
        this.name = name;
        this.biomeType = biomeType;
    }
}
