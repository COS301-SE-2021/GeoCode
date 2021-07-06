package tech.geocodeapp.geocode.collectable.request;

import java.util.HashMap;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import tech.geocodeapp.geocode.collectable.model.Rarity;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CreateCollectableTypeRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-17T08:18:28.046Z[GMT]")


public class CreateCollectableTypeRequest   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("image")
  private String image = null;

  @JsonProperty("rarity")
  private Rarity rarity = null;

  @JsonProperty("setId")
  private UUID setId = null;

  @JsonProperty("properties")
  private HashMap<String,String> properties = new HashMap<>();

  public CreateCollectableTypeRequest name(String name) {
    this.name = name;
    return this;
  }

  public HashMap<String, String> getProperties() {
    return properties;
  }

  public void setProperties(HashMap<String, String> properties) {
    this.properties = properties;
  }

  /**
   * Get name
   * @return name
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public CreateCollectableTypeRequest image(String image) {
    this.image = image;
    return this;
  }

  /**
   * Get image
   * @return image
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public CreateCollectableTypeRequest rarity(Rarity rarity) {
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

  public CreateCollectableTypeRequest setId(UUID setId) {
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
    CreateCollectableTypeRequest createCollectableTypeRequest = (CreateCollectableTypeRequest) o;
    return Objects.equals(this.name, createCollectableTypeRequest.name) &&
        Objects.equals(this.image, createCollectableTypeRequest.image) &&
        Objects.equals(this.rarity, createCollectableTypeRequest.rarity) &&
        Objects.equals(this.setId, createCollectableTypeRequest.setId) &&
        Objects.equals(this.properties, createCollectableTypeRequest.properties);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, image, rarity, setId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateCollectableTypeRequest {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    image: ").append(toIndentedString(image)).append("\n");
    sb.append("    rarity: ").append(toIndentedString(rarity)).append("\n");
    sb.append("    setId: ").append(toIndentedString(setId)).append("\n");
    sb.append("    properties: ").append(toIndentedString(properties)).append("\n");
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
