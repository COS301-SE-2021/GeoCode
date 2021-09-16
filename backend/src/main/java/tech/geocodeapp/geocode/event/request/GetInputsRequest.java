package tech.geocodeapp.geocode.event.request;

import java.util.UUID;

public class GetInputsRequest extends GetEventDetailsByIDRequest{

    /**
     * Default Constructor
     */
    public GetInputsRequest(){
        super();
    }

    public GetInputsRequest(UUID eventID){
        super(eventID);
    }
}
