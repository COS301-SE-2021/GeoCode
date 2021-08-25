package tech.geocodeapp.geocode.user.response;

import tech.geocodeapp.geocode.general.response.Response;

public class UpdateCompletionResponse extends Response {
    public UpdateCompletionResponse(boolean success, String message){
        super(success, message);
    }
}
