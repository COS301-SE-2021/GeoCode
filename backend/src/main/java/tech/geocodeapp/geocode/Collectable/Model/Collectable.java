package tech.geocodeapp.geocode.Collectable.Model;

public class Collectable {
    private String image;
    private String name;
    private Rarity rarity;

    public Collectable() {
    }

    public Collectable(String image, String name, Rarity rarity) {
        this.image = image;
        this.name = name;
        this.rarity = rarity;
    }
}
