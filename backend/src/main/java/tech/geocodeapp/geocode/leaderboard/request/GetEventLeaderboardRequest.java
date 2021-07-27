package tech.geocodeapp.geocode.leaderboard.request;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * GetEventLeaderboardRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-07-05T09:14:58.803Z[GMT]")


public class GetEventLeaderboardRequest   {
  @JsonProperty("eventID")
  private String eventID = null;

  @JsonProperty("starting")
  private Integer starting = null;

  @JsonProperty("count")
  private Integer count = null;

  public GetEventLeaderboardRequest eventID(String eventID) {
    this.eventID = eventID;
    return this;
  }

  /**
   * Get eventID
   * @return eventID
   **/
  @Schema(example = "054463f2-2f7c-4864-8130-68e5aa79ee7f", required = true, description = "")
      @NotNull

    public String getEventID() {
    return eventID;
  }

  public void setEventID(String eventID) {
    this.eventID = eventID;
  }

  public GetEventLeaderboardRequest starting(Integer starting) {
    this.starting = starting;
    return this;
  }

  /**
   * Get starting
   * @return starting
   **/
  @Schema(example = "5", required = true, description = "")

  public Integer getStarting() {
    return starting;
  }

  public void setStarting(Integer starting) {
    this.starting = starting;
  }

  public GetEventLeaderboardRequest count(Integer count) {
    this.count = count;
    return this;
  }

  /**
   * Get count
   * @return count
   **/
  @Schema(example = "10", required = true, description = "")

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetEventLeaderboardRequest getEventLeaderboardRequest = (GetEventLeaderboardRequest) o;
    return Objects.equals(this.eventID, getEventLeaderboardRequest.eventID) &&
        Objects.equals(this.starting, getEventLeaderboardRequest.starting) &&
        Objects.equals(this.count, getEventLeaderboardRequest.count);
  }

  @Override
  public int hashCode() {
    return Objects.hash(eventID, starting, count);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetEventLeaderboardRequest {\n");
    
    sb.append("    eventID: ").append(toIndentedString(eventID)).append("\n");
    sb.append("    starting: ").append(toIndentedString(starting)).append("\n");
    sb.append("    count: ").append(toIndentedString(count)).append("\n");
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
