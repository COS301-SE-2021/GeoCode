package tech.geocodeapp.geocode.event.request;

import java.util.UUID;

public class GetBlocksRequest extends GetEventDetailsByIDRequest{

    public GetBlocksRequest(){
        super();
    }

    public GetBlocksRequest(UUID eventID){
        super(eventID);
    }
}
