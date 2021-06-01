package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * GetCollectableTypeBySetRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-01T18:53:58.734Z[GMT]")


public class GetCollectableTypeBySetRequest   {
  @JsonProperty("setId")
  private UUID setId = null;

  public GetCollectableTypeBySetRequest setId(UUID setId) {
    this.setId = setId;
    return this;
  }

  /**
   * Get setId
   * @return setId
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public UUID getSetId() {
    return setId;
  }

  public void setSetId(UUID setId) {
    this.setId = setId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetCollectableTypeBySetRequest getCollectableTypeBySetRequest = (GetCollectableTypeBySetRequest) o;
    return Objects.equals(this.setId, getCollectableTypeBySetRequest.setId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(setId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetCollectableTypeBySetRequest {\n");
    
    sb.append("    setId: ").append(toIndentedString(setId)).append("\n");
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
