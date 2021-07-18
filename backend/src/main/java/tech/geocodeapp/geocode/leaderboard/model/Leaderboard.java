package tech.geocodeapp.geocode.leaderboard.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Type;
import org.springframework.validation.annotation.Validated;
import tech.geocodeapp.geocode.event.model.Event;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Leaderboard
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-07-05T13:14:20.256Z[GMT]")

@Entity
@Table(name = "leaderboard")
public class Leaderboard   {
  @Id
  @JsonProperty("id")
  @Type(type="org.hibernate.type.UUIDCharType")
  private UUID id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("event")
  @OneToOne
  @Cascade(org.hibernate.annotations.CascadeType.ALL)
  private Event event = null;

  public Leaderboard id(UUID id) {
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

  public Leaderboard name(String name) {
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

  public Leaderboard event(Event event) {
    this.event = event;
    return this;
  }

  /**
   * Get event
   * @return event
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public Event getEvent() {
    return event;
  }

  public void setEvent(Event event) {
    this.event = event;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Leaderboard leaderboard = (Leaderboard) o;
    return Objects.equals(this.id, leaderboard.id) &&
        Objects.equals(this.name, leaderboard.name) &&
        Objects.equals(this.event, leaderboard.event);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, event);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Leaderboard {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    event: ").append(toIndentedString(event)).append("\n");
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
