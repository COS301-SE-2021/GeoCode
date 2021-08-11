package tech.geocodeapp.geocode.event.factory;

import tech.geocodeapp.geocode.event.decorator.EventComponent;
import tech.geocodeapp.geocode.event.decorator.TimeTrialEventDecorator;
import tech.geocodeapp.geocode.event.model.Event;

public class TimeTrialEventFactory extends AbstractEventFactory {

    /**
     * Decorates a provided {@link EventComponent}
     * @param event The {@link Event} to be converted into a decorated object
     * @param eventComponent The {@link EventComponent} to decorate
     * @return The decorated {@link EventComponent}
     */
    @Override
    public EventComponent decorateEvent(Event event, EventComponent eventComponent) {
        TimeTrialEventDecorator toReturn = new TimeTrialEventDecorator(eventComponent);

        String timeLimitString = event.getProperties().get("timeLimit");
        Integer timeLimit = Integer.parseInt(timeLimitString);
        toReturn.setTimeLimit(timeLimit);

        return toReturn;
    }
}
