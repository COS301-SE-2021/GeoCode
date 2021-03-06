package tech.geocodeapp.geocode.geocode.model;

import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.*;
import javax.persistence.Embeddable;

import java.util.Objects;

/**
 * GeoPoint model is used to locate an object, usually a GeoCode, in the real world
 */
@Validated
@Embeddable
public class GeoPoint {

    /**
     * The latitude co-ordinate of an object in the real world
     */
    @JsonProperty( "latitude" )
    @NotNull( message = "GeoPoint's longitude cannot be null." )
    private double latitude;

    /**
     * The longitude co-ordinate of an object in the real world
     */
    @JsonProperty( "longitude" )
    @NotNull( message = "GeoPoint's longitude cannot be null." )
    private double longitude;

    /**
     * Default constructor
     */
    public GeoPoint() {

    }

    /**
     * Overloaded Constructor
     *
     * @param latitude  latitude co-ordinate of an object in the real world
     * @param longitude longitude co-ordinate of an object in the real world
     */
    public GeoPoint( double latitude, double longitude ) {

        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Sets the latitude attribute to the specified value
     *
     * @param latitude the unique latitude to set the GeoPoint to
     *
     * @return the model after changing the latitude
     */
    public GeoPoint latitude( double latitude ) {

        this.latitude = latitude;
        return this;
    }

    /**
     * Gets the saved latitude attribute
     *
     * @return the stored latitude attribute
     */
    public double getLatitude() {

        return latitude;
    }

    /**
     * Sets the latitude attribute to the specified value
     *
     * @param latitude the value the latitude should be set to
     */
    public void setLatitude( double latitude ) {

        this.latitude = latitude;
    }

    /**
     * Sets the longitude attribute to the specified value
     *
     * @param longitude the unique longitude to set the GeoPoint to
     *
     * @return the model after changing the longitude
     */
    public GeoPoint longitude( double longitude ) {

        this.longitude = longitude;
        return this;
    }

    /**
     * Gets the saved longitude attribute
     *
     * @return the stored longitude attribute
     */
    public double getLongitude() {

        return longitude;
    }

    /**
     * Sets the longitude attribute to the specified value
     *
     * @param longitude the value the longitude should be set to
     */
    public void setLongitude( double longitude ) {

        this.longitude = longitude;
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
        GeoPoint geoPoint = ( GeoPoint ) obj;
        return Objects.equals( this.latitude, geoPoint.latitude ) &&
                Objects.equals( this.longitude, geoPoint.longitude );
    }

    /**
     * Creates a hash code from the attributes in the class
     *
     * @return the created has code
     */
    @Override
    public int hashCode() {

        return Objects.hash( latitude, longitude );
    }

    /**
     * Creates a string from all the attributes in the class
     *
     * @return the created string
     */
    @Override
    public String toString() {

        return "class GeoPoint {\n" +
                "    latitude: " + toIndentedString( latitude ) + "\n" +
                "    longitude: " + toIndentedString( longitude ) + "\n" +
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

    /**
     * Calculates the distance (in kilometres) between this GeoPoint and another.
     *
     * @param point the other GeoPoint in the measurement
     *
     * @return the distance (in kilometres) between this GeoPoint and the provided one.
     */
    public double distanceTo( GeoPoint point ) {

        /* The math module contains a function
         * named toRadians which converts from
         * degrees to radians. */
        double lat1 = Math.toRadians( this.getLatitude() );
        double lon1 = Math.toRadians( this.getLongitude() );
        double lat2 = Math.toRadians( point.getLatitude() );
        double lon2 = Math.toRadians( point.getLongitude() );

        /* Haversine formula */
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow( Math.sin( dlat / 2 ), 2 )
                + Math.cos( lat1 ) * Math.cos( lat2 )
                * Math.pow( Math.sin( dlon / 2 ), 2 );

        double c = 2 * Math.asin( Math.sqrt( a ) );

        /* Radius of earth in kilometres. Use 3956 for miles */
        double r = 6371;

        /* calculate the result */
        return ( c * r );
    }

}
