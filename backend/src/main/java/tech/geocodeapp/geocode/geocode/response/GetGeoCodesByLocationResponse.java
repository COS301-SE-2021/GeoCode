package tech.geocodeapp.geocode.geocode.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

import tech.geocodeapp.geocode.geocode.model.Difficulty;
import tech.geocodeapp.geocode.geocode.model.GeoPoint;

import java.util.Objects;
import java.util.UUID;

/**
 * GetGeoCodeByLocationResponse used to access the attributes received from the response
 * to get the specified GeoCode from the given location
 */
@Validated
public class GetGeoCodesByLocationResponse {

    /**
     * The unique ID of the found GeoCode with the specified QR Code
     */
    @JsonProperty( "id" )
    private UUID id = null;

    /**
     * If the found GeoCode is available to be found
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
    @JsonProperty( "location" )
    private GeoPoint location = null;

    /**
     * The difficulty of the found GeoCode
     */
    @JsonProperty( "difficulty" )
    private Difficulty difficulty = null;

    /**
     * Default constructor
     */
    public GetGeoCodesByLocationResponse() {

    }

    /**
     * Overloaded Constructor
     *
     * @param id The unique ID of the found GeoCode with the specified QR Code
     * @param available If the the found GeoCode is available to be found
     * @param description The description of the found GeoCode
     * @param location The longitude and latitude of the found GeoCode
     * @param difficulty The difficulty of the found GeoCode
     */
    public GetGeoCodesByLocationResponse(UUID id, Boolean available, String description, GeoPoint location, Difficulty difficulty ) {

        this.id = id;
        this.available = available;
        this.description = description;
        this.location = location;
        this.difficulty = difficulty;
    }

    /**
     * Sets the id attribute to the specified value
     *
     * @param id the value the attribute should be set to
     *
     * @return the response after the id has been changed
     */
    public GetGeoCodesByLocationResponse id(UUID id ) {

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
    public GetGeoCodesByLocationResponse available(Boolean available ) {

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
    public GetGeoCodesByLocationResponse description(String description ) {

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
     * Sets the location attribute to the specified value
     *
     * @param location the value the attribute should be set to
     *
     * @return the response after the location has been changed
     */
    public GetGeoCodesByLocationResponse longitude(GeoPoint location ) {

        this.location = location;
        return this;
    }

    /**
     * Gets the saved location attribute
     *
     * @return the stored location attribute
     */
    public GeoPoint getLongitude() {

        return location;
    }

    /**
     * Sets the location attribute to the specified value
     *
     * @param location the value the attribute should be set to
     */
    public void setLongitude( GeoPoint location ) {

        this.location = location;
    }

    /**
     * Sets the difficulty attribute to the specified value
     *
     * @param difficulty the value the attribute should be set to
     *
     * @return the response after the latitude has been changed
     */
    public GetGeoCodesByLocationResponse difficulty(Difficulty difficulty ) {

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
        var getGeoCodeByLocationResponse = (GetGeoCodesByLocationResponse) obj;
        return Objects.equals( this.id, getGeoCodeByLocationResponse.id ) &&
                Objects.equals( this.available, getGeoCodeByLocationResponse.available ) &&
                Objects.equals( this.description, getGeoCodeByLocationResponse.description ) &&
                Objects.equals( this.location, getGeoCodeByLocationResponse.location ) &&
                Objects.equals( this.difficulty, getGeoCodeByLocationResponse.difficulty );
    }

    /**
     * Creates a hash code from the attributes in the class
     *
     * @return the created has code
     */
    @Override
    public int hashCode() {

        return Objects.hash( id, available, description, location, difficulty );
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
                "    longitude: " + toIndentedString( location ) + "\n" +
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
