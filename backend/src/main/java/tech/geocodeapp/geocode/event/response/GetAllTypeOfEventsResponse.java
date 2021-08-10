package tech.geocodeapp.geocode.event.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import tech.geocodeapp.geocode.event.model.Event;
import tech.geocodeapp.geocode.event.model.TimeTrial;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

/**
 * GetAllEventsResponse object returns all the Event objects in the system
 */
@Validated
public class GetAllTypeOfEventsResponse {

    /**
     * All the stored Events
     */
    @JsonProperty( "events" )
    private List< Event > events;

    /**
     * All the stored TimeTrials
     */
    @JsonProperty( "timeTrials" )
    private List< TimeTrial > timeTrials;

    /**
     * Default Constructor
     */
    public GetAllTypeOfEventsResponse() {

    }

    /**
     * Overloaded Constructor
     *
     * @param events the list of events to set the events attribute to
     * @param timeTrials the list of timeTrials to set the timeTrials attribute to
     */
    public GetAllTypeOfEventsResponse( List< Event > events, List< TimeTrial > timeTrials ) {

        this.events = events;
        this.timeTrials = timeTrials;
    }

    /**
     * Sets the events attribute to the specified value
     *
     * @param events the value the attribute should be set to
     *
     * @return the request after the events has been changed
     */
    public GetAllTypeOfEventsResponse events( List< Event > events ) {

        this.events = events;
        return this;
    }

    /**
     * Sets a single event inside the events attribute to the specified value
     *
     * @param eventsItem the value the attribute should be set to
     *
     * @return the stored events attribute
     */
    public GetAllTypeOfEventsResponse addEventsItem( Event eventsItem ) {

        this.events.add( eventsItem );
        return this;
    }

    /**
     * Gets the saved events attribute
     *
     * @return the stored events attribute
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
     * Sets the timeTrials attribute to the specified value
     *
     * @param timeTrials the value the attribute should be set to
     *
     * @return the request after the timeTrials has been changed
     */
    public GetAllTypeOfEventsResponse timeTrials( List< TimeTrial > timeTrials ) {

        this.timeTrials = timeTrials;
        return this;
    }

    /**
     * Sets a single TimeTrial inside the timeTrials attribute to the specified value
     *
     * @param eventsItem the value the attribute should be set to
     *
     * @return the stored timeTrials attribute
     */
    public GetAllTypeOfEventsResponse addTimeTrialItem( TimeTrial eventsItem ) {

        this.timeTrials.add( eventsItem );
        return this;
    }

    /**
     * Gets the saved timeTrials attribute
     *
     * @return the stored timeTrials attribute
     */
    @Valid
    public List< TimeTrial > getTimeTrials() {

        return timeTrials;
    }

    /**
     * Sets the timeTrials attribute to the specified value
     *
     * @param timeTrials the value the attribute should be set to
     */
    public void setTimeTrials( List< TimeTrial > timeTrials ) {

        this.timeTrials = timeTrials;
    }

    /**
     * Determines if the specified object is the same as the current object
     *
     * @param obj the object we want to compare with the specific attributes of this class
     *
     * @return if the object is the same or not
     */
    @Override
    public boolean equals( Object obj ) {

        if ( this == obj ) {

            return true;
        }
        if ( obj == null || getClass() != obj.getClass() ) {

            return false;
        }

        return Objects.equals( this.events, ( ( GetAllTypeOfEventsResponse ) obj ).events ) &&
               Objects.equals( this.timeTrials, ( ( GetAllTypeOfEventsResponse ) obj ).timeTrials );
    }

    /**
     * Creates a hash code from the attributes in the class
     *
     * @return the created has code
     */
    @Override
    public int hashCode() {

        return Objects.hash( events, timeTrials );
    }

    /**
     * Creates a string from all the attributes in the class
     *
     * @return the created string
     */
    @Override
    public String toString() {

        return "class GetAllEventsResponse {\n" +
                "    events: " + toIndentedString( events ) + "\n" +
                "    timeTrials: " + toIndentedString( timeTrials ) + "\n" +
                "}";
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString( Object o ) {

        if ( o == null ) {

            return "null";
        }
        return o.toString().replace( "\n", "\n    " );
    }

}
