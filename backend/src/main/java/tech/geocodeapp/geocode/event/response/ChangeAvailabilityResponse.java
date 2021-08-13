package tech.geocodeapp.geocode.event.response;

import org.springframework.validation.annotation.Validated;
import tech.geocodeapp.geocode.general.response.Response;

/**
 * ChangeAvailabilityResponse object to determine if the specified Event was updated or not
 */
@Validated
public class ChangeAvailabilityResponse extends Response {

    /**
     * Overloaded Constructor
     *
     * @param success if the Event was updated or not
     */
    public ChangeAvailabilityResponse( boolean success, String message ) {
        super(success, message);
    }
}
