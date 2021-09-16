package tech.geocodeapp.geocode.leaderboard.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

/**
 * GetEventLeaderboardRequest
 */
public class GetEventLeaderboardRequest   {
  @JsonProperty("leaderboardID")
  private UUID leaderboardID;

  @JsonProperty("starting")
  private Integer starting;

  @JsonProperty("count")
  private Integer count;

  public GetEventLeaderboardRequest() {}

  public GetEventLeaderboardRequest(UUID leaderboardID, Integer starting, Integer count) {
    this.leaderboardID = leaderboardID;
    this.starting = starting;
    this.count = count;
  }

  public UUID getLeaderboardID() {
    return leaderboardID;
  }

  public Integer getStarting() {
    return starting;
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }
}
