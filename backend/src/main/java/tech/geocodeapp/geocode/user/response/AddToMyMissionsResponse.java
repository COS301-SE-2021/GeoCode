package tech.geocodeapp.geocode.user.response;

import tech.geocodeapp.geocode.general.response.Response;

public class AddToMyMissionsResponse extends Response {
    public AddToMyMissionsResponse(boolean success, String message){
        super(success, message);
    }
}
