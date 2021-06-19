package tech.geocodeapp.geocode.User.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import tech.geocodeapp.geocode.Collectable.Model.Collectable;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * UpdateLocationResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-17T08:18:28.046Z[GMT]")


public class UpdateLocationResponse {
  @JsonProperty("Trackable")
  private Collectable trackable = null;
  private boolean success;
  private String message;

  public UpdateLocationResponse(boolean success, String message, Collectable trackable) {
    this.success = success;
    this.message = message;
    this.trackable = trackable;
  }

  public UpdateLocationResponse trackable(Collectable trackable) {
    this.trackable = trackable;
    return this;
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
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
    UpdateLocationResponse updateLocationResponse = (UpdateLocationResponse) o;
    return Objects.equals(this.trackable, updateLocationResponse.trackable);
  }

  @Override
  public int hashCode() {
    return Objects.hash(trackable);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UpdateLocationResponse {\n");
    
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