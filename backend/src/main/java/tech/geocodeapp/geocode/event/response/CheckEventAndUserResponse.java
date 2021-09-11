package tech.geocodeapp.geocode.event.response;

import tech.geocodeapp.geocode.event.decorator.EventComponent;
import tech.geocodeapp.geocode.event.model.UserEventStatus;
import tech.geocodeapp.geocode.general.response.Response;

public class CheckEventAndUserResponse extends Response {
    /**
     * The status for the user for the event, to be used by calling function
     */
    private UserEventStatus status;

    /**
     * The EventComponent that calling function may need
     */
    private EventComponent eventComponent;

    /**
     * Overloaded Constructor
     */
    public CheckEventAndUserResponse(boolean success, String message){
        super(success, message);
    }

    public UserEventStatus getStatus() {
        return status;
    }

    public void setStatus(UserEventStatus status) {
        this.status = status;
    }

    public void setEventComponent(EventComponent eventComponent) {
        this.eventComponent = eventComponent;
    }

    public EventComponent getEventComponent() {
        return eventComponent;
    }
}
