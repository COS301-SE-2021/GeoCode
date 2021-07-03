package tech.geocodeapp.geocode.user.exception;

public class NullUserRequestParameterException extends java.lang.Exception{
    /**
     * Message for if the User request object had any null parameters
     */
    public NullUserRequestParameterException() {
        super("The given request has NULL parameter(s).");
    }
}
