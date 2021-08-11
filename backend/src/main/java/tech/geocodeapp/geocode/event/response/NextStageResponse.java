package tech.geocodeapp.geocode.event.response;

import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonProperty;

import tech.geocodeapp.geocode.event.model.UserEventStatus;

import java.util.Objects;

/**
 * NextStageResponse object to return the new status of the user in an event after changing stages
 */
@Validated
public class NextStageResponse {

    /**
     * The status of the User in this Event so far. Includes the id of the next GeoCode they are searching for
     */
    @JsonProperty( "status" )
    private UserEventStatus status;

    /**
     * Default Constructor
     */
    public NextStageResponse() {

    }

    /**
     * Overloaded Constructor
     *
     * @param status The progress of the User in this Event so far
     */
    public NextStageResponse( UserEventStatus status ) {

        this.status = status;
    }

    /**
     * Sets the status attribute to the specified value
     *
     * @param status the value the attribute should be set to
     *
     * @return the request after the status has been changed
     */
    public NextStageResponse status( UserEventStatus status ) {

        this.status = status;
        return this;
    }

    /**
     * Gets the saved status attribute
     *
     * @return the stored status attribute
     */
    public UserEventStatus getStatus() {

        return status;
    }

    /**
     * Sets the progress attribute to the specified value
     *
     * @param status the value the attribute should be set to
     */
    public void setStatus(UserEventStatus status) {

        this.status = status;
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

        return Objects.equals( this.status, ( ( NextStageResponse ) obj ).status);
    }

    /**
     * Creates a hash code from the attributes in the class
     *
     * @return the created has code
     */
    @Override
    public int hashCode() {

        return Objects.hash(status);
    }

    /**
     * Creates a string from all the attributes in the class
     *
     * @return the created string
     */
    @Override
    public String toString() {

        return "class NextStageResponse {\n" +
                "    status: " + toIndentedString(status) + "\n" +
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
