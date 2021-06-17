package tech.geocodeapp.geocode.User.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.model.Collectable;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * GetUserTrackableResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-17T08:18:28.046Z[GMT]")


public class GetUserTrackableResponse {
  @JsonProperty("Trackable")
  private Collectable trackable = null;

  public GetUserTrackableResponse trackable(Collectable trackable) {
    this.trackable = trackable;
    return this;
  }

  /**
   * Get trackable
   * @return trackable
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public Collectable getTrackable() {
    return trackable;
  }

  public void setTrackable(Collectable trackable) {
    this.trackable = trackable;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetUserTrackableResponse getUserTrackableResponse = (GetUserTrackableResponse) o;
    return Objects.equals(this.trackable, getUserTrackableResponse.trackable);
  }

  @Override
  public int hashCode() {
    return Objects.hash(trackable);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetUserTrackableResponse {\n");
    
    sb.append("    trackable: ").append(toIndentedString(trackable)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
