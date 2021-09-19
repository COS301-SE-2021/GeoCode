package tech.geocodeapp.geocode.mission.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

/**
 * GetProgressRequest
 */
public class GetProgressRequest   {
  @JsonProperty("missionID")
  private UUID missionID;

  public GetProgressRequest() {}

  public GetProgressRequest(UUID missionID) {
    this.missionID = missionID;
  }

  public UUID getMissionID() {
    return missionID;
  }

  public void setMissionID(UUID missionID) {
    this.missionID = missionID;
  }
}
