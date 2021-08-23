package tech.geocodeapp.geocode.user.request;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * GetMyLeaderboardsRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-07-05T09:14:58.803Z[GMT]")


public class GetMyLeaderboardsRequest   {
  @JsonProperty("userID")
  private UUID userID = null;

  public GetMyLeaderboardsRequest(){

  }

  public GetMyLeaderboardsRequest(UUID userID) {
    this.userID = userID;
  }

  public GetMyLeaderboardsRequest userID(UUID userID) {
    this.userID = userID;
    return this;
  }

  /**
   * Get userID
   * @return userID
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public UUID getUserID() {
    return userID;
  }

  public void setUserID(UUID userID) {
    this.userID = userID;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetMyLeaderboardsRequest getMyLeaderboardsRequest = (GetMyLeaderboardsRequest) o;
    return Objects.equals(this.userID, getMyLeaderboardsRequest.userID);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userID);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetMyLeaderboardsRequest {\n");
    
    sb.append("    userID: ").append(toIndentedString(userID)).append("\n");
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
