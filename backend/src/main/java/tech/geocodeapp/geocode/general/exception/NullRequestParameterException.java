package tech.geocodeapp.geocode.general.exception;

public class NullRequestParameterException extends Exception{
    /**
     * Message for if the request object had any null parameters
     */
    public NullRequestParameterException() {
        super("The given request has NULL parameter(s).");
    }
}
