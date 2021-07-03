package tech.geocodeapp.geocode.user.response;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import tech.geocodeapp.geocode.collectable.model.Collectable;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * GetFoundCollectablesResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-17T08:18:28.046Z[GMT]")


public class GetFoundCollectablesResponse   {
  @JsonProperty("collectables")
  @Valid
  private List<Collectable> collectables = null;

  public GetFoundCollectablesResponse collectables(List<Collectable> collectables) {
    this.collectables = collectables;
    return this;
  }

  public GetFoundCollectablesResponse addCollectablesItem(Collectable collectablesItem) {
    if (this.collectables == null) {
      this.collectables = new ArrayList<Collectable>();
    }
    this.collectables.add(collectablesItem);
    return this;
  }

  /**
   * Get collectables
   * @return collectables
   **/
  @Schema(description = "")
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
    GetFoundCollectablesResponse getFoundCollectablesResponse = (GetFoundCollectablesResponse) o;
    return Objects.equals(this.collectables, getFoundCollectablesResponse.collectables);
  }

  @Override
  public int hashCode() {
    return Objects.hash(collectables);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetFoundCollectablesResponse {\n");
    
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
