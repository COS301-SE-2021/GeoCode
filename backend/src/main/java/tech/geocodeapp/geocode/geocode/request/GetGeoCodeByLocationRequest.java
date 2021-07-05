package tech.geocodeapp.geocode.geocode.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * GetGeoCodeByLocationRequest used to specify the attributes needed to find a specific GeoCode
 * by its longitude and latitude
 */
@Validated
public class GetGeoCodeByLocationRequest {

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
     * Default constructor
     */
    public GetGeoCodeByLocationRequest() {

    }

    /**
     * Overloaded Constructor
     *
     * @param longitude The longitude of the location of the GeoCode in the real world
     * @param latitude The latitude of the location of the GeoCode in the real world
     */
    public GetGeoCodeByLocationRequest( String longitude, String latitude ) {

        this.longitude = longitude;
        this.latitude = latitude;
    }

    /**
     * Sets the longitude attribute to the specified value
     *
     * @param longitude the value the attribute should be set to
     *
     * @return the request after the longitude has been changed
     */
    public GetGeoCodeByLocationRequest longitude( String longitude ) {

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
    public GetGeoCodeByLocationRequest latitude( String latitude ) {

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

        var getGeoCodeByLocationRequest = ( GetGeoCodeByLocationRequest ) obj;
        return Objects.equals( this.longitude, getGeoCodeByLocationRequest.longitude ) &&
                Objects.equals( this.latitude, getGeoCodeByLocationRequest.latitude );
    }

    /**
     * Creates a hash code from the attributes in the class
     *
     * @return the created has code
     */
    @Override
    public int hashCode() {

        return Objects.hash( longitude, latitude );
    }

    /**
     * Creates a string from all the attributes in the class
     *
     * @return the created string
     */
    @Override
    public String toString() {

        return "class GetGeoCodeByLocationRequest {\n" +
                "    longitude: " + toIndentedString( longitude ) + "\n" +
                "    latitude: " + toIndentedString( latitude ) + "\n" +
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
