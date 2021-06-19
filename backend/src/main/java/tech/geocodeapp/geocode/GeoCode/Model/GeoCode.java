package tech.geocodeapp.geocode.GeoCode.Model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import tech.geocodeapp.geocode.Collectable.Model.Collectable;
import tech.geocodeapp.geocode.Collectable.Model.Difficulty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.Cascade;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * GeoCode
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-02T03:21:48.298Z[GMT]")

@Entity
public class GeoCode   {
  @Id
  @JsonProperty("id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id = null;

  @JsonProperty("difficulty")
  private Difficulty difficulty = null;

  @JsonProperty("available")
  private Boolean available = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("hints")
  @Valid
  @ElementCollection
  private List<String> hints = new ArrayList<String>();

  @JsonProperty("collectables")
  @ManyToOne
  @JoinColumn(name = "COLLECTABLE_ID")
  @Cascade(org.hibernate.annotations.CascadeType.ALL)
  private Collectable collectables = null;

  @JsonProperty("trackables")
  private String trackables = null;

  @JsonProperty("qrCode")
  private String qrCode = null;

  @JsonProperty("location")
  private String location = null;

  public GeoCode id(UUID id) {
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

  public GeoCode difficulty(Difficulty difficulty) {
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

  public GeoCode available(Boolean available) {
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

  public GeoCode description(String description) {
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

  public GeoCode hints(List<String> hints) {
    this.hints = hints;
    return this;
  }

  public GeoCode addHintsItem(String hintsItem) {
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

  public GeoCode collectables(Collectable collectables) {
    this.collectables = collectables;
    return this;
  }

  /**
   * Get collectables
   * @return collectables
   **/
  @Schema(description = "")

  @Valid
  public Collectable getCollectables() {
    return collectables;
  }

  public void setCollectables(Collectable collectables) {
    this.collectables = collectables;
  }

  public GeoCode trackables(String trackables) {
    this.trackables = trackables;
    return this;
  }

  /**
   * Get trackables
   * @return trackables
   **/
  @Schema(description = "")

  public String getTrackables() {
    return trackables;
  }

  public void setTrackables(String trackables) {
    this.trackables = trackables;
  }

  public GeoCode qrCode(String qrCode) {
    this.qrCode = qrCode;
    return this;
  }

  /**
   * Get qrCode
   * @return qrCode
   **/
  @Schema(required = true, description = "")
  @NotNull

  public String getQrCode() {
    return qrCode;
  }

  public void setQrCode(String qrCode) {
    this.qrCode = qrCode;
  }

  public GeoCode location(String location) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GeoCode geoCode = (GeoCode) o;
    return Objects.equals(this.id, geoCode.id) &&
            Objects.equals(this.difficulty, geoCode.difficulty) &&
            Objects.equals(this.available, geoCode.available) &&
            Objects.equals(this.description, geoCode.description) &&
            Objects.equals(this.hints, geoCode.hints) &&
            Objects.equals(this.collectables, geoCode.collectables) &&
            Objects.equals(this.trackables, geoCode.trackables) &&
            Objects.equals(this.qrCode, geoCode.qrCode) &&
            Objects.equals(this.location, geoCode.location);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, difficulty, available, description, hints, collectables, trackables, qrCode, location);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GeoCode {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    difficulty: ").append(toIndentedString(difficulty)).append("\n");
    sb.append("    available: ").append(toIndentedString(available)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    hints: ").append(toIndentedString(hints)).append("\n");
    sb.append("    collectables: ").append(toIndentedString(collectables)).append("\n");
    sb.append("    trackables: ").append(toIndentedString(trackables)).append("\n");
    sb.append("    qrCode: ").append(toIndentedString(qrCode)).append("\n");
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
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