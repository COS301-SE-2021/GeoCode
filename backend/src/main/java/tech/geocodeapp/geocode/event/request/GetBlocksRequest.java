package tech.geocodeapp.geocode.event.request;

import java.util.UUID;

public class GetBlocksRequest {
    /**
     * The id of the Blockly Event
     */
    private UUID eventID;

    public GetBlocksRequest(UUID eventID){
        this.eventID = eventID;
    }

    public UUID getEventID() {
        return eventID;
    }

    public void setEventID(UUID eventID) {
        this.eventID = eventID;
    }
}
