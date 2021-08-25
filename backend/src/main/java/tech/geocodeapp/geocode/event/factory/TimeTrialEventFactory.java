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
        try {
            String timeLimitString = event.getProperties().get("timeLimit");
            TimeTrialEventDecorator toReturn = new TimeTrialEventDecorator(eventComponent);
            Integer timeLimit = Integer.parseInt(timeLimitString);

            if (timeLimit > 0) {
                toReturn.setTimeLimit(timeLimit);
                return toReturn;
            }

        } catch (NumberFormatException e) {}

        /* Do not decorate if timeLimit is invalid */
        return eventComponent;
    }
}
