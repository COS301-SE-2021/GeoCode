package tech.geocodeapp.geocode.event.request;

import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.*;
import javax.validation.Valid;

import java.util.Objects;
import java.util.UUID;

/**
 * CreateLeaderboardRequest object to specify what Leaderboard to be created
 */
@Validated
public class CreateLeaderboardRequest {

    @JsonProperty( "name" )
    @NotEmpty( message = "CreateLeaderboardRequest name attribute cannot be empty." )
    private String name;

    /**
     * The unique id of the event to get
     */
    @JsonProperty( "eventID" )
    @NotNull( message = "CreateLeaderboardRequest eventID attribute cannot be null." )
    private UUID eventID;

    /**
     * Default Constructor
     */
    public CreateLeaderboardRequest() {

    }

    /**
     * Overloaded Constructor
     *
     * @param name the name of the Leaderboard to create
     */
    public CreateLeaderboardRequest( @Valid String name ) {

        this.name = name;
    }

    /**
     * Sets the name attribute to the specified value
     *
     * @param name the value the attribute should be set to
     *
     * @return the request after the name has been changed
     */
    public CreateLeaderboardRequest name( @Valid String name ) {

        this.name = name;
        return this;
    }

    /**
     * Gets the saved name attribute
     *
     * @return the stored name attribute
     */
    @Valid
    public String getName() {

        return name;
    }

    /**
     * Sets the name attribute to the specified value
     *
     * @param name the value the attribute should be set to
     */
    public void setName( @Valid String name ) {

        this.name = name;
    }

    /**
     * Sets the eventID attribute to the specified value
     *
     * @param eventID the value the attribute should be set to
     *
     * @return the request after the eventID has been changed
     */
    public CreateLeaderboardRequest eventID( UUID eventID ) {

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

        CreateLeaderboardRequest createLeaderboardRequest = ( CreateLeaderboardRequest ) obj;
        return  Objects.equals( this.name, createLeaderboardRequest.name ) &&
                Objects.equals( this.eventID, createLeaderboardRequest.eventID );


    }

    /**
     * Creates a hash code from the attributes in the class
     *
     * @return the created has code
     */
    @Override
    public int hashCode() {

        return Objects.hash( name, eventID );
    }

    /**
     * Creates a string from all the attributes in the class
     *
     * @return the created string
     */
    @Override
    public String toString() {

        return "class CreateLeaderboardRequest {\n" +
                "    name: " + toIndentedString( name ) + "\n" +
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
