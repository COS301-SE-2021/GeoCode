package tech.geocodeapp.geocode.user.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class AddToFoundCollectableTypesRequest {
    @JsonProperty("userID")
    private UUID userID = null;

    @JsonProperty("collectableTypeID")
    private UUID collectableTypeID = null;

    public AddToFoundCollectableTypesRequest(UUID userID, UUID collectableTypeID){
        this.userID = userID;
        this.collectableTypeID = collectableTypeID;
    }

    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    public UUID getCollectableTypeID() {
        return collectableTypeID;
    }

    public void setCollectableTypeID(UUID collectableTypeID) {
        this.collectableTypeID = collectableTypeID;
    }
}
