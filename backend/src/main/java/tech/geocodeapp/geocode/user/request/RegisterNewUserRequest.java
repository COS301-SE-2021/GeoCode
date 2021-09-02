package tech.geocodeapp.geocode.user.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import tech.geocodeapp.geocode.geocode.model.GeoPoint;

/**
 * RegisterNewUserRequest
 */
public class RegisterNewUserRequest   {
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
     * @param username The username of the new User
     * @param location The location of the User's Trackable
     */
    public RegisterNewUserRequest(String username, GeoPoint location){
        this.username = username;
        this.location = location;
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
