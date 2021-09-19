package tech.geocodeapp.geocode.event.response;

import org.springframework.validation.annotation.Validated;
import tech.geocodeapp.geocode.general.response.Response;
import tech.geocodeapp.geocode.geocode.model.GeoCode;

import java.util.List;
import java.util.UUID;

/**
 * CreateEventResponse object that determines if the Event was created successfully
 */
@Validated
public class CreateEventResponse extends Response {
    private UUID eventID;

    private List<GeoCode> geocodes;

    /**
     * Overloaded Constructor
     *
     * @param success the status of the Event created
     */
    public CreateEventResponse( boolean success, String message ) {
        super(success, message);
    }

    /**
     * Overloaded Constructor
     *
     * @param success the status of the Event created
     */
    public CreateEventResponse( boolean success, String message, UUID eventID, List<GeoCode> geocodes) {
        super(success, message);
        this.eventID = eventID;
        this.geocodes = geocodes;
    }

    public UUID getEventID() {
        return eventID;
    }

    public List<GeoCode> getGeocodes() {
        return geocodes;
    }
}
