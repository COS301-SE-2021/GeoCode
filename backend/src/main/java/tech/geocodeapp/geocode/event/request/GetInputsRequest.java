package tech.geocodeapp.geocode.event.request;

import java.util.UUID;

public class GetInputsRequest extends GetEventDetailsByIDRequest{

    public GetInputsRequest(UUID eventID){
        super(eventID);
    }
}
