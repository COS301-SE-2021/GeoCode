package tech.geocodeapp.geocode.GeoCode.Model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;

import tech.geocodeapp.geocode.Collectable.Model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * GeoCode
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-19T19:40:54.656Z[GMT]")

@Entity
@Table(name = "geocode")
public class GeoCode   {

  @JsonProperty("id")
  @Id
  private UUID id = null;

  @JsonProperty("difficulty")
  private Difficulty difficulty = null;

  @JsonProperty("available")
  private Boolean available = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("hints")
  @ElementCollection
  @Valid
  private List<String> hints = new ArrayList<String>();

  @JsonProperty("collectables")
  @ManyToMany
  @Valid
  private List<Collectable> collectables = null;

  @JsonProperty("qrCode")
  private String qrCode = null;

  @JsonProperty("longitude")
  private String longitude = null;

  @JsonProperty("latitude")
  private String latitude = null;

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

  public GeoCode collectables(List<Collectable> collectables) {
    this.collectables = collectables;
    return this;
  }

  public GeoCode addCollectablesItem(Collectable collectablesItem) {
    if (this.collectables == null) {
      this.collectables = new ArrayList<Collectable>();
    }
    this.collectables.add(collectablesItem);
    return this;
  }

  /**
   * Get collectables
   * @return collectables
   **/
  @Schema(description = "")
  @Valid
  public List<Collectable> getCollectables() {
    return collectables;
  }

  public void setCollectables(List<Collectable> collectables) {
    this.collectables = collectables;
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

  public GeoCode longitude(String longitude) {
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

  public GeoCode latitude(String latitude) {
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
            Objects.equals(this.qrCode, geoCode.qrCode) &&
            Objects.equals(this.longitude, geoCode.longitude) &&
            Objects.equals(this.latitude, geoCode.latitude);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, difficulty, available, description, hints, collectables, qrCode, longitude, latitude);
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
    sb.append("    qrCode: ").append(toIndentedString(qrCode)).append("\n");
    sb.append("    longitude: ").append(toIndentedString(longitude)).append("\n");
    sb.append("    latitude: ").append(toIndentedString(latitude)).append("\n");
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
