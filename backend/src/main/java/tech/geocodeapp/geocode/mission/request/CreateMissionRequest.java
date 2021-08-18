package tech.geocodeapp.geocode.mission.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;
import tech.geocodeapp.geocode.collectable.model.Collectable;
import tech.geocodeapp.geocode.geocode.model.GeoPoint;

/**
 * CreateMissionRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-08-13T15:58:34.861Z[GMT]")


public class CreateMissionRequest   {
  @JsonProperty("collectable")
  private Collectable collectable = null;

  /**
   * Passed to down from createGeoCode -> createCollectable -> createMission
   */
  @JsonProperty("location")
  private GeoPoint location;

  public CreateMissionRequest(Collectable collectable){
    this.collectable = collectable;
  }

    public CreateMissionRequest(Collectable savedCollectable, GeoPoint location) {
      this.collectable = savedCollectable;
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
