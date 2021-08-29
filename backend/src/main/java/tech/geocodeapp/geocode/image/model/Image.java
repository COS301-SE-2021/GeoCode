package tech.geocodeapp.geocode.image.model;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.UUID;

public class Image {

    @Id
    @NotNull
    private UUID id;

    @NotNull
    private byte[] bytes;

    private ImageFormat format;

    public Image(UUID id, byte[] bytes) throws IOException {
        this.id = id;
        this.bytes = bytes;
        this.format = ImageFormat.fromBytes( bytes );
    }

    public Image(UUID id, byte[] bytes, ImageFormat format) {
        this.id = id;
        this.bytes = bytes;
        this.format = format;
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

    public ImageFormat getFormat() {
        return format;
    }

    public void setFormat(ImageFormat format) {
        this.format = format;
    }

    public String getFileName() {
        return id.toString()+"."+format.toString();
    }
}