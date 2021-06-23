package tech.geocodeapp.geocode.GeoCode.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * UpdateLocationResponse used to access the attributes received to create the response
 * to determine if changing the location of the specified GeoCode was a success
 */
@Validated
public class UpdateLocationResponse {

    /**
     * Determines if the update of the GeoCode with the specified attributes
     * in the request was a success or not
     */
    @JsonProperty( "success" )
    private Boolean success = null;

    /**
     * Sets the isSuccess attribute to the specified value
     *
     * @param success the value the attribute should be set to
     *
     * @return the response after the isSuccess has been changed
     */
    public UpdateLocationResponse success( Boolean success ) {

        this.success = success;
        return this;
    }

    /**
     * Gets the saved isSuccess attribute
     *
     * @return the stored isSuccess attribute
     */
    public Boolean isSuccess() {

        return success;
    }

    /**
     * Sets the isSuccess attribute to the specified value
     *
     * @param success the value the attribute should be set to
     */
    public void setSuccess( Boolean success ) {

        this.success = success;
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

        return Objects.equals( this.success, ( ( UpdateLocationResponse ) obj ).success );
    }

    /**
     * Creates a hash code from the attributes in the class
     *
     * @return the created has code
     */
    @Override
    public int hashCode() {

        return Objects.hash( success );
    }

    /**
     * Creates a string from all the attributes in the class
     *
     * @return the created string
     */
    @Override
    public String toString() {

        return "class UpdateLocationResponse {\n" +
                "    success: " + toIndentedString( success ) + "}";
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
