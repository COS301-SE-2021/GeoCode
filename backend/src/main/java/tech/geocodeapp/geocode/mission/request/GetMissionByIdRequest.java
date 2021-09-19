package tech.geocodeapp.geocode.mission.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

/**
 * GetMissionByIdRequest
 */
public class GetMissionByIdRequest {
  @JsonProperty("missionID")
  private UUID missionID;

  public GetMissionByIdRequest() {}

  public GetMissionByIdRequest(UUID missionID) {
    this.missionID = missionID;
  }

  public UUID getMissionID() {
    return missionID;
  }

  public void setMissionID(UUID missionID) {
    this.missionID = missionID;
  }
}
