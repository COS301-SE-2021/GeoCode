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
 * GetGeoCodeByQRCodeResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-19T20:06:36.916Z[GMT]")


public class GetGeoCodeByQRCodeResponse   {
  @JsonProperty("id")
  private UUID id = null;

  @JsonProperty("available")
  private Boolean available = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("longitude")
  private String longitude = null;

  @JsonProperty("latitude")
  private String latitude = null;

  @JsonProperty("difficulty")
  private Difficulty difficulty = null;

  public GetGeoCodeByQRCodeResponse id(UUID id) {
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

  public GetGeoCodeByQRCodeResponse available(Boolean available) {
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

  public GetGeoCodeByQRCodeResponse description(String description) {
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

  public GetGeoCodeByQRCodeResponse longitude(String longitude) {
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

  public GetGeoCodeByQRCodeResponse latitude(String latitude) {
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

  public GetGeoCodeByQRCodeResponse difficulty(Difficulty difficulty) {
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
    GetGeoCodeByQRCodeResponse getGeoCodeByQRCodeResponse = (GetGeoCodeByQRCodeResponse) o;
    return Objects.equals(this.id, getGeoCodeByQRCodeResponse.id) &&
        Objects.equals(this.available, getGeoCodeByQRCodeResponse.available) &&
        Objects.equals(this.description, getGeoCodeByQRCodeResponse.description) &&
        Objects.equals(this.longitude, getGeoCodeByQRCodeResponse.longitude) &&
        Objects.equals(this.latitude, getGeoCodeByQRCodeResponse.latitude) &&
        Objects.equals(this.difficulty, getGeoCodeByQRCodeResponse.difficulty);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, available, description, longitude, latitude, difficulty);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetGeoCodeByQRCodeResponse {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    available: ").append(toIndentedString(available)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    longitude: ").append(toIndentedString(longitude)).append("\n");
    sb.append("    latitude: ").append(toIndentedString(latitude)).append("\n");
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
