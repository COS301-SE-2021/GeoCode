package tech.geocodeapp.geocode.mission.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import tech.geocodeapp.geocode.geocode.model.GeoPoint;
import tech.geocodeapp.geocode.mission.model.Mission;

public class UpdateCompletionRequest {
    @JsonProperty("mission")
    private Mission mission;

    @JsonProperty("location")
    private GeoPoint location;

    public UpdateCompletionRequest() {}

    public UpdateCompletionRequest(Mission mission, GeoPoint location) {
        this.mission = mission;
        this.location = location;
    }

    public Mission getMission() {
        return mission;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }

    public GeoPoint getLocation() {
        return location;
    }

    public void setLocation(GeoPoint location) {
        this.location = location;
    }
}
