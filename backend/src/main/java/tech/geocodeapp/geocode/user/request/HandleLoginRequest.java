package tech.geocodeapp.geocode.user.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import tech.geocodeapp.geocode.geocode.model.GeoPoint;

/**
 * RegisterNewUserRequest
 */
public class HandleLoginRequest {
    /*
    * The location of the new User
    * Used to set the location of their User Trackable
     */
    @JsonProperty("location")
    private GeoPoint location;

    public HandleLoginRequest() {}

    /**
     * Overloaded constructor
     * @param location The location of the User's Trackable
     */
    public HandleLoginRequest(GeoPoint location){
        this.location = location;
    }

    public GeoPoint getLocation() {
        return location;
    }

    public void setLocation(GeoPoint location) {
        this.location = location;
    }
}
