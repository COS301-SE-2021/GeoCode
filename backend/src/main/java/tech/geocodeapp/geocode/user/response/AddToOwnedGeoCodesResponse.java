package tech.geocodeapp.geocode.user.response;

import tech.geocodeapp.geocode.general.response.Response;

public class AddToOwnedGeoCodesResponse extends Response {
    public AddToOwnedGeoCodesResponse(boolean success, String message){
        super(success, message);
    }
}
