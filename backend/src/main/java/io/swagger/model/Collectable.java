package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.model.CollectableType;
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
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-01T18:53:58.734Z[GMT]")

@Entity
@Table(name = "")
public class Collectable   {
  @JsonProperty("id")
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id = null;

  @JsonProperty("type")
  @ManyToOne
  @JoinColumn(name = "COLLECTABLE_TYPE_ID")
  @Cascade(org.hibernate.annotations.CascadeType.ALL)
  private CollectableType type = null;

  public Collectable() {
  }

  //main constructor for creating new collectables
  public Collectable(CollectableType type) {
    this.type = type;
  }

  //property for id
  public Collectable id(UUID id) {
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
