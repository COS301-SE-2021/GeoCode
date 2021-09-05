package tech.geocodeapp.geocode.event.request;

import java.util.UUID;

public class SubmitBlocklyCodeRequest {
    /**
     * The id of the Blockly Event
     */
    private UUID eventID;

    /**
     * The JavaScript code that was generated from the Blockly code.
     * This code will be run to check whether the code produces the
     * correct result.
     */
    private String code;

    /**
     * Overloaded Constructor
     * @param eventID The id of the Blockly Event
     * @param code The JavaScript code that was generated from the Blockly code.
     */
    public SubmitBlocklyCodeRequest(UUID eventID, String code){
        this.eventID = eventID;
        this.code = code;
    }

    public UUID getEventID() {
        return eventID;
    }

    public void setEventID(UUID eventID) {
        this.eventID = eventID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
