package tech.geocodeapp.geocode.geocode.model;

import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Cascade;
import javax.validation.Valid;
import javax.persistence.*;

import tech.geocodeapp.geocode.collectable.model.*;

import java.util.*;

/**
 * The GeoCode model that will be stored as a table in the db
 */
@Entity
@Validated
@Table( name = "geocode" )
public class GeoCode {

    /**
     * The unique identifier for the GeoCode
     */
    @Id
    @JsonProperty( "id" )
    @NotNull( message = "GeoCode's id cannot be null." )
    private UUID id;

    /**
     * The description of where the GeoCode is and what it involves
     */
    @JsonProperty( "difficulty" )
    @NotNull( message = "GeoCode's difficulty cannot be null." )
    private Difficulty difficulty;

    /**
     * If the GeoCode is active in the system
     */
    @JsonProperty( "available" )
    @NotNull( message = "GeoCode's available cannot be null." )
    private Boolean available;

    /**
     * The description of where the GeoCode is and what it involves
     */
    @JsonProperty( "description" )
    @NotEmpty( message = "GeoCode's description cannot be empty." )
    private String description;

    /**
     * The list of hints provided by the user who created the GeoCode
     * to help a user searching for the GeoCode find it
     */
    @Valid
    @JsonProperty( "hints" )
    @ElementCollection( fetch = FetchType.LAZY )
    @NotNull( message = "GeoCode's hints cannot be null." )
    private Collection< String > hints = new ArrayList<>();

    /**
     * The list of collectables stored inside of the GeoCode
     */
    @Valid
    @JsonProperty( "collectables" )
    @ElementCollection( fetch = FetchType.EAGER )
    @Cascade( org.hibernate.annotations.CascadeType.ALL )
    @NotNull( message = "GeoCode's collectables cannot be null." )
    private Collection< UUID > collectables;

    /**
     * A short unique identifier to find the GeoCode in the system
     * by the user from the real world
     */
    @JsonProperty( "qrCode" )
    @NotEmpty( message = "GeoCode's qrCode cannot be empty." )
    private String qrCode;

    /**
     * The longitude and latitude of the GeoCode in the real world
     */
    @JsonProperty( "location" )
    @NotNull( message = "GeoCode's location cannot be empty." )
    private GeoPoint location;

    /**
     * The ID of the user whom created the GeoCode
     */
    @JsonProperty( "createdBy" )
    @NotNull( message = "GeoCode's createdBy cannot be null." )
    private UUID createdBy;

    /**
     * Default constructor
     */
    public GeoCode() {

    }

    /**
     * Overloaded Constructor
     *
     * @param id The unique identifier for the GeoCode
     * @param difficulty The description of where the GeoCode is and what it involves
     * @param available If the GeoCode is active in the system
     * @param description The description of where the GeoCode is and what it involves
     * @param hints The list of hints provided by the user who created the GeoCode to help a user searching for the GeoCode find it
     * @param collectables The list of collectables stored inside of the GeoCode
     * @param qrCode A short unique identifier to find the GeoCode in the system by the user from the real world
     * @param location The longitude and latitude of the GeoCode in the real world
     * @param createdBy The user's ID who created the GeoCode
     */
    @Valid
    public GeoCode( UUID id, Difficulty difficulty, Boolean available, String description, Collection< String > hints,
                    Collection< UUID > collectables, String qrCode, GeoPoint location, UUID createdBy ) {

        this.id = id;
        this.difficulty = difficulty;
        this.available = available;
        this.description = description;
        this.hints = hints;
        this.collectables = collectables;
        this.qrCode = qrCode;
        this.location = location;
        this.createdBy = createdBy;
    }

    /**
     * Sets the id attribute to the specified value
     *
     * @param id the unique id to set the GeoCode to
     *
     * @return the model after changing the id
     */
    @Valid
    public GeoCode id( UUID id ) {

        this.id = id;
        return this;
    }

    /**
     * Gets the saved id attribute
     *
     * @return the stored id attribute
     */
    public UUID getId() {

        return id;
    }

    /**
     * Sets the id attribute to the specified value
     *
     * @param id the value the id should be set to
     */
    @Valid
    public void setId( UUID id ) {

        this.id = id;
    }

    /**
     * Sets the difficulty attribute to the specified value
     *
     * @param difficulty the value the attribute should be set to
     *
     * @return the model after the difficulty has been changed
     */
    @Valid
    public GeoCode difficulty( Difficulty difficulty ) {

        this.difficulty = difficulty;
        return this;
    }

    /**
     * Gets the saved difficulty attribute
     *
     * @return the stored difficulty attribute
     */
    @Valid
    public Difficulty getDifficulty() {

        return difficulty;
    }

    /**
     * Sets the difficulty attribute to the specified value
     *
     * @param difficulty the value the attribute should be set to
     */
    @Valid
    public void setDifficulty( Difficulty difficulty ) {

        this.difficulty = difficulty;
    }

    /**
     * Sets the available attribute to the specified value
     *
     * @param available the value the attribute should be set to
     *
     * @return the model after the available has been changed
     */
    @Valid
    public GeoCode available( Boolean available ) {

        this.available = available;
        return this;
    }

    /**
     * Gets the saved available attribute
     *
     * @return the stored available attribute
     */
    public Boolean isAvailable() {

        return available;
    }

    /**
     * Sets the available attribute to the specified value
     *
     * @param available the value the attribute should be set to
     */
    @Valid
    public void setAvailable( Boolean available ) {

        this.available = available;
    }

    /**
     * Sets the description attribute to the specified value
     *
     * @param description the value the attribute should be set to
     *
     * @return the model after the description has been changed
     */
    @Valid
    public GeoCode description( String description ) {

        this.description = description;
        return this;
    }

    /**
     * Gets the saved description attribute
     *
     * @return the stored description attribute
     */
    public String getDescription() {

        return description;
    }

    /**
     * Sets the description attribute to the specified value
     *
     * @param description the value the attribute should be set to
     */
    @Valid
    public void setDescription( String description ) {

        this.description = description;
    }

    /**
     * Sets the hints attribute to the specified value
     *
     * @param hints the value the attribute should be set to
     *
     * @return the model after the hints has been changed
     */
    @Valid
    public GeoCode hints( Collection< String > hints ) {

        this.hints = hints;
        return this;
    }

    /**
     * Sets a single hint inside of the hints attribute to the specified value
     *
     * @param hintsItem the value the attribute should be set to
     *
     * @return the stored hints attribute
     */
    @Valid
    public GeoCode addHintsItem( String hintsItem ) {

        this.hints.add( hintsItem );
        return this;
    }

    /**
     * Gets the saved hints attribute
     *
     * @return the stored hints attribute
     */
    public Collection< String > getHints() {

        return hints;
    }

    /**
     * Sets the hints attribute to the specified value
     *
     * @param hints the value the attribute should be set to
     */
    @Valid
    public void setHints( Collection< String > hints ) {

        this.hints = hints;
    }

    /**
     * Sets the collectables attribute to the specified value
     *
     * @param collectables the value the attribute should be set to
     *
     * @return the model after the collectables has been changed
     */
    @Valid
    public GeoCode collectables( List< UUID > collectables ) {

        this.collectables = collectables;
        return this;
    }

    /**
     * Sets a single hint inside of the collectables attribute to the specified value
     *
     * @param collectablesItem the value the attribute should be set to
     *
     * @return the stored collectables attribute
     */
    @Valid
    public GeoCode addCollectablesItem( UUID collectablesItem ) {

        if ( this.collectables == null ) {

            this.collectables = new ArrayList<>();
        }

        this.collectables.add( collectablesItem );
        return this;
    }

    /**
     * Gets the saved collectables attribute
     *
     * @return the stored collectables attribute
     */
    public Collection< UUID > getCollectables() {

        return collectables;
    }

    /**
     * Sets the collectables attribute to the specified value
     *
     * @param collectables the value the attribute should be set to
     */
    @Valid
    public void setCollectables( Collection< UUID > collectables ) {

        this.collectables = collectables;
    }

    /**
     * Sets the qrCode attribute to the specified value
     *
     * @param qrCode the value the attribute should be set to
     *
     * @return the model after the qrCode has been changed
     */
    @Valid
    public GeoCode qrCode( String qrCode ) {

        this.qrCode = qrCode;
        return this;
    }

    /**
     * Gets the saved qrCode attribute
     *
     * @return the stored qrCode attribute
     */
    public String getQrCode() {

        return qrCode;
    }

    /**
     * Sets the qrCode attribute to the specified value
     *
     * @param qrCode the value the qrCode should be set to
     */
    @Valid
    public void setQrCode( String qrCode ) {

        this.qrCode = qrCode;
    }

    /**
     * Sets the location attribute to the specified value
     *
     * @param location the value the attribute should be set to
     *
     * @return the model after the location has been changed
     */
    @Valid
    public GeoCode location( GeoPoint location ) {

        this.location = location;
        return this;
    }

    /**
     * Gets the saved longitude attribute
     *
     * @return the stored longitude attribute
     */
    public GeoPoint getlocation() {

        return location;
    }

    /**
     * Sets the location attribute to the specified value
     *
     * @param location the value the location should be set to
     */
    @Valid
    public void setlocation( GeoPoint location ) {

        this.location = location;
    }

    /**
     * Sets the createdBy attribute to the specified value
     *
     * @param createdBy the unique createdBy to set the GeoCode to
     *
     * @return the model after changing the createdBy
     */
    @Valid
    public GeoCode createdBy( UUID createdBy ) {

        this.createdBy = createdBy;
        return this;
    }

    /**
     * Gets the saved id attribute
     *
     * @return the stored createdBy attribute
     */
    public UUID getCreatedBy() {

        return createdBy;
    }

    /**
     * Sets the id attribute to the specified value
     *
     * @param createdBy the value the id should be set to
     */
    @Valid
    public void setCreatedBy( UUID createdBy ) {

        this.createdBy = createdBy;
    }

    /**
     * Determines if the specified object is the same as the current object
     *
     * @param obj the object we want to compare with the specific attributes of this class
     *
     * @return if the object is the same or not
     */
    @Override
    public boolean equals( java.lang.Object obj ) {

        if ( this == obj ) {

            return true;
        }
        if ( obj == null || getClass() != obj.getClass() ) {

            return false;
        }

        GeoCode geoCode = ( GeoCode ) obj;
        return Objects.equals( this.id, geoCode.id ) &&
                Objects.equals( this.difficulty, geoCode.difficulty ) &&
                Objects.equals( this.available, geoCode.available ) &&
                Objects.equals( this.description, geoCode.description ) &&
                Objects.equals( this.hints, geoCode.hints ) &&
                Objects.equals( this.collectables, geoCode.collectables ) &&
                Objects.equals( this.qrCode, geoCode.qrCode ) &&
                Objects.equals( this.location, geoCode.location ) &&
                Objects.equals( this.createdBy, geoCode.createdBy );
    }

    /**
     * Creates a hash code from the attributes in the class
     *
     * @return the created has code
     */
    @Override
    public int hashCode() {

        return Objects.hash( id, difficulty, available, description, hints, collectables, qrCode, location, createdBy );
    }

    /**
     * Creates a string from all the attributes in the class
     *
     * @return the created string
     */
    @Override
    public String toString() {

        return "class GeoCode {\n" +
                "    id: " + toIndentedString( id ) + "\n" +
                "    difficulty: " + toIndentedString( difficulty ) + "\n" +
                "    available: " + toIndentedString( available ) + "\n" +
                "    description: " + toIndentedString( description ) + "\n" +
                "    hints: " + toIndentedString( hints ) + "\n" +
                "    collectables: " + toIndentedString( collectables ) + "\n" +
                "    qrCode: " + toIndentedString( qrCode ) + "\n" +
                "    location: " + toIndentedString( location ) + "\n" +
                "    createdBy: " + toIndentedString( createdBy ) + "\n" +
                "}";
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString( java.lang.Object o ) {

        if ( o == null ) {

            return "null";
        }

        return o.toString().replace( "\n", "\n    " );
    }

}
