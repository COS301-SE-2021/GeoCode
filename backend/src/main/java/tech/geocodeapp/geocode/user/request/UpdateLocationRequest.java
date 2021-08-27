package tech.geocodeapp.geocode.user.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import tech.geocodeapp.geocode.geocode.model.GeoPoint;

import java.util.UUID;

/**
 * UpdateLocationRequest
 */
public class UpdateLocationRequest   {
  @JsonProperty("userID")
  private UUID userID = null;

  @JsonProperty("location")
  private GeoPoint location = null;

  public UUID getUserID() {
    return userID;
  }

  public void setUserID(UUID userID) {
    this.userID = userID;
  }

  public GeoPoint getLocation() {
    return location;
  }

  public void setLocation(GeoPoint location) {
    this.location = location;
  }
}
