package tech.geocodeapp.geocode.user.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import tech.geocodeapp.geocode.leaderboard.model.MyLeaderboardDetails;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * GetMyLeaderboardsResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-07-05T09:14:58.803Z[GMT]")


public class GetMyLeaderboardsResponse   {
  @JsonProperty("success")
  private Boolean success = null;

  @JsonProperty("message")
  private String message = null;

  @JsonProperty("leaderboards")
  @Valid
  private List<MyLeaderboardDetails> leaderboards = new ArrayList<MyLeaderboardDetails>();

  public GetMyLeaderboardsResponse(boolean success, String message, List<MyLeaderboardDetails> leaderboards){
    this.success = success;
    this.message = message;
    this.leaderboards = leaderboards;
  }

  public GetMyLeaderboardsResponse success(Boolean success) {
    this.success = success;
    return this;
  }

  /**
   * Get success
   * @return success
   **/
  @Schema(required = true, description = "")
      @NotNull

    public Boolean isSuccess() {
    return success;
  }

  public void setSuccess(Boolean success) {
    this.success = success;
  }

  public GetMyLeaderboardsResponse message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Get message
   * @return message
   **/
  @Schema(example = "The details for the User's Leaderboards were successfully returned", required = true, description = "")
      @NotNull

    public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetMyLeaderboardsResponse getMyLeaderboardsResponse = (GetMyLeaderboardsResponse) o;
    return Objects.equals(this.success, getMyLeaderboardsResponse.success) &&
        Objects.equals(this.message, getMyLeaderboardsResponse.message) &&
        Objects.equals(this.leaderboards, getMyLeaderboardsResponse.leaderboards);
  }

  @Override
  public int hashCode() {
    return Objects.hash(success, message, leaderboards);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetMyLeaderboardsResponse {\n");
    
    sb.append("    success: ").append(toIndentedString(success)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    leaderboards: ").append(toIndentedString(leaderboards)).append("\n");
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
