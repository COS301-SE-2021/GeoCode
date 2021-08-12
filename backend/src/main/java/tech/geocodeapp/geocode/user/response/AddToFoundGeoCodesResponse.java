package tech.geocodeapp.geocode.user.response;

import tech.geocodeapp.geocode.general.response.Response;

public class AddToFoundGeoCodesResponse extends Response {
    public AddToFoundGeoCodesResponse(boolean success, String message){
        super(success, message);
    }
}
