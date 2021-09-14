package tech.geocodeapp.geocode.event.request;

import java.util.UUID;

public class GetBlocksRequest extends GetEventDetailsByIDRequest{

    public GetBlocksRequest() {}

    public GetBlocksRequest(UUID eventID){
        super(eventID);
    }
}
