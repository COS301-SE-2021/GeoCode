package tech.geocodeapp.geocode.user.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;

/**
 * RegisterNewUserRequest
 */
public class RegisterNewUserRequest   {
    @JsonProperty("userID")
    private UUID userID;

    @JsonProperty("username")
    private String username;

    public RegisterNewUserRequest(UUID userID, String username){
        this.userID = userID;
        this.username = username;
    }

    public RegisterNewUserRequest userID(UUID userID) {
        this.userID = userID;
        return this;
    }

    /**
     * Get userID
     * @return userID
     **/
    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }
}
