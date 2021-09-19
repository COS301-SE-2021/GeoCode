package tech.geocodeapp.geocode.image.model;

import org.springframework.http.MediaType;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLConnection;

public enum ImageFormat {

    PNG( "png", MediaType.IMAGE_PNG ),
    GIF( "gif", MediaType.IMAGE_GIF );

    private String extension;
    private MediaType type;

    ImageFormat( String extension, MediaType type ) {
        this.extension = extension;
        this.type = type;
    }

    public String toString() {
        return extension;
    }

    public static ImageFormat fromBytes( byte[] input ) {
        try {
            String format = URLConnection.guessContentTypeFromStream( new ByteArrayInputStream( input ) );
            if (format == null) return null;
            else if (format.equals("image/gif")) return ImageFormat.GIF;
            else if (format.startsWith("image/")) return ImageFormat.PNG;
            else return null;
        } catch (IOException e) {
            return null;
        }
    }

    public MediaType getMimeType() {
        return type;
    }
}
