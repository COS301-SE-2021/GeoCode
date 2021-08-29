package tech.geocodeapp.geocode.image.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLConnection;

public enum ImageFormat {

    PNG( "png" ),
    GIF( "gif" );

    private String extension;

    ImageFormat(String extension) {
        this.extension = extension;
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
}
