package tech.geocodeapp.geocode.event.response;

import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.Valid;

import tech.geocodeapp.geocode.event.model.Event;
import tech.geocodeapp.geocode.general.response.Response;

/**
 * GetEventGeoCodeResponse object to hold the found Event
 */
@Validated
public class GetEventResponse extends Response {
    /**
     * The found Event
     */
    @JsonProperty( "foundEvent" )
    private Event foundEvent = null;

    /**
     *  Overloaded Constructor
     *
     * @param foundEvent The found Event
     */
    public GetEventResponse( boolean success, String message, Event foundEvent ) {
        super(success, message);
        this.foundEvent = foundEvent;
    }

    /**
     * Sets the foundEvent attribute to the specified value
     *
     * @param foundEvent the value the attribute should be set to
     *
     * @return the request after the foundEvent has been changed
     */
    public GetEventResponse foundEvent( Event foundEvent ) {

        this.foundEvent = foundEvent;
        return this;
    }

    /**
     * Gets the saved foundEvent attribute
     *
     * @return the stored foundEvent attribute
     */
    @Valid
    public Event getFoundEvent() {

        return foundEvent;
    }

    /**
     * Sets the foundEvent attribute to the specified value
     *
     * @param foundEvent the value the attribute should be set to
     */
    public void setFoundEvent( Event foundEvent ) {

        this.foundEvent = foundEvent;
    }
}