package tech.geocodeapp.geocode.user.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import tech.geocodeapp.geocode.general.response.Response;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * GetFoundGeoCodesResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-24T10:49:36.244Z[GMT]")


public class GetFoundGeoCodesResponse extends Response {
  @JsonProperty("geocodeIDs")
  @Valid
  private List<UUID> geocodeIDs = new ArrayList<UUID>();

  public GetFoundGeoCodesResponse(boolean success, String message, List<UUID> geocodeIDs) {
    super(success, message);
    this.geocodeIDs = geocodeIDs;
  }

  public GetFoundGeoCodesResponse geocodeIDs(List<UUID> geocodeIDs) {
    this.geocodeIDs = geocodeIDs;
    return this;
  }

  public GetFoundGeoCodesResponse addGeocodeIDsItem(UUID geocodeIDsItem) {
    this.geocodeIDs.add(geocodeIDsItem);
    return this;
  }

  /**
   * Get geocodeIDs
   * @return geocodeIDs
   **/
  @Schema(required = true, description = "")
      @NotNull
    @Valid
    public List<UUID> getGeocodeIDs() {
    return geocodeIDs;
  }

  public void setGeocodeIDs(List<UUID> geocodeIDs) {
    this.geocodeIDs = geocodeIDs;
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
