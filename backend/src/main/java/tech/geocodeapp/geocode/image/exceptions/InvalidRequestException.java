package tech.geocodeapp.geocode.image.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidRequestException extends Exception {

    private final HttpStatus status;

    public InvalidRequestException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
