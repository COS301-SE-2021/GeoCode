package tech.geocodeapp.geocode.leaderboard.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * MyLeaderboardDetails
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-07-05T09:14:58.803Z[GMT]")


public class MyLeaderboardDetails   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("points")
  private BigDecimal points = null;

  @JsonProperty("rank")
  private BigDecimal rank = null;

  public MyLeaderboardDetails name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   **/
  @Schema(example = "Pretoria", required = true, description = "")
      @NotNull

    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public MyLeaderboardDetails points(BigDecimal points) {
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

  public MyLeaderboardDetails rank(BigDecimal rank) {
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
    MyLeaderboardDetails myLeaderboardDetails = (MyLeaderboardDetails) o;
    return Objects.equals(this.name, myLeaderboardDetails.name) &&
        Objects.equals(this.points, myLeaderboardDetails.points) &&
        Objects.equals(this.rank, myLeaderboardDetails.rank);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, points, rank);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MyLeaderboardDetails {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
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
