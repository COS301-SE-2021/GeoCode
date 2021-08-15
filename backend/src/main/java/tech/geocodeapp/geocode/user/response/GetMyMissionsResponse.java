package tech.geocodeapp.geocode.user.response;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import tech.geocodeapp.geocode.general.response.Response;
import tech.geocodeapp.geocode.mission.model.Mission;

import javax.validation.Valid;

/**
 * GetMyMissionsResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-08-12T09:55:42.563Z[GMT]")

public class GetMyMissionsResponse extends Response {
  @JsonProperty("missions")
  @Valid
  private Set<Mission> missions = new HashSet<Mission>();

  public GetMyMissionsResponse(boolean success, String message, Set<Mission> missions) {
    super(success, message);
    this.missions = missions;
  }

  public GetMyMissionsResponse missions(Set<Mission> missions) {
    this.missions = missions;
    return this;
  }

  public GetMyMissionsResponse addMissionsItem(Mission missionsItem) {
    this.missions.add(missionsItem);
    return this;
  }

  /**
   * Get missions
   * @return missions
   **/
  @Schema(description = "")
  @Valid
  public Set<Mission> getMissions() {
    return missions;
  }

  public void setMissions(Set<Mission> missions) {
    this.missions = missions;
  }
}
