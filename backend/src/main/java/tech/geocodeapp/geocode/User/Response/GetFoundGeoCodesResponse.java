package tech.geocodeapp.geocode.User.Response;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import tech.geocodeapp.geocode.GeoCode.Model.GeoCode;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * GetFoundGeoCodesResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-17T08:18:28.046Z[GMT]")


public class GetFoundGeoCodesResponse   {
  @JsonProperty("geocodes")
  @Valid
  private List<GeoCode> geocodes = null;

  public GetFoundGeoCodesResponse geocodes(List<GeoCode> geocodes) {
    this.geocodes = geocodes;
    return this;
  }

  public GetFoundGeoCodesResponse addGeocodesItem(GeoCode geocodesItem) {
    if (this.geocodes == null) {
      this.geocodes = new ArrayList<GeoCode>();
    }
    this.geocodes.add(geocodesItem);
    return this;
  }

  /**
   * Get geocodes
   * @return geocodes
   **/
  @Schema(description = "")
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
    GetFoundGeoCodesResponse getFoundGeoCodesResponse = (GetFoundGeoCodesResponse) o;
    return Objects.equals(this.geocodes, getFoundGeoCodesResponse.geocodes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(geocodes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetFoundGeoCodesResponse {\n");
    
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
