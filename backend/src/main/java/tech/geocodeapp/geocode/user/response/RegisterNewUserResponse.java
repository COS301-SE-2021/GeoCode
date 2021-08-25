package tech.geocodeapp.geocode.user.response;

import tech.geocodeapp.geocode.general.response.Response;
import tech.geocodeapp.geocode.user.model.User;

/**
 * RegisterNewUserResponse
 */
public class RegisterNewUserResponse extends Response {
    private User user;

    public RegisterNewUserResponse(boolean success, String message, User user){
        super(success, message);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
