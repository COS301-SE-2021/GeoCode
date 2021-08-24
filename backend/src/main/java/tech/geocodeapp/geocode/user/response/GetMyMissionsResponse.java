package tech.geocodeapp.geocode.user.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import tech.geocodeapp.geocode.general.response.Response;
import tech.geocodeapp.geocode.mission.model.Mission;

import java.util.Set;

/**
 * GetMyMissionsResponse
 */
public class GetMyMissionsResponse extends Response {
  @JsonProperty("missions")
  private Set<Mission> missions;

  public GetMyMissionsResponse(boolean success, String message, Set<Mission> missions) {
    super(success, message);
    this.missions = missions;
  }

  public Set<Mission> getMissions() {
    return missions;
  }
}
