package tech.geocodeapp.geocode.User.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import tech.geocodeapp.geocode.Collectable.Model.Collectable;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Objects;

/**
 * SwapCollectableResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-09T21:02:56.988Z[GMT]")


public class SwapCollectableResponse {
  @JsonProperty("Collectable")
  private Collectable collectable = null;
  private boolean success;
  private String message;

  public SwapCollectableResponse(boolean success, String message, Collectable collectable) {
    this.success = success;
    this.message = message;
    this.collectable = collectable;
  }

  public SwapCollectableResponse collectable(Collectable collectable) {
    this.collectable = collectable;
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
   * Get collectable
   * @return collectable
   **/
  @Schema(description = "")
  
    @Valid
    public Collectable getCollectable() {
    return collectable;
  }

  public void setCollectable(Collectable collectable) {
    this.collectable = collectable;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SwapCollectableResponse swapCollectableResponse = (SwapCollectableResponse) o;
    return Objects.equals(this.collectable, swapCollectableResponse.collectable);
  }

  @Override
  public int hashCode() {
    return Objects.hash(collectable);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SwapCollectableResponse {\n");
    
    sb.append("    collectable: ").append(toIndentedString(collectable)).append("\n");
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
