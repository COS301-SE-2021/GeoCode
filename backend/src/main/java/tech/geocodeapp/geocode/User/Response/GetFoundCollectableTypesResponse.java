package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * GetFoundCollectableTypesResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-24T10:12:19.520Z[GMT]")


public class GetFoundCollectableTypesResponse   {
  @JsonProperty("collectableTypeIDs")
  @Valid
  private List<UUID> collectableTypeIDs = null;

  public GetFoundCollectableTypesResponse collectableTypeIDs(List<UUID> collectableTypeIDs) {
    this.collectableTypeIDs = collectableTypeIDs;
    return this;
  }

  public GetFoundCollectableTypesResponse addCollectableTypeIDsItem(UUID collectableTypeIDsItem) {
    if (this.collectableTypeIDs == null) {
      this.collectableTypeIDs = new ArrayList<UUID>();
    }
    this.collectableTypeIDs.add(collectableTypeIDsItem);
    return this;
  }

  /**
   * Get collectableTypeIDs
   * @return collectableTypeIDs
   **/
  @Schema(description = "")
      @Valid
    public List<UUID> getCollectableTypeIDs() {
    return collectableTypeIDs;
  }

  public void setCollectableTypeIDs(List<UUID> collectableTypeIDs) {
    this.collectableTypeIDs = collectableTypeIDs;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetFoundCollectableTypesResponse getFoundCollectableTypesResponse = (GetFoundCollectableTypesResponse) o;
    return Objects.equals(this.collectableTypeIDs, getFoundCollectableTypesResponse.collectableTypeIDs);
  }

  @Override
  public int hashCode() {
    return Objects.hash(collectableTypeIDs);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetFoundCollectableTypesResponse {\n");
    
    sb.append("    collectableTypeIDs: ").append(toIndentedString(collectableTypeIDs)).append("\n");
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
