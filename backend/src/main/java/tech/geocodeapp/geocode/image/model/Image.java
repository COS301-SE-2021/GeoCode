package tech.geocodeapp.geocode.image.model;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.IOException;

public class Image {

    @Id
    @NotNull
    private String fileName;

    @NotNull
    private byte[] bytes;

    private ImageFormat format;

    public Image(String fileName, byte[] bytes, ImageFormat format) throws IOException {
        this.fileName = fileName;
        this.bytes = bytes;
        this.format = format;
    }

    public Image(String fileName, byte[] bytes) {
        this.fileName = fileName;
        this.bytes = bytes;
        this.format = ImageFormat.fromBytes( bytes );
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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
}