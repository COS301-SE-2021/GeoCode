package tech.geocodeapp.geocode.GeoCode.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

import tech.geocodeapp.geocode.Collectable.Model.Difficulty;

import java.util.Objects;
import java.util.UUID;

/**
 * GetGeoCodeByLocationResponse used to access the attributes received from the response
 * to get the specified GeoCode from the given location
 */
@Validated
public class GetGeoCodeByLocationResponse {

    /**
     * The unique ID of the found GeoCode with the specified QR Code
     */
    @JsonProperty( "id" )
    private UUID id = null;

    /**
     * If the the found GeoCode is available to be found
     */
    @JsonProperty( "available" )
    private Boolean available = null;

    /**
     * The description of the found GeoCode
     */
    @JsonProperty( "description" )
    private String description = null;

    /**
     * The longitude of the found GeoCode
     */
    @JsonProperty( "longitude" )
    private String longitude = null;

    /**
     * The latitude of the found GeoCode
     */
    @JsonProperty( "latitude" )
    private String latitude = null;

    /**
     * The difficulty of the found GeoCode
     */
    @JsonProperty( "difficulty" )
    private Difficulty difficulty = null;

    /**
     * Default constructor
     */
    public GetGeoCodeByLocationResponse() {

    }

    /**
     * Overloaded Constructor
     *
     * @param id The unique ID of the found GeoCode with the specified QR Code
     * @param available If the the found GeoCode is available to be found
     * @param description The description of the found GeoCode
     * @param longitude The longitude of the found GeoCode
     * @param latitude The latitude of the found GeoCode
     * @param difficulty The difficulty of the found GeoCode
     */
    public GetGeoCodeByLocationResponse( UUID id, Boolean available, String description, String longitude, String latitude, Difficulty difficulty ) {

        this.id = id;
        this.available = available;
        this.description = description;
        this.longitude = longitude;
        this.latitude = latitude;
        this.difficulty = difficulty;
    }

    /**
     * Sets the id attribute to the specified value
     *
     * @param id the value the attribute should be set to
     *
     * @return the response after the id has been changed
     */
    public GetGeoCodeByLocationResponse id( UUID id ) {

        this.id = id;
        return this;
    }

    /**
     * Gets the saved id attribute
     *
     * @return the stored id attribute
     */
    @Valid
    public UUID getId() {

        return id;
    }

    /**
     * Sets the id attribute to the specified value
     *
     * @param id the value the attribute should be set to
     */
    public void setId( UUID id ) {

        this.id = id;
    }

    /**
     * Sets the available attribute to the specified value
     *
     * @param available the value the attribute should be set to
     *
     * @return the response after the available has been changed
     */
    public GetGeoCodeByLocationResponse available( Boolean available ) {

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
     * Sets the description attribute to the specified value
     *
     * @param description the value the attribute should be set to
     *
     * @return the response after the description has been changed
     */
    public GetGeoCodeByLocationResponse description( String description ) {

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
     * @return the response after the longitude has been changed
     */
    public GetGeoCodeByLocationResponse longitude( String longitude ) {

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
     * @param longitude the value the attribute should be set to
     */
    public void setLongitude( String longitude ) {

        this.longitude = longitude;
    }

    /**
     * Sets the latitude attribute to the specified value
     *
     * @param latitude the value the attribute should be set to
     *
     * @return the response after the latitude has been changed
     */
    public GetGeoCodeByLocationResponse latitude( String latitude ) {

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
     * Sets the difficulty attribute to the specified value
     *
     * @param difficulty the value the attribute should be set to
     *
     * @return the response after the latitude has been changed
     */
    public GetGeoCodeByLocationResponse difficulty( Difficulty difficulty ) {

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
        GetGeoCodeByLocationResponse getGeoCodeByLocationResponse = ( GetGeoCodeByLocationResponse ) obj;
        return Objects.equals( this.id, getGeoCodeByLocationResponse.id ) &&
                Objects.equals( this.available, getGeoCodeByLocationResponse.available ) &&
                Objects.equals( this.description, getGeoCodeByLocationResponse.description ) &&
                Objects.equals( this.longitude, getGeoCodeByLocationResponse.longitude ) &&
                Objects.equals( this.latitude, getGeoCodeByLocationResponse.latitude ) &&
                Objects.equals( this.difficulty, getGeoCodeByLocationResponse.difficulty );
    }

    /**
     * Creates a hash code from the attributes in the class
     *
     * @return the created has code
     */
    @Override
    public int hashCode() {

        return Objects.hash( id, available, description, longitude, latitude, difficulty );
    }

    /**
     * Creates a string from all the attributes in the class
     *
     * @return the created string
     */
    @Override
    public String toString() {

        return "class GetGeoCodeByLocationResponse {\n" +
                "    id: " + toIndentedString( id ) + "\n" +
                "    available: " + toIndentedString( available ) + "\n" +
                "    description: " + toIndentedString( description ) + "\n" +
                "    longitude: " + toIndentedString( longitude ) + "\n" +
                "    latitude: " + toIndentedString( latitude ) + "\n" +
                "    difficulty: " + toIndentedString( difficulty ) + "\n" +
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
