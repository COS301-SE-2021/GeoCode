package tech.geocodeapp.geocode.geocode.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * UpdateAvailabilityResponse used to access the attributes received to create the response
 * to determine if changing the availability of the specified GeoCode was a success
 */
@Validated
public class UpdateAvailabilityResponse {

    /**
     * Determines if the update of the GeoCode with the specified attributes
     * in the request was a success or not
     */
    @JsonProperty( "isSuccess" )
    private Boolean isSuccess = null;

    /**
     * Default constructor
     */
    public UpdateAvailabilityResponse() {

    }

    /**
     * Overloaded Constructor
     *
     * @param isSuccess Determines if the update of the GeoCode with the specified attributes in the request was a success or not
     */
    public UpdateAvailabilityResponse( Boolean isSuccess ) {

        this.isSuccess = isSuccess;
    }

    /**
     * Sets the isSuccess attribute to the specified value
     *
     * @param isSuccess the value the attribute should be set to
     *
     * @return the response after the isSuccess has been changed
     */
    public UpdateAvailabilityResponse isSuccess( Boolean isSuccess ) {

        this.isSuccess = isSuccess;
        return this;
    }

    /**
     * Gets the saved isSuccess attribute
     *
     * @return the stored isSuccess attribute
     */
    public Boolean isIsSuccess() {

        return isSuccess;
    }

    /**
     * Sets the isSuccess attribute to the specified value
     *
     * @param isSuccess the value the attribute should be set to
     */
    public void setIsSuccess( Boolean isSuccess ) {

        this.isSuccess = isSuccess;
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

        return Objects.equals( this.isSuccess, ( ( UpdateAvailabilityResponse ) obj ).isSuccess );
    }

    /**
     * Creates a hash code from the attributes in the class
     *
     * @return the created has code
     */
    @Override
    public int hashCode() {

        return Objects.hash( isSuccess );
    }

    /**
     * Creates a string from all the attributes in the class
     *
     * @return the created string
     */
    @Override
    public String toString() {

        return "class UpdateAvailabilityResponse {\n" +
                "    isSuccess: " + toIndentedString( isSuccess ) + "\n" +
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
