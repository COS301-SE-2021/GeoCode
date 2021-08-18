package tech.geocodeapp.geocode.event.model;

import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * ProgressLog object holds a user's current progress in an Event.
 */
@Entity
@Validated
public class UserEventStatus {

    /**
     * The unique identifier for the Event
     */
    @Id
    @JsonProperty( "id" )
    @NotNull( message = "UserEventStatus id cannot be null." )
    protected UUID id;

    /**
     * The unique id of the event to get the next stage from
     */
    @JsonProperty( "eventID" )
    @NotNull( message = "UserEventStatus eventID attribute cannot be null." )
    private UUID eventID;

    /**
     * The unique id of the user participating in the event
     */
    @JsonProperty( "userID" )
    @NotNull( message = "UserEventStatus userID attribute cannot be null." )
    private UUID userID;


    /**
     * The unique identifier of the geocode that the user is searching for
     */
    @JsonProperty( "geocodeID" )
    @NotNull( message = "UserEventStatus geocodeID attribute cannot be null." )
    private UUID geocodeID;

    /**
     * Miscellaneous recorded details for a user's participation in an event
     */
    @JsonProperty( "details" )
    @NotNull( message = "UserEventStatus details attribute cannot be null." )
    @ElementCollection
    private Map< String, String > details;

    /**
     * Default Constructor
     */
    public UserEventStatus() {

    }

    /**
     * Overloaded Constructor
     *
     * @param id      The unique identifier for the Event
     * @param eventID The unique id of the event to get the next stage from
     * @param userID  The unique id of the user competing in the event
     * @param levelID The unique identifier of a specific GeoCode
     * @param details Miscellaneous recorded details for a user's participation in an event
     */
    public UserEventStatus( UUID id, UUID eventID, UUID userID, UUID levelID, Map< String, String > details ) {

        this.id = id;
        this.eventID = eventID;
        this.userID = userID;
        this.geocodeID = levelID;
        this.details = details;
    }

    /**
     * Sets the id attribute to the specified value
     *
     * @param id the unique id to set the Event to
     *
     * @return the model after changing the id
     */
    @Valid
    public UserEventStatus id( UUID id ) {

        this.id = id;
        return this;
    }

    /**
     * Gets the saved id attribute
     *
     * @return the stored id attribute
     */
    public UUID getId() {

        return id;
    }

    /**
     * Sets the id attribute to the specified value
     *
     * @param id the value the id should be set to
     */
    public void setId( UUID id ) {

        this.id = id;
    }

    /**
     * Sets the eventID attribute to the specified value
     *
     * @param eventID the value the attribute should be set to
     *
     * @return the request after the eventID has been changed
     */
    public UserEventStatus eventID( UUID eventID ) {

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
    public UserEventStatus userID( UUID userID ) {

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
     * Sets the geocodeID attribute to the specified value
     *
     * @param geocodeID the value the attribute should be set to
     *
     * @return the request after the geocodeID has been changed
     */
    public UserEventStatus geocodeID( UUID geocodeID ) {

        this.geocodeID = geocodeID;
        return this;
    }

    /**
     * Gets the saved geocodeID attribute
     *
     * @return the stored geocodeID attribute
     */
    @Valid
    public UUID getGeocodeID() {

        return geocodeID;
    }

    /**
     * Sets the geocodeID attribute to the specified value
     *
     * @param geocodeID the value the attribute should be set to
     */
    public void setGeocodeID( UUID geocodeID ) {

        this.geocodeID = geocodeID;
    }

    /**
     * Sets the details attribute to the specified value
     *
     * @param details the value the attribute should be set to
     *
     * @return the request after the details has been changed
     */
    public UserEventStatus details( Map< String, String > details ) {

        this.details = details;
        return this;
    }

    /**
     * Gets the saved details attribute
     *
     * @return the stored details attribute
     */
    @Valid
    public Map< String, String > getDetails() {

        return details;
    }

    /**
     * Sets the details attribute to the specified value
     *
     * @param details the value the attribute should be set to
     */
    public void setDetails( Map< String, String > details ) {

        this.details = details;
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

        UserEventStatus timeLog = ( UserEventStatus ) obj;
        return Objects.equals( this.userID, timeLog.userID ) &&
                Objects.equals( this.geocodeID, timeLog.geocodeID ) &&
                Objects.equals( this.details, timeLog.details );
    }

    /**
     * Creates a hash code from the attributes in the class
     *
     * @return the created has code
     */
    @Override
    public int hashCode() {

        return Objects.hash( id, eventID, userID, geocodeID, details );
    }

    /**
     * Creates a string from all the attributes in the class
     *
     * @return the created string
     */
    @Override
    public String toString() {

        return "class UserEventStatus {\n" +
                "    id: " + toIndentedString( id ) + "\n" +
                "    eventID: " + toIndentedString( eventID ) + "\n" +
                "    userID: " + toIndentedString( userID ) + "\n" +
                "    geocodeID: " + toIndentedString( geocodeID ) + "\n" +
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
