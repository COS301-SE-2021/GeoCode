package tech.geocodeapp.geocode.event.request;

import java.util.UUID;

public class GetEventByIDRequest {
    /**
     * The UUID for the wanted Event
     */
    private UUID eventID;

    public GetEventByIDRequest(UUID eventID) {
        this.eventID = eventID;
    }

    public UUID getEventID() {
        return eventID;
    }

    public void setEventID(UUID eventID) {
        this.eventID = eventID;
    }
}
