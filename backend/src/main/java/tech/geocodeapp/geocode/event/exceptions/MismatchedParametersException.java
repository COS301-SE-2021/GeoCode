package tech.geocodeapp.geocode.event.exceptions;

public class MismatchedParametersException extends java.lang.Exception {

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     */
    public MismatchedParametersException() {
        super( "Some parameters of the given request do not match." );
    }

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public MismatchedParametersException( String message ) {
        super( message );
    }

}
