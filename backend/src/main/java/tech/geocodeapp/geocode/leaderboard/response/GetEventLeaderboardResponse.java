package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.model.EventLeaderboardDetails;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * GetEventLeaderboardResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-07-05T09:14:58.803Z[GMT]")


public class GetEventLeaderboardResponse   {
  @JsonProperty("success")
  private Boolean success = null;

  @JsonProperty("message")
  private String message = null;

  @JsonProperty("leaderboard")
  @Valid
  private List<EventLeaderboardDetails> leaderboard = new ArrayList<EventLeaderboardDetails>();

  public GetEventLeaderboardResponse success(Boolean success) {
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

  public GetEventLeaderboardResponse message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Get message
   * @return message
   **/
  @Schema(example = "The details for the Event's Leaderboard were successfully returned", required = true, description = "")
      @NotNull

    public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetEventLeaderboardResponse getEventLeaderboardResponse = (GetEventLeaderboardResponse) o;
    return Objects.equals(this.success, getEventLeaderboardResponse.success) &&
        Objects.equals(this.message, getEventLeaderboardResponse.message) &&
        Objects.equals(this.leaderboard, getEventLeaderboardResponse.leaderboard);
  }

  @Override
  public int hashCode() {
    return Objects.hash(success, message, leaderboard);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetEventLeaderboardResponse {\n");
    
    sb.append("    success: ").append(toIndentedString(success)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    leaderboard: ").append(toIndentedString(leaderboard)).append("\n");
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
