package tech.geocodeapp.geocode.GeoCode.Response;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import tech.geocodeapp.geocode.Collectable.Model.Collectable;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * UpdateLocationResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-19T15:57:07.487Z[GMT]")


public class UpdateLocationResponse   {
  @JsonProperty("success")
  private Boolean success = null;

  @JsonProperty("message")
  private String message = null;

  @JsonProperty("Trackable")
  private Collectable trackable = null;

  public UpdateLocationResponse success(Boolean success) {
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

  public UpdateLocationResponse message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Get message
   * @return message
   **/
  @Schema(example = "The trackable object's location was successfully updated", required = true, description = "")
      @NotNull

    public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public UpdateLocationResponse trackable(Collectable trackable) {
    this.trackable = trackable;
    return this;
  }

  /**
   * Get trackable
   * @return trackable
   **/
  @Schema(description = "")
  
    @Valid
    public Collectable getTrackable() {
    return trackable;
  }

  public void setTrackable(Collectable trackable) {
    this.trackable = trackable;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateLocationResponse updateLocationResponse = (UpdateLocationResponse) o;
    return Objects.equals(this.success, updateLocationResponse.success) &&
        Objects.equals(this.message, updateLocationResponse.message) &&
        Objects.equals(this.trackable, updateLocationResponse.trackable);
  }

  @Override
  public int hashCode() {
    return Objects.hash(success, message, trackable);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UpdateLocationResponse {\n");
    
    sb.append("    success: ").append(toIndentedString(success)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    trackable: ").append(toIndentedString(trackable)).append("\n");
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
