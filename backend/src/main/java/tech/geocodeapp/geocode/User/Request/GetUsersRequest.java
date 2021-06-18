package tech.geocodeapp.geocode.User.Request;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * GetUsersRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-17T08:18:28.046Z[GMT]")


public class GetUsersRequest   {
  @JsonProperty("adminID")
  private UUID adminID = null;

  public GetUsersRequest adminID(UUID adminID) {
    this.adminID = adminID;
    return this;
  }

  /**
   * Get adminID
   * @return adminID
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public UUID getAdminID() {
    return adminID;
  }

  public void setAdminID(UUID adminID) {
    this.adminID = adminID;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetUsersRequest getUsersRequest = (GetUsersRequest) o;
    return Objects.equals(this.adminID, getUsersRequest.adminID);
  }

  @Override
  public int hashCode() {
    return Objects.hash(adminID);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetUsersRequest {\n");
    
    sb.append("    adminID: ").append(toIndentedString(adminID)).append("\n");
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
