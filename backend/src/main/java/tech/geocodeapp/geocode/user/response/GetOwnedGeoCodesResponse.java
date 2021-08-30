package tech.geocodeapp.geocode.user.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import tech.geocodeapp.geocode.general.response.Response;

import java.util.List;
import java.util.UUID;

/**
 * GetOwnedGeoCodesResponse
 */
public class GetOwnedGeoCodesResponse extends Response {
  @JsonProperty("geocodeIDs")
  private List<UUID> geocodeIDs;

  public GetOwnedGeoCodesResponse(boolean success, String message, List<UUID> geocodeIDs) {
    super(success, message);
    this.geocodeIDs = geocodeIDs;
  }

  public List<UUID> getGeocodeIDs() {
    return geocodeIDs;
  }
}
