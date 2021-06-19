package tech.geocodeapp.geocode.GeoCode.Request;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * UpdateAvailabilityRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-19T15:57:07.487Z[GMT]")


public class UpdateAvailabilityRequest   {
  @JsonProperty("geoCodeID")
  private UUID geoCodeID = null;

  @JsonProperty("isAvailable")
  private Boolean isAvailable = null;

  public UpdateAvailabilityRequest geoCodeID(UUID geoCodeID) {
    this.geoCodeID = geoCodeID;
    return this;
  }

  /**
   * Get geoCodeID
   * @return geoCodeID
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public UUID getGeoCodeID() {
    return geoCodeID;
  }

  public void setGeoCodeID(UUID geoCodeID) {
    this.geoCodeID = geoCodeID;
  }

  public UpdateAvailabilityRequest isAvailable(Boolean isAvailable) {
    this.isAvailable = isAvailable;
    return this;
  }

  /**
   * Get isAvailable
   * @return isAvailable
   **/
  @Schema(required = true, description = "")
      @NotNull

    public Boolean isIsAvailable() {
    return isAvailable;
  }

  public void setIsAvailable(Boolean isAvailable) {
    this.isAvailable = isAvailable;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateAvailabilityRequest updateAvailabilityRequest = (UpdateAvailabilityRequest) o;
    return Objects.equals(this.geoCodeID, updateAvailabilityRequest.geoCodeID) &&
        Objects.equals(this.isAvailable, updateAvailabilityRequest.isAvailable);
  }

  @Override
  public int hashCode() {
    return Objects.hash(geoCodeID, isAvailable);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UpdateAvailabilityRequest {\n");
    
    sb.append("    geoCodeID: ").append(toIndentedString(geoCodeID)).append("\n");
    sb.append("    isAvailable: ").append(toIndentedString(isAvailable)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
