package tech.geocodeapp.geocode.Collectable.Model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class Collectable {

    //A link to the image location of the collectable
    private String image;

    @Id
    //The unique name of a collectable
    private String name;

    //Use ORDINAL to lower storage space usage
    //TODO: Look into options that don't break data set if Enums in Rarity are changed
    @Enumerated(EnumType.ORDINAL)
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
