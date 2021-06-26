package tech.geocodeapp.geocode.collectable.request;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * GetCollectableByTypeRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-17T08:18:28.046Z[GMT]")


public class GetCollectableByTypeRequest   {
  @JsonProperty("collectableTypeId")
  private UUID collectableTypeId = null;

  public GetCollectableByTypeRequest collectableTypeId(UUID collectableTypeId) {
    this.collectableTypeId = collectableTypeId;
    return this;
  }

  /**
   * Get collectableTypeId
   * @return collectableTypeId
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public UUID getCollectableTypeId() {
    return collectableTypeId;
  }

  public void setCollectableTypeId(UUID collectableTypeId) {
    this.collectableTypeId = collectableTypeId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetCollectableByTypeRequest getCollectableByTypeRequest = (GetCollectableByTypeRequest) o;
    return Objects.equals(this.collectableTypeId, getCollectableByTypeRequest.collectableTypeId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(collectableTypeId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetCollectableByTypeRequest {\n");
    
    sb.append("    collectableTypeId: ").append(toIndentedString(collectableTypeId)).append("\n");
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
