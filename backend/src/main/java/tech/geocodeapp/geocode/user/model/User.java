package tech.geocodeapp.geocode.user.model;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import org.hibernate.annotations.Cascade;
import tech.geocodeapp.geocode.collectable.model.Collectable;
import tech.geocodeapp.geocode.collectable.model.CollectableType;
import tech.geocodeapp.geocode.geocode.model.GeoCode;
import tech.geocodeapp.geocode.leaderboard.model.Point;
import org.springframework.validation.annotation.Validated;
import tech.geocodeapp.geocode.mission.model.Mission;

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
  private java.util.UUID id = null;

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

  public User(java.util.UUID id) {
    this.id = id;
  }

  public User(java.util.UUID id, String username) {
    this.id = id;
    this.username = username;
  }

    public User id(java.util.UUID id) {
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
    public java.util.UUID getId() {
    return id;
  }

  public void setId(java.util.UUID id) {
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
    this.points.add(pointsItem);
    return this;
  }

  /**
   * Get points
   * @return points
   **/
  @Schema(description = "")
      @Valid
    public Collection<Point> getPoints() {
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

  public Set<Mission> getMissions() {
    return this.missions;
  }

  public void setMissions(Set<Mission> missions) {
    this.missions = missions;
  }

  public User addMissionsItem(Mission missionsItem) {
    this.missions.add(missionsItem);
    return this;
  }


}
