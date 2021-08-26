package tech.geocodeapp.geocode.image.exceptions;

public class DirectoryNotFoundException extends Exception {

    public DirectoryNotFoundException() {
        super( "The specified image directory was not found." );
    }
}
