package tech.geocodeapp.geocode.Collectable.Response;

import tech.geocodeapp.geocode.Collectable.Decorator.CollectableTypeComponent;

import java.util.UUID;

/**
 * A Class to return Collectables with their CollectableType mapped to a CollectableTypeComponent
 */
public class CollectableResponse {
    UUID id;
    CollectableTypeComponent type;

    public CollectableResponse() {
    }

    public CollectableResponse(UUID id, CollectableTypeComponent type) {
        this.id = id;
        this.type = type;
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
}
