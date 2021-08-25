package tech.geocodeapp.geocode.image.model;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class Image {

    @Id
    @NotNull
    private UUID id;

    @NotNull
    private byte[] bytes;

    public Image(UUID id, byte[] bytes) {
        this.id = id;
        this.bytes = bytes;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}