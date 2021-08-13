package tech.geocodeapp.geocode.user.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
  @JsonProperty("message")
  private Object getMyMissionsResponseMessage = null;

  @JsonProperty("missions")
  @Valid
  private List<Mission> missions = null;

  public GetMyMissionsResponse getMyMissionsResponseMessage(Object getMyMissionsResponseMessage) {
    this.getMyMissionsResponseMessage = getMyMissionsResponseMessage;
    return this;
  }

  /**
   * Get getMyMissionsResponseMessage
   * @return getMyMissionsResponseMessage
   **/
  @Schema(example = "User Missions returned", description = "")
  
    public Object getGetMyMissionsResponseMessage() {
    return getMyMissionsResponseMessage;
  }

  public void getGetMyMissionsResponseMessage(Object getMyMissionsResponseMessage) {
    this.getMyMissionsResponseMessage = getMyMissionsResponseMessage;
  }

  public GetMyMissionsResponse missions(List<Mission> missions) {
    this.missions = missions;
    return this;
  }

  public GetMyMissionsResponse addMissionsItem(Mission missionsItem) {
    if (this.missions == null) {
      this.missions = new ArrayList<Mission>();
    }
    this.missions.add(missionsItem);
    return this;
  }

  /**
   * Get missions
   * @return missions
   **/
  @Schema(description = "")
      @Valid
    public List<Mission> getMissions() {
    return missions;
  }

  public void setMissions(List<Mission> missions) {
    this.missions = missions;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetMyMissionsResponse getMyMissionsResponse = (GetMyMissionsResponse) o;
    return Objects.equals(this.getMyMissionsResponseMessage, getMyMissionsResponse.getMyMissionsResponseMessage) &&
        Objects.equals(this.missions, getMyMissionsResponse.missions) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(getMyMissionsResponseMessage, missions, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetMyMissionsResponse {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    getMyMissionsResponseMessage: ").append(toIndentedString(getMyMissionsResponseMessage)).append("\n");
    sb.append("    missions: ").append(toIndentedString(missions)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
