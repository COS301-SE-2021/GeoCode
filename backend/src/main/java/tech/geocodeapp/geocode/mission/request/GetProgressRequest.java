package tech.geocodeapp.geocode.mission.request;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * GetProgressRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-08-12T09:55:42.563Z[GMT]")


public class GetProgressRequest   {
  @JsonProperty("missionID")
  private UUID missionID = null;

  public GetProgressRequest missionID(UUID missionID) {
    this.missionID = missionID;
    return this;
  }

  /**
   * Get missionID
   * @return missionID
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public UUID getMissionID() {
    return missionID;
  }

  public void setMissionID(UUID missionID) {
    this.missionID = missionID;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetProgressRequest getProgressRequest = (GetProgressRequest) o;
    return Objects.equals(this.missionID, getProgressRequest.missionID);
  }

  @Override
  public int hashCode() {
    return Objects.hash(missionID);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetProgressRequest {\n");
    
    sb.append("    missionID: ").append(toIndentedString(missionID)).append("\n");
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
