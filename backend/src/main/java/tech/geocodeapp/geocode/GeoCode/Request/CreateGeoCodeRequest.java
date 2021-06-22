package tech.geocodeapp.geocode.GeoCode.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

import java.util.ArrayList;
import java.util.Objects;
import java.util.List;

import tech.geocodeapp.geocode.Collectable.Model.Difficulty;

/**
 * CreateGeoCodeRequest used to specify the attributes needed to create a new GeoCode object
 */
@Validated
public class CreateGeoCodeRequest {

    /**
     * The description of where the GeoCode is and what it involves
     */
    @JsonProperty( "description" )
    private String description = null;

    /**
     * The longitude of the location of the GeoCode in the real world
     */
    @JsonProperty( "longitude" )
    private String longitude = null;

    /**
     * The latitude of the location of the GeoCode in the real world
     */
    @JsonProperty( "latitude" )
    private String latitude = null;

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
     * Sets the description attribute to the specified value
     *
     * @param description the value the attribute should be set to
     *
     * @return the request after the description has been changed
     */
    public CreateGeoCodeRequest description( String description ) {

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
     * Sets the longitude attribute to the specified value
     *
     * @param longitude the value the attribute should be set to
     *
     * @return the request after the longitude has been changed
     */
    public CreateGeoCodeRequest longitude( String longitude ) {

        this.longitude = longitude;
        return this;
    }

    /**
     * Gets the saved longitude attribute
     *
     * @return the stored longitude attribute
     */
    public String getLongitude() {

        return longitude;
    }

    /**
     * Sets the longitude attribute to the specified value
     *
     * @param longitude the value the longitude should be set to
     */
    public void setLongitude( String longitude ) {

        this.longitude = longitude;
    }

    /**
     * Sets the latitude attribute to the specified value
     *
     * @param latitude the value the attribute should be set to
     *
     * @return the request after the latitude has been changed
     */
    public CreateGeoCodeRequest latitude( String latitude ) {

        this.latitude = latitude;
        return this;
    }

    /**
     * Gets the saved latitude attribute
     *
     * @return the stored latitude attribute
     */
    public String getLatitude() {

        return latitude;
    }

    /**
     * Sets the latitude attribute to the specified value
     *
     * @param latitude the value the attribute should be set to
     */
    public void setLatitude( String latitude ) {

        this.latitude = latitude;
    }

    /**
     * Sets the hints attribute to the specified value
     *
     * @param hints the value the attribute should be set to
     *
     * @return the request after the hints has been changed
     */
    public CreateGeoCodeRequest hints( List< String > hints ) {

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
    public CreateGeoCodeRequest addHintsItem( String hintsItem ) {

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
    public CreateGeoCodeRequest difficulty( Difficulty difficulty ) {

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
    public CreateGeoCodeRequest available( Boolean available ) {

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
    public boolean equals( java.lang.Object obj ) {

        if ( this == obj ) {

            return true;
        }
        if ( obj == null || getClass() != obj.getClass() ) {

            return false;
        }
        CreateGeoCodeRequest createGeoCodeRequest = ( CreateGeoCodeRequest ) obj;
        return  Objects.equals( this.description, createGeoCodeRequest.description ) &&
                Objects.equals( this.longitude, createGeoCodeRequest.longitude ) &&
                Objects.equals( this.latitude, createGeoCodeRequest.latitude ) &&
                Objects.equals( this.hints, createGeoCodeRequest.hints ) &&
                Objects.equals( this.difficulty, createGeoCodeRequest.difficulty ) &&
                Objects.equals( this.available, createGeoCodeRequest.available );
    }

    /**
     * Creates a hash code from the attributes in the class
     *
     * @return the created has code
     */
    @Override
    public int hashCode() {

        return Objects.hash( description, longitude, latitude, hints, difficulty, available );
    }

    /**
     * Creates a string from all the attributes in the class
     *
     * @return the created string
     */
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append( "class CreateGeoCodeRequest {\n" );

        sb.append( "    description: " ).append( toIndentedString( description ) ).append( "\n" );
        sb.append( "    longitude: " ).append( toIndentedString( longitude ) ).append( "\n" );
        sb.append( "    latitude: " ).append( toIndentedString( latitude ) ).append( "\n" );
        sb.append( "    hints: " ).append( toIndentedString( hints ) ).append( "\n" );
        sb.append( "    difficulty: " ).append( toIndentedString( difficulty ) ).append( "\n" );
        sb.append( "    available: " ).append( toIndentedString( available ) ).append( "\n" );
        sb.append( "}" );
        return sb.toString();
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
