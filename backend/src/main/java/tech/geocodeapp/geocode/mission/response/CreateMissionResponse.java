package tech.geocodeapp.geocode.mission.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import tech.geocodeapp.geocode.general.response.Response;
import tech.geocodeapp.geocode.mission.model.Mission;

/**
 * CreateMissionResponse
 */
public class CreateMissionResponse extends Response {
  @JsonProperty("mission")
  private Mission mission;

  public CreateMissionResponse() {}

  public CreateMissionResponse(boolean success, String message, Mission mission) {
    super(success, message);
    this.mission = mission;
  }

  public Mission getMission() {
    return mission;
  }

  public void setMission(Mission mission) {
    this.mission = mission;
  }
}
