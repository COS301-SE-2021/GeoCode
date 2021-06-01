package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.model.Rarity;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * GetCollectableTypeByRarityRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-01T18:53:58.734Z[GMT]")


public class GetCollectableTypeByRarityRequest   {
  @JsonProperty("rarity")
  private Rarity rarity = null;

  public GetCollectableTypeByRarityRequest rarity(Rarity rarity) {
    this.rarity = rarity;
    return this;
  }

  /**
   * Get rarity
   * @return rarity
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public Rarity getRarity() {
    return rarity;
  }

  public void setRarity(Rarity rarity) {
    this.rarity = rarity;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetCollectableTypeByRarityRequest getCollectableTypeByRarityRequest = (GetCollectableTypeByRarityRequest) o;
    return Objects.equals(this.rarity, getCollectableTypeByRarityRequest.rarity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(rarity);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetCollectableTypeByRarityRequest {\n");
    
    sb.append("    rarity: ").append(toIndentedString(rarity)).append("\n");
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
