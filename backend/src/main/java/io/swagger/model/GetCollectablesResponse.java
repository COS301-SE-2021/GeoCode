package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.model.Collectable;
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
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-02T03:21:48.298Z[GMT]")


public class GetCollectablesResponse   {
  @JsonProperty("collectables")
  @Valid
  private List<Collectable> collectables = new ArrayList<Collectable>();

  public GetCollectablesResponse collectables(List<Collectable> collectables) {
    this.collectables = collectables;
    return this;
  }

  public GetCollectablesResponse addCollectablesItem(Collectable collectablesItem) {
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
    public List<Collectable> getCollectables() {
    return collectables;
  }

  public void setCollectables(List<Collectable> collectables) {
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
