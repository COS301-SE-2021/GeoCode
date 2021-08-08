package tech.geocodeapp.geocode.event.request;

import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.*;
import javax.validation.Valid;

import java.util.Objects;
import java.util.UUID;

/**
 * IsTimeTrialRequest object to get the ID of an Event to determine its instantiation
 */
@Validated
public class IsTimeTrialRequest {

    /**
     * The unique id of the event to get
     */
    @JsonProperty( "eventID" )
    @NotNull( message = "IsTimeTrialRequest eventID attribute cannot be null." )
    private UUID eventID = null;

    /**
     * Default Constructor
     */
    public IsTimeTrialRequest() {

    }

    /**
     * Overloaded Constructor
     *
     * @param eventID The unique id of the event to get
     */
    public IsTimeTrialRequest( UUID eventID ) {

        this.eventID = eventID;
    }

    /**
     * Sets the eventID attribute to the specified value
     *
     * @param eventID the value the attribute should be set to
     *
     * @return the request after the eventID has been changed
     */
    public IsTimeTrialRequest eventID( UUID eventID ) {

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

        return Objects.equals( this.eventID, ( ( IsTimeTrialRequest ) obj ).eventID );
    }

    /**
     * Creates a hash code from the attributes in the class
     *
     * @return the created has code
     */
    @Override
    public int hashCode() {

        return Objects.hash( eventID );
    }

    /**
     * Creates a string from all the attributes in the class
     *
     * @return the created string
     */
    @Override
    public String toString() {

        return "class IsTimeTrialRequest {\n" +
                "    eventID: " + toIndentedString( eventID ) + "\n" +
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
