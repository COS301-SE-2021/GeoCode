package tech.geocodeapp.geocode.GeoCode.Request;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;
import javax.validation.Valid;

/**
 * SwapCollectablesRequest used to specify the attributes needed to find a GeoCode
 * and swap one of its Collectables with a Collectable from a user
 */
@Validated
public class SwapCollectablesRequest {

    /**
     * The unique ID of a Collectable contained in the GeoCode
     */
    @JsonProperty( "targetGeoCodeID" )
    private UUID targetGeoCodeID = null;

    /**
     * The unique ID of a Collectable the user has
     */
    @JsonProperty( "targetCollectableID" )
    private UUID targetCollectableID = null;

    /**
     * Sets the targetGeoCodeID attribute to the specified value
     *
     * @param targetGeoCodeID the value the attribute should be set to
     *
     * @return the request after the targetGeoCodeID has been changed
     */
    public SwapCollectablesRequest targetGeoCodeID( UUID targetGeoCodeID ) {

        this.targetGeoCodeID = targetGeoCodeID;
        return this;
    }

    /**
     * Gets the saved targetGeoCodeID attribute
     *
     * @return the stored targetGeoCodeID attribute
     */
    @Valid
    public UUID getTargetGeoCodeID() {

        return targetGeoCodeID;
    }

    /**
     * Sets the targetGeoCodeID attribute to the specified value
     *
     * @param targetGeoCodeID the value the attribute should be set to
     */
    public void setTargetGeoCodeID( UUID targetGeoCodeID ) {

        this.targetGeoCodeID = targetGeoCodeID;
    }

    /**
     * Sets the targetCollectableID attribute to the specified value
     *
     * @param targetCollectableID the value the attribute should be set to
     *
     * @return the request after the targetCollectableID has been changed
     */
    public SwapCollectablesRequest targetCollectableID( UUID targetCollectableID ) {

        this.targetCollectableID = targetCollectableID;
        return this;
    }

    /**
     * Gets the saved targetCollectableID attribute
     *
     * @return the stored targetCollectableID attribute
     */
    @Valid
    public UUID getTargetCollectableID() {

        return targetCollectableID;
    }

    /**
     * Sets the targetCollectableID attribute to the specified value
     *
     * @param targetCollectableID the value the attribute should be set to
     */
    public void setTargetCollectableID( UUID targetCollectableID ) {

        this.targetCollectableID = targetCollectableID;
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
        SwapCollectablesRequest swapCollectablesRequest = ( SwapCollectablesRequest ) obj;
        return Objects.equals( this.targetGeoCodeID, swapCollectablesRequest.targetGeoCodeID ) &&
                Objects.equals( this.targetCollectableID, swapCollectablesRequest.targetCollectableID );
    }

    /**
     * Creates a hash code from the attributes in the class
     *
     * @return the created has code
     */
    @Override
    public int hashCode() {

        return Objects.hash( targetGeoCodeID, targetCollectableID );
    }

    /**
     * Creates a string from all the attributes in the class
     *
     * @return the created string
     */
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append( "class SwapCollectablesRequest {\n" );

        sb.append( "    targetGeoCodeID: " ).append( toIndentedString( targetGeoCodeID ) ).append( "\n" );
        sb.append( "    targetCollectableID: " ).append( toIndentedString( targetCollectableID ) ).append( "\n" );
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
