package tech.geocodeapp.geocode.mission.response;

import tech.geocodeapp.geocode.general.response.Response;

public class UpdateCompletionResponse extends Response {
    public UpdateCompletionResponse(boolean success, String message){
        super(success, message);
    }
}
