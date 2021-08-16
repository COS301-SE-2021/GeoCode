package tech.geocodeapp.geocode.collectable.response;

import tech.geocodeapp.geocode.collectable.decorator.CollectableTypeComponent;
import tech.geocodeapp.geocode.geocode.model.GeoPoint;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * A Class to return Collectables with their CollectableType mapped to a CollectableTypeComponent
 */
public class CollectableResponse {
    UUID id;
    CollectableTypeComponent type;
    Collection<GeoPoint> pastLocations = new ArrayList<>();
    UUID missionId;

    public CollectableResponse() {
    }

    public CollectableResponse(UUID id, CollectableTypeComponent type, Collection<GeoPoint> pastLocations) {
        this.id = id;
        this.type = type;
        this.pastLocations = pastLocations;
    }

    public CollectableResponse(UUID id, CollectableTypeComponent type, Collection<GeoPoint> pastLocations, UUID missionId) {
        this.id = id;
        this.type = type;
        this.pastLocations = pastLocations;
        this.missionId = missionId;
    }

    public UUID getMissionId() {
        return missionId;
    }

    public void setMissionId(UUID missionId) {
        this.missionId = missionId;
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

    public Collection<GeoPoint> getPastLocations() {
        return pastLocations;
    }

    public void setPastLocations(List<GeoPoint> pastLocations) {
        this.pastLocations = pastLocations;
    }
}
