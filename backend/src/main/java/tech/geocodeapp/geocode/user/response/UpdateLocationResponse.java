package tech.geocodeapp.geocode.user.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import tech.geocodeapp.geocode.collectable.model.Collectable;
import tech.geocodeapp.geocode.general.response.Response;

/**
 * UpdateLocationResponse
 */
public class UpdateLocationResponse extends Response {
  @JsonProperty("trackable")
  private Collectable trackable;

  public UpdateLocationResponse(boolean success, String message, Collectable trackable) {
    super(success, message);
    this.trackable = trackable;
  }

  public Collectable getTrackable() {
    return trackable;
  }

  public void setTrackable(Collectable trackable) {
    this.trackable = trackable;
  }
}
