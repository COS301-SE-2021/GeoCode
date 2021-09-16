package tech.geocodeapp.geocode.user.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

/**
 * GetFoundCollectableTypesRequest
 */
public class GetFoundCollectableTypesRequest   {
  @JsonProperty("userID")
  private UUID userID;

  public GetFoundCollectableTypesRequest() {}

  public GetFoundCollectableTypesRequest(UUID userID){
    this.userID = userID;
  }

  public UUID getUserID() {
    return userID;
  }

  public void setUserID(UUID userID) {
    this.userID = userID;
  }
}
