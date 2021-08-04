package tech.geocodeapp.geocode.leaderboard.response;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import tech.geocodeapp.geocode.general.Response;
import tech.geocodeapp.geocode.leaderboard.model.EventLeaderboardDetails;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * GetEventLeaderboardResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-07-05T09:14:58.803Z[GMT]")


public class GetEventLeaderboardResponse extends Response {

  @JsonProperty("leaderboard")
  @Valid
  private List<EventLeaderboardDetails> leaderboard = new ArrayList<EventLeaderboardDetails>();

  public GetEventLeaderboardResponse(boolean success, String message, List<EventLeaderboardDetails> leaderboard){
    super(success, message);
    this.leaderboard = leaderboard;
  }

  public GetEventLeaderboardResponse leaderboard(List<EventLeaderboardDetails> leaderboard) {
    this.leaderboard = leaderboard;
    return this;
  }

  public GetEventLeaderboardResponse addLeaderboardItem(EventLeaderboardDetails leaderboardItem) {
    this.leaderboard.add(leaderboardItem);
    return this;
  }

  /**
   * Get leaderboard
   * @return leaderboard
   **/
  @Schema(required = true, description = "")
      @NotNull
    @Valid
    public List<EventLeaderboardDetails> getLeaderboard() {
    return leaderboard;
  }

  public void setLeaderboard(List<EventLeaderboardDetails> leaderboard) {
    this.leaderboard = leaderboard;
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
