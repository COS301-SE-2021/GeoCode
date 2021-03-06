package tech.geocodeapp.geocode.collectable.response;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * GetCollectablesResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-17T08:18:28.046Z[GMT]")


public class GetCollectablesResponse   {
  @JsonProperty("collectables")
  @Valid
  private List<CollectableResponse> collectables = new ArrayList<CollectableResponse>();

  public GetCollectablesResponse collectables(List<CollectableResponse> collectables) {
    this.collectables = collectables;
    return this;
  }

  public GetCollectablesResponse addCollectablesItem(CollectableResponse collectablesItem) {
    this.collectables.add(collectablesItem);
    return this;
  }

  /**
   * Get collectables
   * @return collectables
   **/
  @Schema(required = true, description = "")
      @NotNull
    @Valid
    public List<CollectableResponse> getCollectables() {
    return collectables;
  }

  public void setCollectables(List<CollectableResponse> collectables) {
    this.collectables = collectables;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetCollectablesResponse getCollectablesResponse = (GetCollectablesResponse) o;
    return Objects.equals(this.collectables, getCollectablesResponse.collectables);
  }

  @Override
  public int hashCode() {
    return Objects.hash(collectables);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetCollectablesResponse {\n");
    
    sb.append("    collectables: ").append(toIndentedString(collectables)).append("\n");
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
