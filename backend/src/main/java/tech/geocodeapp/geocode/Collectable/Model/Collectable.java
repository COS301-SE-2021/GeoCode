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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }
}
