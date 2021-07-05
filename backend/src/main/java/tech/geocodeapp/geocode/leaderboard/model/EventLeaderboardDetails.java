package tech.geocodeapp.geocode.leaderboard.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * EventLeaderboardDetails
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-07-05T09:14:58.803Z[GMT]")


public class EventLeaderboardDetails   {
  @JsonProperty("username")
  private String username = null;

  @JsonProperty("points")
  private BigDecimal points = null;

  @JsonProperty("rank")
  private BigDecimal rank = null;

  public EventLeaderboardDetails username(String username) {
    this.username = username;
    return this;
  }

  /**
   * Get username
   * @return username
   **/
  @Schema(example = "john_smith", required = true, description = "")
      @NotNull

    public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public EventLeaderboardDetails points(BigDecimal points) {
    this.points = points;
    return this;
  }

  /**
   * Get points
   * @return points
   **/
  @Schema(example = "15", required = true, description = "")
      @NotNull

    @Valid
    public BigDecimal getPoints() {
    return points;
  }

  public void setPoints(BigDecimal points) {
    this.points = points;
  }

  public EventLeaderboardDetails rank(BigDecimal rank) {
    this.rank = rank;
    return this;
  }

  /**
   * Get rank
   * @return rank
   **/
  @Schema(example = "5", required = true, description = "")
      @NotNull

    @Valid
    public BigDecimal getRank() {
    return rank;
  }

  public void setRank(BigDecimal rank) {
    this.rank = rank;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EventLeaderboardDetails eventLeaderboardDetails = (EventLeaderboardDetails) o;
    return Objects.equals(this.username, eventLeaderboardDetails.username) &&
        Objects.equals(this.points, eventLeaderboardDetails.points) &&
        Objects.equals(this.rank, eventLeaderboardDetails.rank);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, points, rank);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EventLeaderboardDetails {\n");
    
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    points: ").append(toIndentedString(points)).append("\n");
    sb.append("    rank: ").append(toIndentedString(rank)).append("\n");
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
