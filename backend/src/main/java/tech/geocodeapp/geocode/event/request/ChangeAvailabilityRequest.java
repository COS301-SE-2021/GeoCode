package tech.geocodeapp.geocode.event.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.*;
import javax.validation.Valid;

import java.util.Objects;
import java.util.UUID;

/**
 * ChangeAvailabilityRequest object to specify what Event to updated the availability
 */
@Validated
public class ChangeAvailabilityRequest {

    /**
     * The unique id of the event that needs to be updated
     */
    @JsonProperty( "eventID" )
    @NotNull( message = "ChangeAvailabilityRequest eventID attribute cannot be null." )
    private UUID eventID;

    /**
     * The availability the Event should be changed to
     */
    @JsonProperty( "availability" )
    @NotNull( message = "ChangeAvailabilityRequest availability attribute cannot be null." )
    private Boolean availability;

    /**
     * Overloaded Constructor
     *
     * @param eventID the id of the Event to updated
     * @param availability the availability to update to
     */
    public ChangeAvailabilityRequest( @Valid UUID eventID, @Valid Boolean availability ) {

        this.eventID = eventID;
        this.availability = availability;
    }

    /**
     * Sets the eventID attribute to the specified value
     *
     * @param eventID the value the attribute should be set to
     *
     * @return the request after the eventID has been changed
     */
    public ChangeAvailabilityRequest eventID( @Valid UUID eventID ) {

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
    public void setEventID( @Valid UUID eventID ) {

        this.eventID = eventID;
    }

    /**
     * Sets the availability attribute to the specified value
     *
     * @param availability the value the attribute should be set to
     *
     * @return the request after the availability has been changed
     */
    public ChangeAvailabilityRequest availability( @Valid Boolean availability ) {

        this.availability = availability;
        return this;
    }

    /**
     * Gets the saved availability attribute
     *
     * @return the stored availability attribute
     */
    @Valid
    public Boolean isAvailability() {

        return availability;
    }

    /**
     * Sets the availability attribute to the specified value
     *
     * @param availability the value the attribute should be set to
     */
    public void setAvailability( @Valid Boolean availability ) {

        this.availability = availability;
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
        ChangeAvailabilityRequest changeAvailabilityRequest = ( ChangeAvailabilityRequest ) obj;
        return Objects.equals( this.eventID, changeAvailabilityRequest.eventID ) &&
                Objects.equals( this.availability, changeAvailabilityRequest.availability );
    }

    /**
     * Creates a hash code from the attributes in the class
     *
     * @return the created has code
     */
    @Override
    public int hashCode() {

        return Objects.hash( eventID, availability );
    }

    /**
     * Creates a string from all the attributes in the class
     *
     * @return the created string
     */
    @Override
    public String toString() {

        return "class ChangeAvailabilityRequest {\n" +
                "    eventID: " + toIndentedString( eventID ) + "\n" +
                "    availability: " + toIndentedString( availability ) + "\n" +
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
