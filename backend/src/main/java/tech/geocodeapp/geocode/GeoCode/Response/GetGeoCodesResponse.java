package tech.geocodeapp.geocode.GeoCode.Response;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import tech.geocodeapp.geocode.GeoCode.Model.GeoCode;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * GetGeoCodesResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-17T08:18:28.046Z[GMT]")


public class GetGeoCodesResponse   {
  @JsonProperty("geocodes")
  @Valid
  private List<GeoCode> geocodes = new ArrayList<GeoCode>();

  public GetGeoCodesResponse geocodes(List<GeoCode> geocodes) {
    this.geocodes = geocodes;
    return this;
  }

  public GetGeoCodesResponse addGeocodesItem(GeoCode geocodesItem) {
    this.geocodes.add(geocodesItem);
    return this;
  }

  /**
   * Get geocodes
   * @return geocodes
   **/
  @Schema(required = true, description = "")
      @NotNull
    @Valid
    public List<GeoCode> getGeocodes() {
    return geocodes;
  }

  public void setGeocodes(List<GeoCode> geocodes) {
    this.geocodes = geocodes;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetGeoCodesResponse getGeoCodesResponse = (GetGeoCodesResponse) o;
    return Objects.equals(this.geocodes, getGeoCodesResponse.geocodes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(geocodes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetGeoCodesResponse {\n");
    
    sb.append("    geocodes: ").append(toIndentedString(geocodes)).append("\n");
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
