package tech.geocodeapp.geocode.event.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import java.util.Objects;
import java.util.UUID;

/**
 * GetCurrentEventRequest object to specify what Event to retrieve
 */
@Validated
public class GetCurrentEventRequest {

    /**
     * The unique id of the user doing the event to get
     */
    @JsonProperty( "userID" )
    @NotNull( message = "GetCurrentEventRequest userID attribute cannot be null." )
    private UUID userID;

    /**
     * Default Constructor
     */
    public GetCurrentEventRequest() {

    }

    /**
     * Overloaded Constructor
     *
     * @param userID The unique id of the user doing the event to get
     */
    public GetCurrentEventRequest( UUID userID ) {

        this.userID = userID;
    }

    /**
     * Sets the userID attribute to the specified value
     *
     * @param userID the value the attribute should be set to
     *
     * @return the request after the userID has been changed
     */
    public GetCurrentEventRequest userID( UUID userID ) {

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

        GetCurrentEventRequest getCurrentEventRequest = ( GetCurrentEventRequest ) obj;
        return Objects.equals( this.userID, getCurrentEventRequest.userID );
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

        return "class GetCurrentEventRequest {\n" +
                "    userID: " + toIndentedString( userID ) + "\n" +
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
