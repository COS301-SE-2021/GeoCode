package tech.geocodeapp.geocode.event.request;

import java.util.List;
import java.util.UUID;

public class CheckOutputRequest {
    /**
     * The id for the event
     */
    private UUID eventID;

    /**
     * The output that the User's program generated
     */
    private List<String> outputs;

    /**
     * Default Constructor
     */
    public CheckOutputRequest(){

    }

    /**
     * Overloaded Constructor
     * @param eventID The id for the event
     * @param outputs The output that the User's program generated
     */
    public CheckOutputRequest(UUID eventID, List<String> outputs){

        this.eventID = eventID;
        this.outputs = outputs;
    }

    public UUID getEventID() {
        return eventID;
    }

    public void setEventID(UUID eventID) {
        this.eventID = eventID;
    }

    public List<String> getOutputs() {
        return outputs;
    }

    public void setOutputs(List<String> outputs) {
        this.outputs = outputs;
    }
}
