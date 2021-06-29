package tech.geocodeapp.geocode.user.model;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import org.hibernate.annotations.Cascade;
import tech.geocodeapp.geocode.collectable.model.Collectable;
import tech.geocodeapp.geocode.geocode.model.GeoCode;
import tech.geocodeapp.geocode.leaderboard.model.Point;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * User
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-24T11:24:11.186Z[GMT]")

@Entity
@Table(name="user_table")
public class User   {
  @Id
  @JsonProperty("id")
  private UUID id = null;

  @JsonProperty("username")
  private String username = null;

  @JsonProperty("trackableObject")
  @ManyToOne
  @Cascade(org.hibernate.annotations.CascadeType.ALL)
  private Collectable trackableObject = null;

  @JsonProperty("points")
  @Valid
  @ManyToMany
  @Cascade(org.hibernate.annotations.CascadeType.ALL)
  private Set<Point> points = null;

  @JsonProperty("currentCollectable")
  @ManyToOne
  @Cascade(org.hibernate.annotations.CascadeType.ALL)
  private Collectable currentCollectable = null;

  @JsonProperty("foundCollectableTypes")
  @Valid
  @ManyToMany
  @Cascade(org.hibernate.annotations.CascadeType.ALL)
  private Set<CollectableType> foundCollectableTypes = null;

  @JsonProperty("foundGeocodes")
  @Valid
  @ManyToMany
  @Cascade(org.hibernate.annotations.CascadeType.ALL)
  private Set<GeoCode> foundGeocodes = null;

  @JsonProperty("ownedGeocodes")
  @Valid
  @ManyToMany
  @Cascade(org.hibernate.annotations.CascadeType.ALL)
  private Set<GeoCode> ownedGeocodes = null;

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

  public User trackableObject(Collectable trackableObject) {
    this.trackableObject = trackableObject;
    return this;
  }

  /**
   * Get trackableObject
   * @return trackableObject
   **/
  @Schema(description = "")
  
    @Valid
    public Collectable getTrackableObject() {
    return trackableObject;
  }

  public void setTrackableObject(Collectable trackableObject) {
    this.trackableObject = trackableObject;
  }

  public User points(Set<Point> points) {
    this.points = points;
    return this;
  }

  public User addPointsItem(Point pointsItem) {
    if (this.points == null) {
      this.points = new HashSet<Point>();
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
    public Set<Point> getPoints() {
    return points;
  }

  public void setPoints(Set<Point> points) {
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

  public User foundCollectableTypes(Set<CollectableType> foundCollectableTypes) {
    this.foundCollectableTypes = foundCollectableTypes;
    return this;
  }

  public User addFoundCollectableTypesItem(CollectableType foundCollectableTypesItem) {
    if (this.foundCollectableTypes == null) {
      this.foundCollectableTypes = new HashSet<CollectableType>();
    }
    this.foundCollectableTypes.add(foundCollectableTypesItem);
    return this;
  }

  /**
   * Get foundCollectableTypes
   * @return foundCollectableTypes
   **/
  @Schema(description = "")
      @Valid
    public Set<CollectableType> getFoundCollectableTypes() {
    return foundCollectableTypes;
  }

  public void setFoundCollectableTypes(Set<CollectableType> foundCollectableTypes) {
    this.foundCollectableTypes = foundCollectableTypes;
  }

  public User foundGeocodes(Set<GeoCode> foundGeocodes) {
    this.foundGeocodes = foundGeocodes;
    return this;
  }

  public User addFoundGeocodesItem(GeoCode foundGeocodesItem) {
    if (this.foundGeocodes == null) {
      this.foundGeocodes = new HashSet<GeoCode>();
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
    public Set<GeoCode> getFoundGeocodes() {
    return foundGeocodes;
  }

  public void setFoundGeocodes(Set<GeoCode> foundGeocodes) {
    this.foundGeocodes = foundGeocodes;
  }

  public User ownedGeocodes(Set<GeoCode> ownedGeocodes) {
    this.ownedGeocodes = ownedGeocodes;
    return this;
  }

  public User addOwnedGeocodesItem(GeoCode ownedGeocodesItem) {
    if (this.ownedGeocodes == null) {
      this.ownedGeocodes = new HashSet<GeoCode>();
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
    public Set<GeoCode> getOwnedGeocodes() {
    return ownedGeocodes;
  }

  public void setOwnedGeocodes(Set<GeoCode> ownedGeocodes) {
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
        Objects.equals(this.trackableObject, user.trackableObject) &&
        Objects.equals(this.points, user.points) &&
        Objects.equals(this.currentCollectable, user.currentCollectable) &&
        Objects.equals(this.foundCollectableTypes, user.foundCollectableTypes) &&
        Objects.equals(this.foundGeocodes, user.foundGeocodes) &&
        Objects.equals(this.ownedGeocodes, user.ownedGeocodes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username, trackableObject, points, currentCollectable, foundCollectableTypes, foundGeocodes, ownedGeocodes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class User {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    trackableObject: ").append(toIndentedString(trackableObject)).append("\n");
    sb.append("    points: ").append(toIndentedString(points)).append("\n");
    sb.append("    currentCollectable: ").append(toIndentedString(currentCollectable)).append("\n");
    sb.append("    foundCollectableTypes: ").append(toIndentedString(foundCollectableTypes)).append("\n");
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
