package tech.geocodeapp.geocode.mission.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import tech.geocodeapp.geocode.collectable.model.Collectable;
import tech.geocodeapp.geocode.geocode.model.GeoPoint;

/**
 * CreateMissionRequest
 */
public class CreateMissionRequest {
  @JsonProperty("collectable")
  private Collectable collectable;

  /**
   * Passed to down from createGeoCode -> createCollectable -> createMission
   */
  @JsonProperty("location")
  private GeoPoint location;

  public CreateMissionRequest(Collectable collectable, GeoPoint location) {
      this.collectable = collectable;
      this.location = location;
    }

    public Collectable getCollectable() {
    return collectable;
  }

  public void setCollectable(Collectable collectable) {
    this.collectable = collectable;
  }

  public void setLocation(GeoPoint location) {
    this.location = location;
  }

  public GeoPoint getLocation(){
    return location;
  }
}
