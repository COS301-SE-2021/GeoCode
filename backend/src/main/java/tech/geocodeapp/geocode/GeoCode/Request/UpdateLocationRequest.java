package tech.geocodeapp.geocode.GeoCode.Request;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * UpdateLocationRequest used to specify the attributes needed to
 */
@Validated
public class UpdateLocationRequest {

    /**
     * The unique identifier to update the users location
     */
    @JsonProperty( "userID" )
    private UUID userID = null;

    /**
     * Sets the userID attribute to the specified value
     *
     * @param userID the value the attribute should be set to
     *
     * @return the request after the userID has been changed
     */
    public UpdateLocationRequest userID( UUID userID ) {

        this.userID = userID;
        return this;
    }

    /**
     * Gets the saved userID attribute
     *
     * @return the stored userID attribute
     */
    @Valid
    public UUID getUserID() {

        return userID;
    }

    /**
     * Sets the userID attribute to the specified value
     *
     * @param userID the value the attribute should be set to
     */
    public void setUserID( UUID userID ) {

        this.userID = userID;
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
        UpdateLocationRequest updateLocationRequest = ( UpdateLocationRequest ) obj;
        return Objects.equals( this.userID, updateLocationRequest.userID );
    }

    /**
     * Creates a hash code from the attributes in the class
     *
     * @return the created has code
     */
    @Override
    public int hashCode() {

        return Objects.hash( userID );
    }

    /**
     * Creates a string from all the attributes in the class
     *
     * @return the created string
     */
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append( "class UpdateLocationRequest {\n" );

        sb.append( "    userID: " ).append( toIndentedString( userID ) ).append( "\n" );
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
