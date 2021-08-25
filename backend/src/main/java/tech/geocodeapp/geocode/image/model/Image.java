package tech.geocodeapp.geocode.image.model;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.awt.image.BufferedImage;
import java.util.UUID;

public class Image {

    @Id
    @NotNull
    private UUID id;

    @NotNull
    private BufferedImage imageData;

    public Image(UUID id, BufferedImage imageData) {
        this.id = id;
        this.imageData = imageData;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BufferedImage getImageData() {
        return imageData;
    }

    public void setImageData(BufferedImage imageData) {
        this.imageData = imageData;
    }
}