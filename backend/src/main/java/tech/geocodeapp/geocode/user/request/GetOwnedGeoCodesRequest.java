package tech.geocodeapp.geocode.user.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

/**
 * GetOwnedGeoCodesRequest
 */
public class GetOwnedGeoCodesRequest   {
  @JsonProperty("userID")
  private UUID userID = null;

  public GetOwnedGeoCodesRequest(){

  }

  public GetOwnedGeoCodesRequest(UUID userID) {
    this.userID = userID;
  }

  /**
   * Get userID
   * @return userID
   **/
  public UUID getUserID() {
    return userID;
  }

  public void setUserID(UUID userID) {
    this.userID = userID;
  }
}
