package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import tech.geocodeapp.geocode.swagger.model.Collectable;
import tech.geocodeapp.geocode.swagger.model.GeoCode;
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
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-01T08:10:17.138Z[GMT]")


public class User   {
  @JsonProperty("id")
  private UUID id = null;

  @JsonProperty("username")
  private String username = null;

  @JsonProperty("currentCollectable")
  private Collectable currentCollectable = null;

  @JsonProperty("foundCollectables")
  @Valid
  private List<Collectable> foundCollectables = null;

  @JsonProperty("foundGeocodes")
  @Valid
  private List<GeoCode> foundGeocodes = null;

  @JsonProperty("myGeocodes")
  @Valid
  private List<GeoCode> myGeocodes = null;

  public User id(UUID id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   **/
  @Schema(description = "")
  
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

  public User myGeocodes(List<GeoCode> myGeocodes) {
    this.myGeocodes = myGeocodes;
    return this;
  }

  public User addMyGeocodesItem(GeoCode myGeocodesItem) {
    if (this.myGeocodes == null) {
      this.myGeocodes = new ArrayList<GeoCode>();
    }
    this.myGeocodes.add(myGeocodesItem);
    return this;
  }

  /**
   * Get myGeocodes
   * @return myGeocodes
   **/
  @Schema(description = "")
      @Valid
    public List<GeoCode> getMyGeocodes() {
    return myGeocodes;
  }

  public void setMyGeocodes(List<GeoCode> myGeocodes) {
    this.myGeocodes = myGeocodes;
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
        Objects.equals(this.currentCollectable, user.currentCollectable) &&
        Objects.equals(this.foundCollectables, user.foundCollectables) &&
        Objects.equals(this.foundGeocodes, user.foundGeocodes) &&
        Objects.equals(this.myGeocodes, user.myGeocodes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username, currentCollectable, foundCollectables, foundGeocodes, myGeocodes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class User {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    currentCollectable: ").append(toIndentedString(currentCollectable)).append("\n");
    sb.append("    foundCollectables: ").append(toIndentedString(foundCollectables)).append("\n");
    sb.append("    foundGeocodes: ").append(toIndentedString(foundGeocodes)).append("\n");
    sb.append("    myGeocodes: ").append(toIndentedString(myGeocodes)).append("\n");
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
