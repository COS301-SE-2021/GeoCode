package tech.geocodeapp.geocode.Collectable.Model;

import java.util.ArrayList;
import java.util.List;
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
 * Collectable
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-02T03:21:48.298Z[GMT]")

@Entity
@Table(name = "collectable")
public class Collectable   {
  @JsonProperty("id")
  @Id
  private UUID id = null;

  @JsonProperty("type")
  @ManyToOne
  @JoinColumn(name = "COLLECTABLE_TYPE_ID")
  @Cascade(org.hibernate.annotations.CascadeType.ALL)
  private CollectableType type = null;

  @ElementCollection
  private List<String> pastLocations = new ArrayList<String>();

  public Collectable() {
    id = UUID.randomUUID();
  }

  //main constructor for creating new collectables
  public Collectable(CollectableType type) {
    this.type = type;
    id = UUID.randomUUID();
  }

  //property for id
  public Collectable id(UUID id) {
    this.id = id;
    return this;
  }

  public List<String> getPastLocations() {
    return pastLocations;
  }

  public void setPastLocations(List<String> pastLocations) {
    this.pastLocations = pastLocations;
  }

  /**
   * A method to update the current location of a Collectable and to ensure only Collectables with a trackable property
   * in their CollectableType have the history of past locations saved
   * @param location the new location of the Collectable
   */
  public void changeLocation(String location) {
    if(type.getProperties()!=null && type.getProperties().containsKey("trackable")){
      pastLocations.add(location);
    }else{
      pastLocations.clear();
      pastLocations.add(location);
    }
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

  //property for type
  public Collectable type(CollectableType type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
   **/
  @Schema(required = true, description = "")
  @NotNull

  @Valid
  public CollectableType getType() {
    return type;
  }

  public void setType(CollectableType type) {
    this.type = type;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Collectable collectable = (Collectable) o;
    return Objects.equals(this.id, collectable.id) &&
            Objects.equals(this.type, collectable.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Collectable {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
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