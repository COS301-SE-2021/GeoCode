package tech.geocodeapp.geocode.GeoCode.Response;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import tech.geocodeapp.geocode.GeoCode.Model.GeoCode;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * CreateGeoCodeResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-17T08:18:28.046Z[GMT]")


public class CreateGeoCodeResponse   {
  @JsonProperty("geoCode")
  private GeoCode geoCode = null;

  public CreateGeoCodeResponse geoCode(GeoCode geoCode) {
    this.geoCode = geoCode;
    return this;
  }

  /**
   * Get geoCode
   * @return geoCode
   **/
  @Schema(description = "")
  
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
    CreateGeoCodeResponse createGeoCodeResponse = (CreateGeoCodeResponse) o;
    return Objects.equals(this.geoCode, createGeoCodeResponse.geoCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(geoCode);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateGeoCodeResponse {\n");
    
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