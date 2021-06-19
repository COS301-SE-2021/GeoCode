package tech.geocodeapp.geocode.GeoCode.Request;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import tech.geocodeapp.geocode.Collectable.Model.Collectable;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * SwapCollectablesRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-18T04:32:33.769Z[GMT]")


public class SwapCollectablesRequest   {
  @JsonProperty("collectable")
  private Collectable collectable = null;

  @JsonProperty("geoCodeID")
  private UUID geoCodeID = null;

  public SwapCollectablesRequest collectable(Collectable collectable) {
    this.collectable = collectable;
    return this;
  }

  /**
   * Get collectable
   * @return collectable
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public Collectable getCollectable() {
    return collectable;
  }

  public void setCollectable(Collectable collectable) {
    this.collectable = collectable;
  }

  public SwapCollectablesRequest geoCodeID(UUID geoCodeID) {
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
    SwapCollectablesRequest swapCollectablesRequest = (SwapCollectablesRequest) o;
    return Objects.equals(this.collectable, swapCollectablesRequest.collectable) &&
        Objects.equals(this.geoCodeID, swapCollectablesRequest.geoCodeID);
  }

  @Override
  public int hashCode() {
    return Objects.hash(collectable, geoCodeID);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SwapCollectablesRequest {\n");
    
    sb.append("    collectable: ").append(toIndentedString(collectable)).append("\n");
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
