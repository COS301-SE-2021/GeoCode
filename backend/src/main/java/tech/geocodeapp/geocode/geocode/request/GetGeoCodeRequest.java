package tech.geocodeapp.geocode.geocode.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

import java.util.Objects;
import java.util.UUID;

/**
 * GetGeoCodeRequest
 */
@Validated
public class GetGeoCodeRequest {

    /**
     * The unique identifier of a specific GeoCode
     */
    @JsonProperty( "geoCodeID" )
    private UUID geoCodeID;

    /**
     * Default constructor
     */
    public GetGeoCodeRequest() {

    }

    /**
     * Overloaded Constructor
     *
     * @param geoCodeID The unique identifier of a specific GeoCode
     */
    public GetGeoCodeRequest( UUID geoCodeID ) {

        this.geoCodeID = geoCodeID;
    }

    /**
     * Sets the geoCodeID attribute to the specified value
     *
     * @param geoCodeID the value the attribute should be set to
     *
     * @return the request after the geoCodeID has been changed
     */
    public GetGeoCodeRequest geoCodeID( UUID geoCodeID ) {

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

        return Objects.equals( this.geoCodeID, ( ( GetGeoCodeRequest ) obj ).geoCodeID );
    }

    /**
     * Creates a hash code from the attributes in the class
     *
     * @return the created has code
     */
    @Override
    public int hashCode() {

        return Objects.hash( geoCodeID );
    }

    /**
     * Creates a string from all the attributes in the class
     *
     * @return the created string
     */
    @Override
    public String toString() {

        return "class GetGeoCodeRequest {\n" +
                "    geoCodeID: " + toIndentedString( geoCodeID ) + "\n" +
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
