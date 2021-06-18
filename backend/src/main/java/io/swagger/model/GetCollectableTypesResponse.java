package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import tech.geocodeapp.geocode.Collectable.Decorator.CollectableTypeComponent;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * GetCollectableTypesResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-17T08:18:28.046Z[GMT]")


public class GetCollectableTypesResponse   {
  @JsonProperty("collectableTypes")
  @Valid
  private List<CollectableTypeComponent> collectableTypes = new ArrayList<CollectableTypeComponent>();

  public GetCollectableTypesResponse collectableTypes(List<CollectableTypeComponent> collectableTypes) {
    this.collectableTypes = collectableTypes;
    return this;
  }

  public GetCollectableTypesResponse addCollectableTypesItem(CollectableTypeComponent collectableTypesItem) {
    this.collectableTypes.add(collectableTypesItem);
    return this;
  }

  /**
   * Get collectableTypes
   * @return collectableTypes
   **/
  @Schema(required = true, description = "")
      @NotNull
    @Valid
    public List<CollectableTypeComponent> getCollectableTypes() {
    return collectableTypes;
  }

  public void setCollectableTypes(List<CollectableTypeComponent> collectableTypes) {
    this.collectableTypes = collectableTypes;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetCollectableTypesResponse getCollectableTypesResponse = (GetCollectableTypesResponse) o;
    return Objects.equals(this.collectableTypes, getCollectableTypesResponse.collectableTypes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(collectableTypes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetCollectableTypesResponse {\n");
    
    sb.append("    collectableTypes: ").append(toIndentedString(collectableTypes)).append("\n");
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
