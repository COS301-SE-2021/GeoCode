package tech.geocodeapp.geocode.collectable.model;

import java.util.HashMap;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;

import org.hibernate.annotations.Cascade;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CollectableType
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-02T03:21:48.298Z[GMT]")

@Entity
public class CollectableType   {
  @JsonProperty("id")
  @Id
  private UUID id = null;

  @JsonProperty("name")
  private String name = null;

  @Column(length=1000)
  @JsonProperty("image")
  private String image = null;

  @JsonProperty("rarity")
  private Rarity rarity = null;

  @JsonProperty("set")
  @ManyToOne
  @JoinColumn(name = "COLLECTABLE_SET_ID")
  @Cascade(org.hibernate.annotations.CascadeType.ALL)
  private CollectableSet set = null;

  @JsonProperty("properties")
  private HashMap<String,String> properties;

  public CollectableType() {
    id = UUID.randomUUID();
  }

  //main constructor for creating new CollectableTypes
  public CollectableType(String name, String image, Rarity rarity, CollectableSet set, HashMap<String,String> properties) {
    id = UUID.randomUUID();
    this.name = name;
    this.image = image;
    this.rarity = rarity;
    this.set = set;
    this.properties = properties;
  }

  public HashMap<String, String> getProperties() {
    return properties;
  }

  public void setProperties(HashMap<String, String> properties) {
    this.properties = properties;
  }

  //property for id
  public CollectableType id(UUID id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   **/
  @Schema(required = true, description = "")
  @NotNull

  @Valid
  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  //property for name
  public CollectableType name(String name) {
    this.name = name;
    return this;
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

  //property for image
  public CollectableType image(String image) {
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

  //property for rarity
  public CollectableType rarity(Rarity rarity) {
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

  //property for set
  public CollectableType set(CollectableSet set) {
    this.set = set;
    return this;
  }

  /**
   * Get set
   * @return set
   **/
  @Schema(required = true, description = "")
  @NotNull

  @Valid
  public CollectableSet getSet() {
    return set;
  }

  public void setSet(CollectableSet set) {
    this.set = set;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CollectableType collectableType = (CollectableType) o;
    return Objects.equals(this.id, collectableType.id) &&
            Objects.equals(this.name, collectableType.name) &&
            Objects.equals(this.image, collectableType.image) &&
            Objects.equals(this.rarity, collectableType.rarity) &&
            Objects.equals(this.set, collectableType.set);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, image, rarity, set);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CollectableType {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    image: ").append(toIndentedString(image)).append("\n");
    sb.append("    rarity: ").append(toIndentedString(rarity)).append("\n");
    sb.append("    set: ").append(toIndentedString(set)).append("\n");
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