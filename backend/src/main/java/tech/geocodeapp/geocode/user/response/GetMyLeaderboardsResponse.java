package tech.geocodeapp.geocode.user.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import tech.geocodeapp.geocode.general.response.Response;
import tech.geocodeapp.geocode.leaderboard.model.MyLeaderboardDetails;

import java.util.List;

/**
 * GetMyLeaderboardsResponse
 */
public class GetMyLeaderboardsResponse extends Response {
  @JsonProperty("leaderboards")
  private List<MyLeaderboardDetails> leaderboards;

  public GetMyLeaderboardsResponse(boolean success, String message, List<MyLeaderboardDetails> leaderboards){
    super(success, message);
    this.leaderboards = leaderboards;
  }

  public List<MyLeaderboardDetails> getLeaderboards() {
    return leaderboards;
  }

  public void setLeaderboards(List<MyLeaderboardDetails> leaderboards) {
    this.leaderboards = leaderboards;
  }
}
