package tech.geocodeapp.geocode.GeoCode.Request;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import tech.geocodeapp.geocode.Collectable.Model.Difficulty;

/**
 * CreateGeoCodeRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-19T19:40:54.656Z[GMT]")


public class CreateGeoCodeRequest   {
  @JsonProperty("id")
  private UUID id = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("longitude")
  private String longitude = null;

  @JsonProperty("latitude")
  private String latitude = null;

  @JsonProperty("hints")
  @Valid
  private List<String> hints = new ArrayList<String>();

  @JsonProperty("difficulty")
  private Difficulty difficulty = null;

  @JsonProperty("available")
  private Boolean available = null;

  public CreateGeoCodeRequest id(UUID id) {
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

  public CreateGeoCodeRequest description(String description) {
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

  public CreateGeoCodeRequest longitude(String longitude) {
    this.longitude = longitude;
    return this;
  }

  /**
   * Get longitude
   * @return longitude
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getLongitude() {
    return longitude;
  }

  public void setLongitude(String longitude) {
    this.longitude = longitude;
  }

  public CreateGeoCodeRequest latitude(String latitude) {
    this.latitude = latitude;
    return this;
  }

  /**
   * Get latitude
   * @return latitude
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getLatitude() {
    return latitude;
  }

  public void setLatitude(String latitude) {
    this.latitude = latitude;
  }

  public CreateGeoCodeRequest hints(List<String> hints) {
    this.hints = hints;
    return this;
  }

  public CreateGeoCodeRequest addHintsItem(String hintsItem) {
    this.hints.add(hintsItem);
    return this;
  }

  /**
   * Get hints
   * @return hints
   **/
  @Schema(required = true, description = "")
      @NotNull

    public List<String> getHints() {
    return hints;
  }

  public void setHints(List<String> hints) {
    this.hints = hints;
  }

  public CreateGeoCodeRequest difficulty(Difficulty difficulty) {
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

  public CreateGeoCodeRequest available(Boolean available) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateGeoCodeRequest createGeoCodeRequest = (CreateGeoCodeRequest) o;
    return Objects.equals(this.id, createGeoCodeRequest.id) &&
        Objects.equals(this.description, createGeoCodeRequest.description) &&
        Objects.equals(this.longitude, createGeoCodeRequest.longitude) &&
        Objects.equals(this.latitude, createGeoCodeRequest.latitude) &&
        Objects.equals(this.hints, createGeoCodeRequest.hints) &&
        Objects.equals(this.difficulty, createGeoCodeRequest.difficulty) &&
        Objects.equals(this.available, createGeoCodeRequest.available);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, description, longitude, latitude, hints, difficulty, available);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateGeoCodeRequest {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    longitude: ").append(toIndentedString(longitude)).append("\n");
    sb.append("    latitude: ").append(toIndentedString(latitude)).append("\n");
    sb.append("    hints: ").append(toIndentedString(hints)).append("\n");
    sb.append("    difficulty: ").append(toIndentedString(difficulty)).append("\n");
    sb.append("    available: ").append(toIndentedString(available)).append("\n");
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
