package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.model.GeoCode;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * UpdateAvailabilityRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-17T16:36:22.979Z[GMT]")


public class UpdateAvailabilityRequest   {
  @JsonProperty("geoCode")
  private GeoCode geoCode = null;

  @JsonProperty("available")
  private Boolean available = null;

  public UpdateAvailabilityRequest geoCode(GeoCode geoCode) {
    this.geoCode = geoCode;
    return this;
  }

  /**
   * Get geoCode
   * @return geoCode
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public GeoCode getGeoCode() {
    return geoCode;
  }

  public void setGeoCode(GeoCode geoCode) {
    this.geoCode = geoCode;
  }

  public UpdateAvailabilityRequest available(Boolean available) {
    this.available = available;
    return this;
  }

  /**
   * Get available
   * @return available
   **/
  @Schema(required = true, description = "")
      @NotNull

    public Boolean isAvailable() {
    return available;
  }

  public void setAvailable(Boolean available) {
    this.available = available;
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
    return Objects.equals(this.geoCode, updateAvailabilityRequest.geoCode) &&
        Objects.equals(this.available, updateAvailabilityRequest.available);
  }

  @Override
  public int hashCode() {
    return Objects.hash(geoCode, available);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UpdateAvailabilityRequest {\n");
    
    sb.append("    geoCode: ").append(toIndentedString(geoCode)).append("\n");
    sb.append("    available: ").append(toIndentedString(available)).append("\n");
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
