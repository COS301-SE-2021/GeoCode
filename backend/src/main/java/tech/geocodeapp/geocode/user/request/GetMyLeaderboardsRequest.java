package tech.geocodeapp.geocode.user.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

/**
 * GetMyLeaderboardsRequest
 */
public class GetMyLeaderboardsRequest  {
  @JsonProperty("userID")
  private UUID userID = null;

  public GetMyLeaderboardsRequest(){

  }

  public GetMyLeaderboardsRequest(UUID userID) {
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
