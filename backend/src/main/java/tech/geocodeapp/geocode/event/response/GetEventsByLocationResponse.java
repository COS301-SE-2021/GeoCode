package tech.geocodeapp.geocode.event.response;

import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.Valid;

import tech.geocodeapp.geocode.event.model.Event;

import java.util.Objects;
import java.util.List;

/**
 * GetEventsByLocationResponse object returns all the Events at the given location
 */
@Validated
public class GetEventsByLocationResponse {

    @JsonProperty( "events" )
    private List< Event > events;

    /**
     * Overloaded Constructor
     *
     * @param events the list of Events at the specified location
     */
    public GetEventsByLocationResponse( List< Event > events ) {

        this.events = events;
    }

    /**
     * Sets the events attribute to the specified value
     *
     * @param events the value the attribute should be set to
     *
     * @return the request after the events has been changed
     */
    public GetEventsByLocationResponse events( List< Event > events ) {

        this.events = events;
        return this;
    }

    /**
     * Sets a single event inside of the events attribute to the specified value
     *
     * @param eventsItem the value the attribute should be set to
     *
     * @return the stored events attribute
     */
    public GetEventsByLocationResponse addEventsItem( Event eventsItem ) {

        this.events.add( eventsItem );
        return this;
    }

    /**
     * Gets the saved events attribute
     *
     * @return the stored geocodes attribute
     */
    @Valid
    public List< Event > getEvents() {

        return events;
    }

    /**
     * Sets the events attribute to the specified value
     *
     * @param events the value the attribute should be set to
     */
    public void setEvents( List< Event > events ) {

        this.events = events;
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

        return Objects.equals( this.events, ( ( GetEventsByLocationResponse ) obj ).events );
    }

    /**
     * Creates a hash code from the attributes in the class
     *
     * @return the created has code
     */
    @Override
    public int hashCode() {

        return Objects.hash( events );
    }

    /**
     * Creates a string from all the attributes in the class
     *
     * @return the created string
     */
    @Override
    public String toString() {

        return "class GetEventsByLocationResponse {\n" +
                "    events: " + toIndentedString( events ) + "\n" +
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
