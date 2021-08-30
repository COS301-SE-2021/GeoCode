package tech.geocodeapp.geocode.user.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import tech.geocodeapp.geocode.general.response.Response;
import tech.geocodeapp.geocode.user.model.User;

/**
 * GetUserByIdResponse
 */
public class GetUserByIdResponse extends Response {
    @JsonProperty("user")
    private User user;

    public GetUserByIdResponse(boolean success, String message, User user){
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
