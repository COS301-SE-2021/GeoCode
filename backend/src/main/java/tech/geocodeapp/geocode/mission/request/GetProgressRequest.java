package tech.geocodeapp.geocode.mission.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

/**
 * GetProgressRequest
 */
public class GetProgressRequest   {
  @JsonProperty("missionID")
  private UUID missionID = null;

  public GetProgressRequest() {}

  public UUID getMissionID() {
    return missionID;
  }
}
