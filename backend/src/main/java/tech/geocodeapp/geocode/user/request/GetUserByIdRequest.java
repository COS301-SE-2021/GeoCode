package tech.geocodeapp.geocode.user.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;

/**
 * GetUserByIdRequest
 */
public class GetUserByIdRequest   {
    @JsonProperty("userID")
    private UUID userID = null;

    public GetUserByIdRequest() {}

    public GetUserByIdRequest(UUID userID){
        this.userID = userID;
    }

    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }
}
