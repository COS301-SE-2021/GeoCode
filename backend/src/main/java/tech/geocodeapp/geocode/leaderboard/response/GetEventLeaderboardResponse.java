package tech.geocodeapp.geocode.leaderboard.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import tech.geocodeapp.geocode.general.response.Response;
import tech.geocodeapp.geocode.leaderboard.model.EventLeaderboardDetails;

import javax.validation.Valid;
import java.util.List;

/**
 * GetEventLeaderboardResponse
 */
public class GetEventLeaderboardResponse extends Response {

  @JsonProperty("leaderboard")
  @Valid
  private List<EventLeaderboardDetails> leaderboard;

  public GetEventLeaderboardResponse(boolean success, String message, List<EventLeaderboardDetails> leaderboard){
    super(success, message);
    this.leaderboard = leaderboard;
  }

  public GetEventLeaderboardResponse leaderboard(List<EventLeaderboardDetails> leaderboard) {
    this.leaderboard = leaderboard;
    return this;
  }

  public List<EventLeaderboardDetails> getLeaderboard() {
    return leaderboard;
  }

  public void setLeaderboard(List<EventLeaderboardDetails> leaderboard) {
    this.leaderboard = leaderboard;
  }
}
