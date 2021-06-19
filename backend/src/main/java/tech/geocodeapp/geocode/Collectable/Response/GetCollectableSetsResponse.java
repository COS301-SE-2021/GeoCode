package tech.geocodeapp.geocode.Collectable.Response;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import tech.geocodeapp.geocode.Collectable.Model.CollectableSet;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * GetCollectableSetsResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-17T08:18:28.046Z[GMT]")


public class GetCollectableSetsResponse   {
  @JsonProperty("collectableSets")
  @Valid
  private List<CollectableSet> collectableSets = new ArrayList<CollectableSet>();

  public GetCollectableSetsResponse collectableSets(List<CollectableSet> collectableSets) {
    this.collectableSets = collectableSets;
    return this;
  }

  public GetCollectableSetsResponse addCollectableSetsItem(CollectableSet collectableSetsItem) {
    this.collectableSets.add(collectableSetsItem);
    return this;
  }

  /**
   * Get collectableSets
   * @return collectableSets
   **/
  @Schema(required = true, description = "")
      @NotNull
    @Valid
    public List<CollectableSet> getCollectableSets() {
    return collectableSets;
  }

  public void setCollectableSets(List<CollectableSet> collectableSets) {
    this.collectableSets = collectableSets;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetCollectableSetsResponse getCollectableSetsResponse = (GetCollectableSetsResponse) o;
    return Objects.equals(this.collectableSets, getCollectableSetsResponse.collectableSets);
  }

  @Override
  public int hashCode() {
    return Objects.hash(collectableSets);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetCollectableSetsResponse {\n");
    
    sb.append("    collectableSets: ").append(toIndentedString(collectableSets)).append("\n");
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