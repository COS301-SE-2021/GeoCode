package tech.geocodeapp.geocode.event.request;

import java.util.UUID;

public abstract class GetEventDetailsByIDRequest {
    /**
     * The id for the Event
     */
    private UUID eventID;

    public GetEventDetailsByIDRequest(UUID eventID){

        this.eventID = eventID;
    }

    public UUID getEventID() {
        return eventID;
    }

    public void setEventID(UUID eventID) {
        this.eventID = eventID;
    }
}
