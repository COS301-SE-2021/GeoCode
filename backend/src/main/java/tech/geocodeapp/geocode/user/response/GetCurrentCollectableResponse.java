package tech.geocodeapp.geocode.user.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import tech.geocodeapp.geocode.collectable.model.Collectable;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import tech.geocodeapp.geocode.general.response.Response;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * GetCurrentCollectableResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-24T10:12:19.520Z[GMT]")


public class GetCurrentCollectableResponse extends Response {
  @JsonProperty("collectable")
  private Collectable collectable = null;

  public GetCurrentCollectableResponse(boolean success, String message, Collectable collectable) {
    super(success, message);
    this.collectable = collectable;
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
