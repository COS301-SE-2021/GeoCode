package tech.geocodeapp.geocode.user.request;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;
import tech.geocodeapp.geocode.geocode.model.GeoPoint;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * UpdateLocationRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-18T17:54:44.315Z[GMT]")


public class UpdateLocationRequest   {
  @JsonProperty("userID")
  private UUID userID = null;

  @JsonProperty("location")
  private GeoPoint location = null;

  public UpdateLocationRequest userID(UUID userID) {
    this.userID = userID;
    return this;
  }

  /**
   * Get userID
   * @return userID
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public UUID getUserID() {
    return userID;
  }

  public void setUserID(UUID userID) {
    this.userID = userID;
  }

  public UpdateLocationRequest location(GeoPoint location) {
    this.location = location;
    return this;
  }

  /**
   * Get location
   * @return location
   **/
  @Schema(required = true, description = "")
      @NotNull

    public GeoPoint getLocation() {
    return location;
  }

  public void setLocation(GeoPoint location) {
    this.location = location;
  }
}
