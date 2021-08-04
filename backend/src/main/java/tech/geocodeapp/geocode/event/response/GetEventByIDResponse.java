package tech.geocodeapp.geocode.event.response;

import tech.geocodeapp.geocode.event.model.Event;
import tech.geocodeapp.geocode.general.response.Response;

public class GetEventByIDResponse extends Response {
    /**
     * The wanted Event object
     */
    private Event event;


    public GetEventByIDResponse(boolean success, String message, Event event) {
        super(success, message);
        this.event = event;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
