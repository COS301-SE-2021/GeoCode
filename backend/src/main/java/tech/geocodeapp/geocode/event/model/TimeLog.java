package tech.geocodeapp.geocode.event.model;

import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.threeten.bp.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import java.util.Objects;
import java.util.UUID;

/**
 * TimeLog object holds when each User starts and Event. It is to keep Track of TimeTrials
 */
@Entity
@Validated
@Table( name = "timelog" )
public class TimeLog {

    /**
     * The unique identifier for the Event
     */
    @Id
    @JsonProperty( "id" )
    @NotNull( message = "TimeLog's id cannot be null." )
    protected UUID id;

    /**
     * The unique id of the event to get the next stage from
     */
    @JsonProperty( "eventID" )
    @NotNull( message = "TimeLog's eventID attribute cannot be null." )
    private UUID eventID;
    
    /**
     * The unique id of the user competing in the event
     */
    @JsonProperty( "userID" )
    @NotNull( message = "TimeLog's userID attribute cannot be null." )
    private UUID userID;

    /**
     * The unique identifier of a specific GeoCode
     */
    @JsonProperty( "geoCodeID" )
    @NotNull( message = "TimeLog's geoCodeID attribute cannot be null." )
    private UUID geoCodeID;

    /**
     * The time when a user started a Level of an Event
     */
    @JsonProperty( "startTime" )
    @NotNull( message = "TimeLog's startTime attribute cannot be null." )
    private OffsetDateTime startTime;

    /**
     * Default Constructor
     */
    public TimeLog() {

    }

    /**
     * Overloaded Constructor
     *
     * @param id The unique identifier for the Event
     * @param eventID The unique id of the event to get the next stage from
     * @param userID The unique id of the user competing in the event
     * @param geoCodeID The unique identifier of a specific GeoCode
     * @param startTime The time when a user started a Level of an Event
     */
    public TimeLog( UUID id, UUID eventID, UUID userID, UUID geoCodeID, OffsetDateTime startTime ) {

        this.id = id;
        this.eventID = eventID;
        this.userID = userID;
        this.geoCodeID = geoCodeID;
        this.startTime = startTime;
    }

    /**
     * Sets the id attribute to the specified value
     *
     * @param id the unique id to set the Event to
     *
     * @return the model after changing the id
     */
    @Valid
    public TimeLog id( UUID id ) {

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
    public TimeLog eventID( UUID eventID ) {

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
    public TimeLog userID( UUID userID ) {

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
     * Sets the geoCodeID attribute to the specified value
     *
     * @param geoCodeID the value the attribute should be set to
     *
     * @return the request after the geoCodeID has been changed
     */
    public TimeLog geoCodeID( UUID geoCodeID ) {

        this.geoCodeID = geoCodeID;
        return this;
    }

    /**
     * Gets the saved geoCodeID attribute
     *
     * @return the stored geoCodeID attribute
     */
    @Valid
    public UUID getGeoCodeID() {

        return geoCodeID;
    }

    /**
     * Sets the geoCodeID attribute to the specified value
     *
     * @param geoCodeID the value the attribute should be set to
     */
    public void setGeoCodeID( UUID geoCodeID ) {

        this.geoCodeID = geoCodeID;
    }

    /**
     * Sets the startTime attribute to the specified value
     *
     * @param startTime the value the attribute should be set to
     *
     * @return the request after the startTime has been changed
     */
    public TimeLog startTime( OffsetDateTime startTime ) {

        this.startTime = startTime;
        return this;
    }

    /**
     * Gets the saved startTime attribute
     *
     * @return the stored startTime attribute
     */
    @Valid
    public OffsetDateTime getStartTime() {

        return startTime;
    }

    /**
     * Sets the startTime attribute to the specified value
     *
     * @param startTime the value the attribute should be set to
     */
    public void setStartTime( OffsetDateTime startTime ) {

        this.startTime = startTime;
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

        TimeLog timeLog = ( TimeLog ) obj;
        return Objects.equals( this.userID, timeLog.userID ) &&
                Objects.equals( this.geoCodeID, timeLog.geoCodeID ) &&
                Objects.equals( this.startTime, timeLog.startTime );
    }

    /**
     * Creates a hash code from the attributes in the class
     *
     * @return the created has code
     */
    @Override
    public int hashCode() {

        return Objects.hash( userID, geoCodeID, startTime );
    }

    /**
     * Creates a string from all the attributes in the class
     *
     * @return the created string
     */
    @Override
    public String toString() {

        return "class TimeLog {\n" +
                "    userID: " + toIndentedString( userID ) + "\n" +
                "    geoCodeID: " + toIndentedString( geoCodeID ) + "\n" +
                "    startTime: " + toIndentedString( startTime ) + "\n" +
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
