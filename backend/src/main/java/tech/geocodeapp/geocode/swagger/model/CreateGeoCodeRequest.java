package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CreateGeoCodeRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-01T08:10:17.138Z[GMT]")


public class CreateGeoCodeRequest   {
  @JsonProperty("description")
  private String description = null;

  @JsonProperty("location")
  private String location = null;

  @JsonProperty("hints")
  @Valid
  private List<String> hints = new ArrayList<String>();

  /**
   * GeoCode real world locating difficulty
   */
  public enum DifficultyEnum {
    EASY("EASY"),
    
    MEDIUM("MEDIUM"),
    
    DIFFICULTY("DIFFICULTY"),
    
    INSANE("INSANE");

    private String value;

    DifficultyEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static DifficultyEnum fromValue(String text) {
      for (DifficultyEnum b : DifficultyEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("difficulty")
  private DifficultyEnum difficulty = null;

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

  public CreateGeoCodeRequest location(String location) {
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

  public CreateGeoCodeRequest difficulty(DifficultyEnum difficulty) {
    this.difficulty = difficulty;
    return this;
  }

  /**
   * GeoCode real world locating difficulty
   * @return difficulty
   **/
  @Schema(required = true, description = "GeoCode real world locating difficulty")
      @NotNull

    public DifficultyEnum getDifficulty() {
    return difficulty;
  }

  public void setDifficulty(DifficultyEnum difficulty) {
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
    CreateGeoCodeRequest createGeoCodeRequest = (CreateGeoCodeRequest) o;
    return Objects.equals(this.description, createGeoCodeRequest.description) &&
        Objects.equals(this.location, createGeoCodeRequest.location) &&
        Objects.equals(this.hints, createGeoCodeRequest.hints) &&
        Objects.equals(this.difficulty, createGeoCodeRequest.difficulty);
  }

  @Override
  public int hashCode() {
    return Objects.hash(description, location, hints, difficulty);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateGeoCodeRequest {\n");
    
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
    sb.append("    hints: ").append(toIndentedString(hints)).append("\n");
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
