package tech.geocodeapp.geocode.event.response;

import tech.geocodeapp.geocode.general.response.Response;

public class CheckOutputResponse extends Response {

    public CheckOutputResponse(boolean success, String message){
        super(success, message);

    }
}
