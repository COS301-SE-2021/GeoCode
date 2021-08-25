package tech.geocodeapp.geocode.geocode.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;
import tech.geocodeapp.geocode.geocode.model.GeoPoint;

import java.util.Objects;

/**
 * GetCollectablesInGeoCodesByLocationRequest used to specify the attributes needed to find a specific GeoCode
 * by its longitude and latitude
 */
@Validated
public class GetCollectablesInGeoCodesByLocationRequest {

    /**
     * The location of the GeoCode in the real world
     */
    @JsonProperty( "location" )
    private GeoPoint location = null;

    /**
     * Default constructor
     */
    public GetCollectablesInGeoCodesByLocationRequest() {

    }

    /**
     * Overloaded Constructor
     *
     * @param location The longitude and latitude of the GeoCode in the real world
     */
    public GetCollectablesInGeoCodesByLocationRequest( GeoPoint location ) {

        this.location = location;
    }

    /**
     * Sets the location attribute to the specified value
     *
     * @param location the value the attribute should be set to
     *
     * @return the request after the location has been changed
     */
    public GetCollectablesInGeoCodesByLocationRequest location( GeoPoint location ) {

        this.location = location;
        return this;
    }

    /**
     * Gets the saved location attribute
     *
     * @return the stored location attribute
     */
    public GeoPoint getLocation() {

        return location;
    }

    /**
     * Sets the location attribute to the specified value
     *
     * @param location the value the location should be set to
     */
    public void setLocation( GeoPoint location ) {

        this.location = location;
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

        return Objects.equals( this.location, ( ( GetCollectablesInGeoCodesByLocationRequest ) obj ).location );
    }

    /**
     * Creates a hash code from the attributes in the class
     *
     * @return the created has code
     */
    @Override
    public int hashCode() {

        return Objects.hash( location );
    }

    /**
     * Creates a string from all the attributes in the class
     *
     * @return the created string
     */
    @Override
    public String toString() {

        return "class GetCollectablesInGeoCodesByLocationRequest {\n" +
                "    longitude: " + toIndentedString( location ) + "\n" +
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
