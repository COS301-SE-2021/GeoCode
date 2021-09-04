package tech.geocodeapp.geocode.event.factory;

import tech.geocodeapp.geocode.event.decorator.EventComponent;
import tech.geocodeapp.geocode.event.model.Event;

public class BlocklyEventFactory extends AbstractEventFactory{
    @Override
    public EventComponent decorateEvent(Event event, EventComponent eventComponent) {
        return null;
    }
}
