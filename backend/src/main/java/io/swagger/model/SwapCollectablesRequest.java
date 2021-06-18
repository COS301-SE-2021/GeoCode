package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.model.Collectable;
import io.swagger.model.GeoCode;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * SwapCollectablesRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-17T15:47:52.307Z[GMT]")


public class SwapCollectablesRequest   {
  @JsonProperty("collectable")
  private Collectable collectable = null;

  @JsonProperty("geoCode")
  private GeoCode geoCode = null;

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

  public SwapCollectablesRequest geoCode(GeoCode geoCode) {
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
        Objects.equals(this.geoCode, swapCollectablesRequest.geoCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(collectable, geoCode);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SwapCollectablesRequest {\n");
    
    sb.append("    collectable: ").append(toIndentedString(collectable)).append("\n");
    sb.append("    geoCode: ").append(toIndentedString(geoCode)).append("\n");
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
