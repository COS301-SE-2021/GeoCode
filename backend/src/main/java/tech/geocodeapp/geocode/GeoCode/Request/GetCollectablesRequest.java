package tech.geocodeapp.geocode.GeoCode.Request;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * GetCollectablesRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-19T20:33:08.210Z[GMT]")


public class GetCollectablesRequest   {
  @JsonProperty("geoCodeID")
  private UUID geoCodeID = null;

  public GetCollectablesRequest geoCodeID(UUID geoCodeID) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetCollectablesRequest getCollectablesRequest = (GetCollectablesRequest) o;
    return Objects.equals(this.geoCodeID, getCollectablesRequest.geoCodeID);
  }

  @Override
  public int hashCode() {
    return Objects.hash(geoCodeID);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetCollectablesRequest {\n");
    
    sb.append("    geoCodeID: ").append(toIndentedString(geoCodeID)).append("\n");
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
