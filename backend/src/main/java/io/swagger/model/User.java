package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.model.Collectable;
import io.swagger.model.GeoCode;
import io.swagger.model.Point;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * User
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-09T21:02:56.988Z[GMT]")


public class User   {
  @JsonProperty("id")
  private UUID id = null;

  @JsonProperty("username")
  private String username = null;

  @JsonProperty("points")
  @Valid
  private List<Point> points = null;

  @JsonProperty("currentCollectable")
  private Collectable currentCollectable = null;

  @JsonProperty("foundCollectables")
  @Valid
  private List<Collectable> foundCollectables = null;

  @JsonProperty("foundGeocodes")
  @Valid
  private List<GeoCode> foundGeocodes = null;

  @JsonProperty("ownedGeocodes")
  @Valid
  private List<GeoCode> ownedGeocodes = null;

  public User id(UUID id) {
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

  public User username(String username) {
    this.username = username;
    return this;
  }

  /**
   * Get username
   * @return username
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public User points(List<Point> points) {
    this.points = points;
    return this;
  }

  public User addPointsItem(Point pointsItem) {
    if (this.points == null) {
      this.points = new ArrayList<Point>();
    }
    this.points.add(pointsItem);
    return this;
  }

  /**
   * Get points
   * @return points
   **/
  @Schema(description = "")
      @Valid
    public List<Point> getPoints() {
    return points;
  }

  public void setPoints(List<Point> points) {
    this.points = points;
  }

  public User currentCollectable(Collectable currentCollectable) {
    this.currentCollectable = currentCollectable;
    return this;
  }

  /**
   * Get currentCollectable
   * @return currentCollectable
   **/
  @Schema(description = "")
  
    @Valid
    public Collectable getCurrentCollectable() {
    return currentCollectable;
  }

  public void setCurrentCollectable(Collectable currentCollectable) {
    this.currentCollectable = currentCollectable;
  }

  public User foundCollectables(List<Collectable> foundCollectables) {
    this.foundCollectables = foundCollectables;
    return this;
  }

  public User addFoundCollectablesItem(Collectable foundCollectablesItem) {
    if (this.foundCollectables == null) {
      this.foundCollectables = new ArrayList<Collectable>();
    }
    this.foundCollectables.add(foundCollectablesItem);
    return this;
  }

  /**
   * Get foundCollectables
   * @return foundCollectables
   **/
  @Schema(description = "")
      @Valid
    public List<Collectable> getFoundCollectables() {
    return foundCollectables;
  }

  public void setFoundCollectables(List<Collectable> foundCollectables) {
    this.foundCollectables = foundCollectables;
  }

  public User foundGeocodes(List<GeoCode> foundGeocodes) {
    this.foundGeocodes = foundGeocodes;
    return this;
  }

  public User addFoundGeocodesItem(GeoCode foundGeocodesItem) {
    if (this.foundGeocodes == null) {
      this.foundGeocodes = new ArrayList<GeoCode>();
    }
    this.foundGeocodes.add(foundGeocodesItem);
    return this;
  }

  /**
   * Get foundGeocodes
   * @return foundGeocodes
   **/
  @Schema(description = "")
      @Valid
    public List<GeoCode> getFoundGeocodes() {
    return foundGeocodes;
  }

  public void setFoundGeocodes(List<GeoCode> foundGeocodes) {
    this.foundGeocodes = foundGeocodes;
  }

  public User ownedGeocodes(List<GeoCode> ownedGeocodes) {
    this.ownedGeocodes = ownedGeocodes;
    return this;
  }

  public User addOwnedGeocodesItem(GeoCode ownedGeocodesItem) {
    if (this.ownedGeocodes == null) {
      this.ownedGeocodes = new ArrayList<GeoCode>();
    }
    this.ownedGeocodes.add(ownedGeocodesItem);
    return this;
  }

  /**
   * Get ownedGeocodes
   * @return ownedGeocodes
   **/
  @Schema(description = "")
      @Valid
    public List<GeoCode> getOwnedGeocodes() {
    return ownedGeocodes;
  }

  public void setOwnedGeocodes(List<GeoCode> ownedGeocodes) {
    this.ownedGeocodes = ownedGeocodes;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(this.id, user.id) &&
        Objects.equals(this.username, user.username) &&
        Objects.equals(this.points, user.points) &&
        Objects.equals(this.currentCollectable, user.currentCollectable) &&
        Objects.equals(this.foundCollectables, user.foundCollectables) &&
        Objects.equals(this.foundGeocodes, user.foundGeocodes) &&
        Objects.equals(this.ownedGeocodes, user.ownedGeocodes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username, points, currentCollectable, foundCollectables, foundGeocodes, ownedGeocodes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class User {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    points: ").append(toIndentedString(points)).append("\n");
    sb.append("    currentCollectable: ").append(toIndentedString(currentCollectable)).append("\n");
    sb.append("    foundCollectables: ").append(toIndentedString(foundCollectables)).append("\n");
    sb.append("    foundGeocodes: ").append(toIndentedString(foundGeocodes)).append("\n");
    sb.append("    ownedGeocodes: ").append(toIndentedString(ownedGeocodes)).append("\n");
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