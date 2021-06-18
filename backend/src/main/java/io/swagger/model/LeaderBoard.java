package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * LeaderBoard
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-17T08:18:28.046Z[GMT]")


public class LeaderBoard   {
  @JsonProperty("id")
  private UUID id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("eventId")
  private UUID eventId = null;

  @JsonProperty("pointId")
  private UUID pointId = null;

  public LeaderBoard id(UUID id) {
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

  public LeaderBoard name(String name) {
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

  public LeaderBoard eventId(UUID eventId) {
    this.eventId = eventId;
    return this;
  }

  /**
   * Get eventId
   * @return eventId
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public UUID getEventId() {
    return eventId;
  }

  public void setEventId(UUID eventId) {
    this.eventId = eventId;
  }

  public LeaderBoard pointId(UUID pointId) {
    this.pointId = pointId;
    return this;
  }

  /**
   * Get pointId
   * @return pointId
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public UUID getPointId() {
    return pointId;
  }

  public void setPointId(UUID pointId) {
    this.pointId = pointId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LeaderBoard leaderBoard = (LeaderBoard) o;
    return Objects.equals(this.id, leaderBoard.id) &&
        Objects.equals(this.name, leaderBoard.name) &&
        Objects.equals(this.eventId, leaderBoard.eventId) &&
        Objects.equals(this.pointId, leaderBoard.pointId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, eventId, pointId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LeaderBoard {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    eventId: ").append(toIndentedString(eventId)).append("\n");
    sb.append("    pointId: ").append(toIndentedString(pointId)).append("\n");
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
