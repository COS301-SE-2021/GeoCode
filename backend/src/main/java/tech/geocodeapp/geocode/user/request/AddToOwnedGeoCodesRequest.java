package tech.geocodeapp.geocode.user.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class AddToOwnedGeoCodesRequest {
    @JsonProperty("userID")
    private UUID userID = null;

    @JsonProperty("geoCodeID")
    private UUID geoCodeID = null;

    public AddToOwnedGeoCodesRequest(UUID userID, UUID geoCodeID){
        this.userID = userID;
        this.geoCodeID = geoCodeID;
    }

    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    public UUID getGeoCodeID() {
        return geoCodeID;
    }

    public void setGeoCodeID(UUID geoCodeID) {
        this.geoCodeID = geoCodeID;
    }
}