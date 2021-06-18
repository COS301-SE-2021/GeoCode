package tech.geocodeapp.geocode.Collectable.Response;

import tech.geocodeapp.geocode.Collectable.Decorator.CollectableTypeComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * A Class to return Collectables with their CollectableType mapped to a CollectableTypeComponent
 */
public class CollectableResponse {
    UUID id;
    CollectableTypeComponent type;
    List<String> pastLocations = new ArrayList<>();

    public CollectableResponse() {
    }

    public CollectableResponse(UUID id, CollectableTypeComponent type, List<String> pastLocations) {
        this.id = id;
        this.type = type;
        this.pastLocations = pastLocations;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public CollectableTypeComponent getType() {
        return type;
    }

    public void setType(CollectableTypeComponent type) {
        this.type = type;
    }

    public List<String> getPastLocations() {
        return pastLocations;
    }

    public void setPastLocations(List<String> pastLocations) {
        this.pastLocations = pastLocations;
    }
}
