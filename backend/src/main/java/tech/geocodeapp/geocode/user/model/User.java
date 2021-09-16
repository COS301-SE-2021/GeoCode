package tech.geocodeapp.geocode.user.model;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import org.hibernate.annotations.Cascade;
import tech.geocodeapp.geocode.collectable.model.*;
import tech.geocodeapp.geocode.geocode.model.GeoCode;
import tech.geocodeapp.geocode.leaderboard.model.Point;
import tech.geocodeapp.geocode.mission.model.Mission;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * User
 */
@Entity
@Table(name="user_table")
public class User {
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
  private Set<Point> points = new HashSet<>();

  @JsonProperty("currentCollectable")
  @ManyToOne
  @Cascade(org.hibernate.annotations.CascadeType.ALL)
  private Collectable currentCollectable = null;

  @JsonProperty("foundCollectableTypes")
  @Valid
  @ManyToMany
  @Cascade(org.hibernate.annotations.CascadeType.ALL)
  private Set<CollectableType> foundCollectableTypes = new HashSet<>();

  @JsonProperty("foundGeocodes")
  @Valid
  @ManyToMany
  @Cascade(org.hibernate.annotations.CascadeType.ALL)
  private Set<GeoCode> foundGeocodes = new HashSet<>();

  @JsonProperty("ownedGeocodes")
  @Valid
  @ManyToMany
  @Cascade(org.hibernate.annotations.CascadeType.ALL)
  private Set<GeoCode> ownedGeocodes = new HashSet<>();

  @JsonProperty("missions")
  @Valid
  @ManyToMany
  @Cascade(org.hibernate.annotations.CascadeType.ALL)
  private Set<Mission> missions = new HashSet<>();

  public User() {

  }

  public User(UUID id, String username) {
    this.id = id;
    this.username = username;
  }

    public User id(UUID id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   **/
  @Schema(required = true)
  @NotNull
  @Valid
  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  /**
   * Get username
   * @return username
   **/
  @Schema(required = true)
  @NotNull
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * Get trackableObject
   * @return trackableObject
   **/
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

  /**
   * Get points
   * @return points
   **/
  public Collection<Point> getPoints() {
    return points;
  }

  public void setPoints(Set<Point> points) {
    this.points = points;
  }

  /**
   * Get currentCollectable
   * @return currentCollectable
   **/
  public Collectable getCurrentCollectable() {
    return currentCollectable;
  }

  public void setCurrentCollectable(Collectable currentCollectable) {
    this.currentCollectable = currentCollectable;
  }

  public void addFoundCollectableTypesItem(CollectableType foundCollectableTypesItem) {
    this.foundCollectableTypes.add(foundCollectableTypesItem);
  }

  /**
   * Get foundCollectableTypes
   * @return foundCollectableTypes
   **/
  public Set<CollectableType> getFoundCollectableTypes() {
    return foundCollectableTypes;
  }

  public void addFoundGeocodesItem(GeoCode foundGeocodesItem) {
    this.foundGeocodes.add(foundGeocodesItem);
  }

  /**
   * Get foundGeocodes
   * @return foundGeocodes
   **/
  public Set<GeoCode> getFoundGeocodes() {
    return foundGeocodes;
  }

  public void addOwnedGeocodesItem(GeoCode ownedGeocodesItem) {
    this.ownedGeocodes.add(ownedGeocodesItem);
  }

  /**
   * Get ownedGeocodes
   * @return ownedGeocodes
   **/
  public Set<GeoCode> getOwnedGeocodes() {
    return ownedGeocodes;
  }

  public Set<Mission> getMissions() {
    return this.missions;
  }

  public void addMissionsItem(Mission missionsItem) {
    this.missions.add(missionsItem);
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
            Objects.equals(this.ownedGeocodes, user.ownedGeocodes) &&
            Objects.equals(this.missions, user.missions);
  }

  /**
   * Creates a hash code from the attributes in the class
   *
   * @return the created has code
   */
  @Override
  public int hashCode() {
    return Objects.hash( id, username, trackableObject, points, currentCollectable, foundCollectableTypes, foundGeocodes, ownedGeocodes, missions );
  }
}
