package tech.geocodeapp.geocode.geocode.exceptions;

public class NotFoundException extends java.lang.Exception {

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     */
    public NotFoundException() {

        super( "The GeoCode was not found" );
    }

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     * @param isEmpty determines if the request was empty or not
     */
    public NotFoundException( Boolean isEmpty ) {

        super( "The GeoCode is empty." );
    }

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public NotFoundException( String message ) {

        super( message );
    }

}