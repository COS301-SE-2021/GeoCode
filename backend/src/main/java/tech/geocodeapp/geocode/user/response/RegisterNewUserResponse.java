package tech.geocodeapp.geocode.user.response;

import tech.geocodeapp.geocode.general.Response;

/**
 * RegisterNewUserResponse
 */
public class RegisterNewUserResponse extends Response {

    public RegisterNewUserResponse(boolean success, String message){
        super(success, message);
    }
}
