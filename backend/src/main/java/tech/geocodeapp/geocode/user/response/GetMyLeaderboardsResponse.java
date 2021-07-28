package tech.geocodeapp.geocode.user.response;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import tech.geocodeapp.geocode.general.Response;
import tech.geocodeapp.geocode.leaderboard.model.MyLeaderboardDetails;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * GetMyLeaderboardsResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-07-05T09:14:58.803Z[GMT]")


public class GetMyLeaderboardsResponse extends Response {
  @JsonProperty("leaderboards")
  @Valid
  private List<MyLeaderboardDetails> leaderboards = new ArrayList<MyLeaderboardDetails>();

  public GetMyLeaderboardsResponse(boolean success, String message, List<MyLeaderboardDetails> leaderboards){
    super(success, message);
    this.leaderboards = leaderboards;
  }

  public GetMyLeaderboardsResponse leaderboards(List<MyLeaderboardDetails> leaderboards) {
    this.leaderboards = leaderboards;
    return this;
  }

  public GetMyLeaderboardsResponse addLeaderboardsItem(MyLeaderboardDetails leaderboardsItem) {
    this.leaderboards.add(leaderboardsItem);
    return this;
  }

  /**
   * Get leaderboards
   * @return leaderboards
   **/
  @Schema(required = true, description = "")
      @NotNull
    @Valid
    public List<MyLeaderboardDetails> getLeaderboards() {
    return leaderboards;
  }

  public void setLeaderboards(List<MyLeaderboardDetails> leaderboards) {
    this.leaderboards = leaderboards;
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
