package tech.geocodeapp.geocode.GeoCode.Exceptions;

public class InvalidRequestException extends java.lang.Exception {

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     */
    public InvalidRequestException() {

        super( "The given request is missing parameter/s." );
    }

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     * @param isEmpty determines if the request was empty or not
     */
    public InvalidRequestException( Boolean isEmpty ) {

        super( "The given request is empty." );
    }

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public InvalidRequestException( String message ) {

        super( message );
    }

}
