package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.model.Difficulty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * SwapCollectablesRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-02T03:21:48.298Z[GMT]")


public class SwapCollectablesRequest   {
  @JsonProperty("description")
  private String description = null;

  @JsonProperty("location")
  private String location = null;

  @JsonProperty("hints")
  @Valid
  private List<String> hints = new ArrayList<String>();

  @JsonProperty("difficulty")
  private Difficulty difficulty = null;

  public SwapCollectablesRequest description(String description) {
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

  public SwapCollectablesRequest location(String location) {
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

  public SwapCollectablesRequest hints(List<String> hints) {
    this.hints = hints;
    return this;
  }

  public SwapCollectablesRequest addHintsItem(String hintsItem) {
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

  public SwapCollectablesRequest difficulty(Difficulty difficulty) {
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
    SwapCollectablesRequest swapCollectablesRequest = (SwapCollectablesRequest) o;
    return Objects.equals(this.description, swapCollectablesRequest.description) &&
        Objects.equals(this.location, swapCollectablesRequest.location) &&
        Objects.equals(this.hints, swapCollectablesRequest.hints) &&
        Objects.equals(this.difficulty, swapCollectablesRequest.difficulty);
  }

  @Override
  public int hashCode() {
    return Objects.hash(description, location, hints, difficulty);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SwapCollectablesRequest {\n");
    
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