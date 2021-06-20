package tech.geocodeapp.geocode.GeoCode.Request;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import tech.geocodeapp.geocode.Collectable.Model.Collectable;

/**
 * SwapCollectablesRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-19T19:40:54.656Z[GMT]")


public class SwapCollectablesRequest   {
  @JsonProperty("targetGeoCodeID")
  private UUID targetGeoCodeID = null;

  @JsonProperty("targetCollectableID")
  private UUID targetCollectableID = null;

  public SwapCollectablesRequest targetGeoCodeID(UUID targetGeoCodeID) {
    this.targetGeoCodeID = targetGeoCodeID;
    return this;
  }

  /**
   * Get targetGeoCodeID
   * @return targetGeoCodeID
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public UUID getTargetGeoCodeID() {
    return targetGeoCodeID;
  }

  public void setTargetGeoCodeID(UUID targetGeoCodeID) {
    this.targetGeoCodeID = targetGeoCodeID;
  }

  public SwapCollectablesRequest targetCollectableID(UUID targetCollectableID) {
    this.targetCollectableID = targetCollectableID;
    return this;
  }

  /**
   * Get targetCollectableID
   * @return targetCollectableID
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public UUID getTargetCollectableID() {
    return targetCollectableID;
  }

  public void setTargetCollectableID(UUID targetCollectableID) {
    this.targetCollectableID = targetCollectableID;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SwapCollectablesRequest swapCollectablesRequest = (SwapCollectablesRequest) o;
    return Objects.equals(this.targetGeoCodeID, swapCollectablesRequest.targetGeoCodeID) &&
        Objects.equals(this.targetCollectableID, swapCollectablesRequest.targetCollectableID);
  }

  @Override
  public int hashCode() {
    return Objects.hash(targetGeoCodeID, targetCollectableID);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SwapCollectablesRequest {\n");
    
    sb.append("    targetGeoCodeID: ").append(toIndentedString(targetGeoCodeID)).append("\n");
    sb.append("    targetCollectableID: ").append(toIndentedString(targetCollectableID)).append("\n");
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
