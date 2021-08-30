package tech.geocodeapp.geocode.user.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

/**
 * GetMyMissionsRequest
 */
public class GetMyMissionsRequest   {
  @JsonProperty("userID")
  private UUID userID;

  public GetMyMissionsRequest(UUID userID) {
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
