package tech.geocodeapp.geocode.geocode.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CreateGeoCodeResponse used to access the attributes received to create the response
 * of if a GeoCode was created
 */
@Validated
public class UpdateGeoCodeResponse {

    /**
     * Determines if the creation of a GeoCode with the specified attributes
     * in the request was a success or not
     */
    @JsonProperty( "success" )
    private Boolean success;

    /**
     * A message about the success of updating
     */
    @JsonProperty( "message" )
    private String message;

    /**
     * Default constructor
     */
    public UpdateGeoCodeResponse() {

    }

    /**
     * Overloaded Constructor
     *
     * @param success Determines if the creation of a GeoCode with the specified attributes in the request was a success or not
     */
    public UpdateGeoCodeResponse( Boolean success ) {

        this.success = success;
    }

    /**
     * Overloaded Constructor
     *
     * @param success Determines if the creation of a GeoCode with the specified attributes in the request was a success or not
     * @param message A message about the success of updating
     */
    public UpdateGeoCodeResponse( Boolean success, String message ) {

        this.success = success;
        this.message = message;
    }

    /**
     * Sets the success attribute to the specified value
     *
     * @param success the value the attribute should be set to
     *
     * @return the response after the success has been changed
     */
    public UpdateGeoCodeResponse success( Boolean success ) {

        this.success = success;
        return this;
    }

    /**
     * Gets the saved success attribute
     *
     * @return the stored success attribute
     */
    public Boolean isSuccess() {

        return success;
    }

    /**
     * Sets the success attribute to the specified value
     *
     * @param message the value the attribute should be set to
     */
    public void setMessage( String message ) {

        this.message = message;
    }

    /**
     * Sets the message attribute to the specified value
     *
     * @param message the value the attribute should be set to
     *
     * @return the response after the message has been changed
     */
    public UpdateGeoCodeResponse message( String message ) {

        this.message = message;
        return this;
    }

    /**
     * Sets the message attribute to the specified value
     *
     * @param message the value the attribute should be set to
     */
    public void setSuccess( String message ) {

        this.message = message;
    }

    /**
     * Determines if the specified object is the same as the current object
     *
     * @param obj the object we want to compare with the specific attributes of this class
     *
     * @return if the object is the same or not
     */
    @Override
    public boolean equals( Object obj ) {

        if ( this == obj ) {

            return true;
        }
        if ( obj == null || getClass() != obj.getClass() ) {

            return false;
        }

        var updateGeoCodeRequest = ( UpdateGeoCodeResponse ) obj;
        return  Objects.equals( this.success, updateGeoCodeRequest.success ) &&
                Objects.equals( this.message, updateGeoCodeRequest.message );
    }

    /**
     * Creates a hash code from the attributes in the class
     *
     * @return the created has code
     */
    @Override
    public int hashCode() {

        return Objects.hash( success, message );
    }

    /**
     * Creates a string from all the attributes in the class
     *
     * @return the created string
     */
    @Override
    public String toString() {

        return "class CreateGeoCodeResponse {\n" +
                "    success: " + toIndentedString( success ) + "\n" +
                "    message: " + toIndentedString( message ) + "\n" +
                "}";
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString( Object o ) {

        if ( o == null ) {

            return "null";
        }

        return o.toString().replace( "\n", "\n    " );
    }

}