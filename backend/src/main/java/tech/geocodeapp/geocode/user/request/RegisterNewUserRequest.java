package tech.geocodeapp.geocode.user.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import tech.geocodeapp.geocode.geocode.model.GeoPoint;

import java.util.UUID;

/**
 * RegisterNewUserRequest
 */
public class RegisterNewUserRequest   {
    @JsonProperty("userID")
    private UUID userID;

    @JsonProperty("username")
    private String username;

    /*
    * The location of the new User
    * Used to set the location of their User Trackable
     */
    @JsonProperty("location")
    private GeoPoint location;

    /**
     * Overloaded constructor
     * @param userID The id of the new User
     * @param username The username of the new User
     */
    public RegisterNewUserRequest(UUID userID, String username){
        this.userID = userID;
        this.username = username;
    }

    /**
     * Overloaded constructor
     * @param userID The id of the new User
     * @param username The username of the new User
     */
    public RegisterNewUserRequest(UUID userID, String username, GeoPoint location){
        this.userID = userID;
        this.username = username;
        this.location = location;
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

    public GeoPoint getLocation() {
        return location;
    }

    public void setLocation(GeoPoint location) {
        this.location = location;
    }
}
