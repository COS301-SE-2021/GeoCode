package tech.geocodeapp.geocode.User.Response;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * GetOwnedGeoCodesResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-24T10:12:19.520Z[GMT]")


public class GetOwnedGeoCodesResponse   {
  @JsonProperty("geocodeIDs")
  @Valid
  private List<UUID> geocodeIDs = null;

  public GetOwnedGeoCodesResponse geocodeIDs(List<UUID> geocodeIDs) {
    this.geocodeIDs = geocodeIDs;
    return this;
  }

  public GetOwnedGeoCodesResponse addGeocodeIDsItem(UUID geocodeIDsItem) {
    if (this.geocodeIDs == null) {
      this.geocodeIDs = new ArrayList<UUID>();
    }
    this.geocodeIDs.add(geocodeIDsItem);
    return this;
  }

  /**
   * Get geocodeIDs
   * @return geocodeIDs
   **/
  @Schema(description = "")
      @Valid
    public List<UUID> getGeocodeIDs() {
    return geocodeIDs;
  }

  public void setGeocodeIDs(List<UUID> geocodeIDs) {
    this.geocodeIDs = geocodeIDs;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetOwnedGeoCodesResponse getOwnedGeoCodesResponse = (GetOwnedGeoCodesResponse) o;
    return Objects.equals(this.geocodeIDs, getOwnedGeoCodesResponse.geocodeIDs);
  }

  @Override
  public int hashCode() {
    return Objects.hash(geocodeIDs);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetOwnedGeoCodesResponse {\n");
    
    sb.append("    geocodeIDs: ").append(toIndentedString(geocodeIDs)).append("\n");
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
