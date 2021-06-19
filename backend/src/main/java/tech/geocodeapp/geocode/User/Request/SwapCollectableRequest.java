package tech.geocodeapp.geocode.User.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

/**
 * SwapCollectableRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-09T21:02:56.988Z[GMT]")


public class SwapCollectableRequest {
  @JsonProperty("userID")
  private UUID userID = null;

  @JsonProperty("targetGeoCodeID")
  private UUID targetGeoCodeID = null;

  @JsonProperty("targetCollectableID")
  private UUID targetCollectableID = null;

  public SwapCollectableRequest userID(UUID userID) {
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

  public SwapCollectableRequest targetGeoCodeID(UUID targetGeoCodeID) {
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

  public SwapCollectableRequest targetCollectableID(UUID targetCollectableID) {
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
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SwapCollectableRequest swapCollectableRequest = (SwapCollectableRequest) o;
    return Objects.equals(this.userID, swapCollectableRequest.userID) &&
        Objects.equals(this.targetGeoCodeID, swapCollectableRequest.targetGeoCodeID) &&
        Objects.equals(this.targetCollectableID, swapCollectableRequest.targetCollectableID);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userID, targetGeoCodeID, targetCollectableID);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SwapCollectableRequest {\n");
    
    sb.append("    userID: ").append(toIndentedString(userID)).append("\n");
    sb.append("    targetGeoCodeID: ").append(toIndentedString(targetGeoCodeID)).append("\n");
    sb.append("    targetCollectableID: ").append(toIndentedString(targetCollectableID)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
