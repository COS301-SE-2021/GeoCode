package tech.geocodeapp.geocode.event.factory;

import tech.geocodeapp.geocode.event.decorator.BlocklyEventDecorator;
import tech.geocodeapp.geocode.event.decorator.EventComponent;
import tech.geocodeapp.geocode.event.model.Event;

public class BlocklyEventFactory extends AbstractEventFactory{

    /**
     * d
     * @param event the value of the event to decorate the component with or the values to use in the {@link BasicEventFactory}
     * @param eventComponent The EventComponent to decorate or null if there is none in the case of a {@link BasicEventFactory}
     * @return the decorated {@link EventComponent}
     */
    @Override
    public EventComponent decorateEvent(Event event, EventComponent eventComponent) {
        BlocklyEventDecorator toReturn = new BlocklyEventDecorator(eventComponent);
        toReturn.setBlocklyDetails(event.getProperties().get("blocklyDetails"));
        toReturn.setBlocklyEvent(true);
        return toReturn;
    }
}
