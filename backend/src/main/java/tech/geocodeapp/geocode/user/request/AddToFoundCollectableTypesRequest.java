package tech.geocodeapp.geocode.user.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import tech.geocodeapp.geocode.collectable.model.CollectableType;
import tech.geocodeapp.geocode.user.model.User;

import java.util.UUID;

public class AddToFoundCollectableTypesRequest {
    @JsonProperty("user")
    private User user;

    @JsonProperty("collectableType")
    private CollectableType collectableType;

    public AddToFoundCollectableTypesRequest(User user, CollectableType collectableType){
        this.user = user;
        this.collectableType = collectableType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CollectableType getCollectableType() {
        return collectableType;
    }

    public void setCollectableType(CollectableType collectableType) {
        this.collectableType = collectableType;
    }
}
