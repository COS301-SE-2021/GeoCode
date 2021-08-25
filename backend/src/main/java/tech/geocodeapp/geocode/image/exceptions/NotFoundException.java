package tech.geocodeapp.geocode.image.exceptions;


public class NotFoundException extends Exception {

    public NotFoundException() {
        super( "An image with the specified ID was not found." );
    }
}