package tech.geocodeapp.geocode.event.response;

import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonProperty;
import tech.geocodeapp.geocode.general.response.Response;

import javax.validation.constraints.NotNull;
import javax.validation.Valid;

import java.util.Objects;

/**
 * CreateEventResponse object that determines if the Event was created successfully
 */
@Validated
public class CreateEventResponse extends Response {
    /**
     * Overloaded Constructor
     *
     * @param success the status of the Event created
     */
    public CreateEventResponse( boolean success, String message ) {
        super(success, message);
    }
}
