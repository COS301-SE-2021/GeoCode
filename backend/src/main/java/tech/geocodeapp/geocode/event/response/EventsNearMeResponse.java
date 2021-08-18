package tech.geocodeapp.geocode.event.response;

import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.Valid;
import javax.validation.constraints.*;

import tech.geocodeapp.geocode.event.model.Event;
import tech.geocodeapp.geocode.general.response.Response;

import java.util.List;
import java.util.Objects;

/**
 * EventsNearMeResponse object to hold the list of found Events
 */
@Validated
public class EventsNearMeResponse extends Response {

    /**
     * The list of Events found within the locations radius
     */
    @Valid
    @JsonProperty( "foundEvents" )
    @NotNull( message = "EventsNearMeResponse foundEvents attribute cannot be null." )
    private List< Event > foundEvents;

    /**
     *  Overloaded Constructor
     *
     * @param foundEvents The list of Events found within the locations radius
     */
    public EventsNearMeResponse( boolean success, String message, List< Event > foundEvents ) {
        super(success, message);
        this.foundEvents = foundEvents;
    }

    /**
     * Sets the foundEvents attribute to the specified value
     *
     * @param foundEvents the value the attribute should be set to
     *
     * @return the request after the foundEvents has been changed
     */
    public EventsNearMeResponse foundEvents( List< Event > foundEvents ) {

        this.foundEvents = foundEvents;
        return this;
    }

    /**
     * Add a single entry to the foundEvents
     *
     * @param foundEventsItem the entry to add to the foundEvents
     *
     * @return the changed response object
     */
    public EventsNearMeResponse addFoundEventsItem( Event foundEventsItem ) {

        this.foundEvents.add( foundEventsItem );
        return this;
    }

    /**
     * Gets the saved foundEvents attribute
     *
     * @return the stored foundEvents attribute
     */
    @Valid
    public List< Event > getFoundEvents() {

        return foundEvents;
    }

    /**
     * Sets the foundEvents attribute to the specified value
     *
     * @param foundEvents the value the attribute should be set to
     */
    public void setFoundEvents( List< Event > foundEvents ) {

        this.foundEvents = foundEvents;
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

        return Objects.equals( this.foundEvents, ( ( EventsNearMeResponse ) obj ).foundEvents );
    }

    /**
     * Creates a hash code from the attributes in the class
     *
     * @return the created has code
     */
    @Override
    public int hashCode() {

        return Objects.hash( foundEvents );
    }

    /**
     * Creates a string from all the attributes in the class
     *
     * @return the created string
     */
    @Override
    public String toString() {

        return "class EventsNearMeResponse {\n" +
                "    foundEvents: " + toIndentedString( foundEvents ) + "\n" +
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
