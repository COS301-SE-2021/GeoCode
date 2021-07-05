package tech.geocodeapp.geocode.user.response;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import tech.geocodeapp.geocode.collectable.model.Collectable;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import tech.geocodeapp.geocode.collectable.model.Collectable;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * GetCurrentCollectableResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-24T10:12:19.520Z[GMT]")


public class GetCurrentCollectableResponse   {
  @JsonProperty("success")
  private Boolean success = null;

  @JsonProperty("message")
  private String message = null;

  @JsonProperty("Collectable")
  private Collectable collectable = null;

  public GetCurrentCollectableResponse(boolean success, String message, Collectable collectable) {
    this.success = success;
    this.message = message;
    this.collectable = collectable;
  }


  public GetCurrentCollectableResponse success(Boolean success) {
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

  public GetCurrentCollectableResponse message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Get message
   * @return message
   **/
  @Schema(example = "The user's Collectable was successfully returned", required = true, description = "")
      @NotNull

    public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public GetCurrentCollectableResponse collectable(Collectable collectable) {
    this.collectable = collectable;
    return this;
  }

  /**
   * Get collectable
   * @return collectable
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public Collectable getCollectable() {
    return collectable;
  }

  public void setCollectable(Collectable collectable) {
    this.collectable = collectable;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetCurrentCollectableResponse getCurrentCollectableResponse = (GetCurrentCollectableResponse) o;
    return Objects.equals(this.success, getCurrentCollectableResponse.success) &&
        Objects.equals(this.message, getCurrentCollectableResponse.message) &&
        Objects.equals(this.collectable, getCurrentCollectableResponse.collectable);
  }

  @Override
  public int hashCode() {
    return Objects.hash(success, message, collectable);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetCurrentCollectableResponse {\n");
    
    sb.append("    success: ").append(toIndentedString(success)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    collectable: ").append(toIndentedString(collectable)).append("\n");
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
