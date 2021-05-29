package tech.geocodeapp.geocode.Collectable.Model;

public class Collectable {
    //A link to the image location of the collectable
    private String image;
    //The unique name of a collectable
    private String name;
    //Enum set to the rarity of a collectable
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
