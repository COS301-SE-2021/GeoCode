package tech.geocodeapp.geocode.GeoCode.Request;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * GetHintsRequest used to specify the attributes needed to find all the hints
 * of the specified GeoCode
 */
@Validated
public class GetHintsRequest {

    /**
     * The unique ID of a GeoCode in the database
     */
    @JsonProperty( "geoCodeID" )
    private UUID geoCodeID = null;

    /**
     * Sets the geoCodeID attribute to the specified value
     *
     * @param geoCodeID the value the attribute should be set to
     *
     * @return the request after the geoCodeID has been changed
     */
    public GetHintsRequest geoCodeID( UUID geoCodeID ) {

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
        GetHintsRequest getHintsRequest = ( GetHintsRequest ) obj;
        return Objects.equals( this.geoCodeID, getHintsRequest.geoCodeID );
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

        StringBuilder sb = new StringBuilder();
        sb.append( "class GetHintsRequest {\n" );

        sb.append( "    geoCodeID: " ).append( toIndentedString( geoCodeID ) ).append( "\n" );
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
