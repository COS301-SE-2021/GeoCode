package tech.geocodeapp.geocode.geocode.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;
import tech.geocodeapp.geocode.geocode.model.Difficulty;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * CreateGeoCodeRequest used to specify the attributes needed to create a new GeoCode object
 */
@Validated
public class UpdateGeoCodeRequest {

    /**
     * The unique ID of a GeoCode in the database
     */
    @JsonProperty( "geoCodeID" )
    private UUID geoCodeID = null;

    /**
     * The description of where the GeoCode is and what it involves
     */
    @JsonProperty( "description" )
    private String description = null;

    /**
     * The list of hints provided by the user who created the GeoCode
     * to help a user searching for the GeoCode find it
     */
    @Valid
    @JsonProperty( "hints" )
    private List< String > hints = new ArrayList<>();

    /**
     * The level of difficulty to find a GeoCode in the real world
     */
    @JsonProperty( "difficulty" )
    private Difficulty difficulty = null;

    /**
     * If the GeoCode is active in the system
     */
    @JsonProperty( "available" )
    private Boolean available = null;

    /**
     * Default constructor
     */
    public UpdateGeoCodeRequest() {

    }

    /**
     * Overloaded Constructor
     *
     * @param geoCodeID The unique ID of a GeoCode in the database
     * @param description The description of where the GeoCode is and what it involves
     * @param hints       The list of hints provided by the user who created the GeoCode to help a user searching for the GeoCode find it
     * @param difficulty  The level of difficulty to find a GeoCode in the real world
     * @param available   If the GeoCode is active in the system
     */
    public UpdateGeoCodeRequest( UUID geoCodeID, String description, List< String > hints, Difficulty difficulty, Boolean available ) {

        this.description = description;
        this.hints = hints;
        this.difficulty = difficulty;
        this.available = available;
    }

    /**
     * Sets the geoCodeID attribute to the specified value
     *
     * @param geoCodeID the value the attribute should be set to
     *
     * @return the request after the geoCodeID has been changed
     */
    public UpdateGeoCodeRequest geoCodeID( UUID geoCodeID ) {

        this.geoCodeID = geoCodeID;
        return this;
    }

    /**
     * Gets the saved geoCodeID attribute
     *
     * @return the stored geoCodeID attribute
     */
    @Valid
    public UUID getGeoCodeID() {

        return geoCodeID;
    }

    /**
     * Sets the geoCodeID attribute to the specified value
     *
     * @param geoCodeID the value the attribute should be set to
     */
    public void setGeoCodeID( UUID geoCodeID ) {

        this.geoCodeID = geoCodeID;
    }

    /**
     * Sets the description attribute to the specified value
     *
     * @param description the value the attribute should be set to
     *
     * @return the request after the description has been changed
     */
    public UpdateGeoCodeRequest description( String description ) {

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
    public void setDescription( String description ) {

        this.description = description;
    }

    /**
     * Sets the hints attribute to the specified value
     *
     * @param hints the value the attribute should be set to
     *
     * @return the request after the hints has been changed
     */
    public UpdateGeoCodeRequest hints( List< String > hints ) {

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
    public UpdateGeoCodeRequest addHintsItem( String hintsItem ) {

        this.hints.add( hintsItem );
        return this;
    }

    /**
     * Gets the saved hints attribute
     *
     * @return the stored hints attribute
     */
    public List< String > getHints() {

        return hints;
    }

    /**
     * Sets the hints attribute to the specified value
     *
     * @param hints the value the attribute should be set to
     */
    public void setHints( List< String > hints ) {

        this.hints = hints;
    }

    /**
     * Sets the difficulty attribute to the specified value
     *
     * @param difficulty the value the attribute should be set to
     *
     * @return the request after the difficulty has been changed
     */
    public UpdateGeoCodeRequest difficulty( Difficulty difficulty ) {

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
    public void setDifficulty( Difficulty difficulty ) {

        this.difficulty = difficulty;
    }

    /**
     * Sets the available attribute to the specified value
     *
     * @param available the value the attribute should be set to
     *
     * @return the request after the available has been changed
     */
    public UpdateGeoCodeRequest available( Boolean available ) {

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
    public void setAvailable( Boolean available ) {

        this.available = available;
    }

    /**
     * Determines if the specified object is the same as the current object
     *
     * @param obj the object we want to compare with the specific attributes of this class
     *
     * @return if the object is the same or not
     */
    @Override
    public boolean equals( Object obj ) {

        if ( this == obj ) {

            return true;
        }
        if ( obj == null || getClass() != obj.getClass() ) {

            return false;
        }

        var createGeoCodeRequest = ( UpdateGeoCodeRequest ) obj;
        return  Objects.equals( this.description, createGeoCodeRequest.description ) &&
                Objects.equals( this.hints, createGeoCodeRequest.hints ) &&
                Objects.equals( this.difficulty, createGeoCodeRequest.difficulty ) &&
                Objects.equals( this.geoCodeID, createGeoCodeRequest.geoCodeID ) &&
                Objects.equals( this.available, createGeoCodeRequest.available );
    }

    /**
     * Creates a hash code from the attributes in the class
     *
     * @return the created has code
     */
    @Override
    public int hashCode() {

        return Objects.hash( description, hints, difficulty, available, geoCodeID );
    }

    /**
     * Creates a string from all the attributes in the class
     *
     * @return the created string
     */
    @Override
    public String toString() {

        return "class CreateGeoCodeRequest {\n" +
                "    description: " + toIndentedString( description ) + "\n" +
                "    hints: " + toIndentedString( hints ) + "\n" +
                "    difficulty: " + toIndentedString( difficulty ) + "\n" +
                "    available: " + toIndentedString( available ) + "\n" +
                "    geoCodeID: " + toIndentedString( geoCodeID ) + "\n" +
                "}";
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString( Object o ) {

        if ( o == null ) {

            return "null";
        }
        return o.toString().replace( "\n", "\n    " );
    }

}
