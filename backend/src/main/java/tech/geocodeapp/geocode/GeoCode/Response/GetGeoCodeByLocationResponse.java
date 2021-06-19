package tech.geocodeapp.geocode.GeoCode.Response;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;
import tech.geocodeapp.geocode.Collectable.Model.Difficulty;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * GetGeoCodeByLocationResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-19T15:57:07.487Z[GMT]")


public class GetGeoCodeByLocationResponse   {
  @JsonProperty("id")
  private UUID id = null;

  @JsonProperty("available")
  private Boolean available = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("location")
  private String location = null;

  @JsonProperty("difficulty")
  private Difficulty difficulty = null;

  public GetGeoCodeByLocationResponse id(UUID id) {
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

  public GetGeoCodeByLocationResponse available(Boolean available) {
    this.available = available;
    return this;
  }

  /**
   * Get available
   * @return available
   **/
  @Schema(required = true, description = "")
      @NotNull

    public Boolean isAvailable() {
    return available;
  }

  public void setAvailable(Boolean available) {
    this.available = available;
  }

  public GetGeoCodeByLocationResponse description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public GetGeoCodeByLocationResponse location(String location) {
    this.location = location;
    return this;
  }

  /**
   * Get location
   * @return location
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public GetGeoCodeByLocationResponse difficulty(Difficulty difficulty) {
    this.difficulty = difficulty;
    return this;
  }

  /**
   * Get difficulty
   * @return difficulty
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public Difficulty getDifficulty() {
    return difficulty;
  }

  public void setDifficulty(Difficulty difficulty) {
    this.difficulty = difficulty;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetGeoCodeByLocationResponse getGeoCodeByLocationResponse = (GetGeoCodeByLocationResponse) o;
    return Objects.equals(this.id, getGeoCodeByLocationResponse.id) &&
        Objects.equals(this.available, getGeoCodeByLocationResponse.available) &&
        Objects.equals(this.description, getGeoCodeByLocationResponse.description) &&
        Objects.equals(this.location, getGeoCodeByLocationResponse.location) &&
        Objects.equals(this.difficulty, getGeoCodeByLocationResponse.difficulty);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, available, description, location, difficulty);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetGeoCodeByLocationResponse {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    available: ").append(toIndentedString(available)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
    sb.append("    difficulty: ").append(toIndentedString(difficulty)).append("\n");
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
