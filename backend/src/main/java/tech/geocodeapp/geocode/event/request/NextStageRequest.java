package tech.geocodeapp.geocode.event.request;

import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import java.util.Objects;
import java.util.UUID;

/**
 * NextStageRequest object gets the next GeoCode for a user to find
 */
@Validated
public class NextStageRequest {

    /**
     * The unique id of the event to get the next stage from
     */
    @JsonProperty( "eventID" )
    @NotNull( message = "NextStageRequest eventID attribute cannot be null." )
    private UUID eventID;

    /**
     * The unique id of the user competing in the event
     */
    @JsonProperty( "userID" )
    @NotNull( message = "NextStageRequest userID attribute cannot be null." )
    private UUID userID;

    /**
     * Default Constructor
     */
    public NextStageRequest() {

    }

    /**
     * Overloaded Constructor
     *
     * @param eventID The unique id of the event to get the next stage from
     * @param userID The unique id of the user competing in the event
     */
    public NextStageRequest( UUID eventID, UUID userID ) {

        this.eventID = eventID;
        this.userID = userID;
    }

    /**
     * Sets the eventID attribute to the specified value
     *
     * @param eventID the value the attribute should be set to
     *
     * @return the request after the eventID has been changed
     */
    public NextStageRequest eventID( UUID eventID ) {

        this.eventID = eventID;
        return this;
    }

    /**
     * Gets the saved eventID attribute
     *
     * @return the stored eventID attribute
     */
    @Valid
    public UUID getEventID() {

        return eventID;
    }

    /**
     * Sets the eventID attribute to the specified value
     *
     * @param eventID the value the attribute should be set to
     */
    public void setEventID( UUID eventID ) {

        this.eventID = eventID;
    }

    /**
     * Sets the userID attribute to the specified value
     *
     * @param userID the value the attribute should be set to
     *
     * @return the request after the userID has been changed
     */
    public NextStageRequest userID( UUID userID ) {

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

        NextStageRequest nextStageRequest = ( NextStageRequest ) obj;
        return Objects.equals( this.eventID, nextStageRequest.eventID ) &&
                Objects.equals( this.userID, nextStageRequest.userID );
    }

    /**
     * Creates a hash code from the attributes in the class
     *
     * @return the created has code
     */
    @Override
    public int hashCode() {

        return Objects.hash( eventID, userID );
    }

    /**
     * Creates a string from all the attributes in the class
     *
     * @return the created string
     */
    @Override
    public String toString() {

        return "class NextStageRequest {\n" +
                "    eventID: " + toIndentedString( eventID ) + "\n" +
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
