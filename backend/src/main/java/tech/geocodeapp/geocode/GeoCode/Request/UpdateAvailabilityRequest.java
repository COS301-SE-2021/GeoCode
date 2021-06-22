package tech.geocodeapp.geocode.GeoCode.Request;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * UpdateAvailabilityRequest used to specify the attributes needed to change the availability
 * of a specific GeoCode
 */
@Validated
public class UpdateAvailabilityRequest {

    /**
     * The unique identifier of a specific GeoCode
     */
    @JsonProperty( "geoCodeID" )
    private UUID geoCodeID = null;

    /**
     * If the GeoCode is active in the system
     */
    @JsonProperty( "isAvailable" )
    private Boolean isAvailable = null;

    /**
     * Sets the geoCodeID attribute to the specified value
     *
     * @param geoCodeID the value the attribute should be set to
     *
     * @return the request after the geoCodeID has been changed
     */
    public UpdateAvailabilityRequest geoCodeID( UUID geoCodeID ) {

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
     * Sets the available attribute to the specified value
     *
     * @param isAvailable the value the attribute should be set to
     *
     * @return the request after the available has been changed
     */
    public UpdateAvailabilityRequest isAvailable( Boolean isAvailable ) {

        this.isAvailable = isAvailable;
        return this;
    }

    /**
     * Gets the saved available attribute
     *
     * @return the stored available attribute
     */
    public Boolean isIsAvailable() {

        return isAvailable;
    }

    /**
     * Sets the available attribute to the specified value
     *
     * @param isAvailable the value the attribute should be set to
     */
    public void setIsAvailable( Boolean isAvailable ) {

        this.isAvailable = isAvailable;
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
        UpdateAvailabilityRequest updateAvailabilityRequest = ( UpdateAvailabilityRequest ) obj;
        return Objects.equals( this.geoCodeID, updateAvailabilityRequest.geoCodeID ) &&
                Objects.equals( this.isAvailable, updateAvailabilityRequest.isAvailable );
    }

    /**
     * Creates a hash code from the attributes in the class
     *
     * @return the created has code
     */
    @Override
    public int hashCode() {

        return Objects.hash( geoCodeID, isAvailable );
    }

    /**
     * Creates a string from all the attributes in the class
     *
     * @return the created string
     */
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append( "class UpdateAvailabilityRequest {\n" );

        sb.append( "    geoCodeID: " ).append( toIndentedString( geoCodeID ) ).append( "\n" );
        sb.append( "    isAvailable: " ).append( toIndentedString( isAvailable ) ).append( "\n" );
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
