package tech.geocodeapp.geocode.event.factory;

import tech.geocodeapp.geocode.collectable.model.Rarity;
import tech.geocodeapp.geocode.event.decorator.ConcreteEvent;
import tech.geocodeapp.geocode.event.decorator.EventComponent;
import tech.geocodeapp.geocode.event.model.Event;

import java.util.UUID;

public class BasicEventFactory extends AbstractEventFactory {

    /**
     * Creates the a new {@link ConcreteEvent} and sets its variables to the values obtained from parsing properties
     * @param event An Event to convert into a decorated object
     * @param eventComponent Null as it is created here and decorated by the other factories if required
     * @return the created {@link ConcreteEvent} returned as a {@link EventComponent} interface object
     */
    @Override
    public EventComponent decorateEvent(Event event, EventComponent eventComponent) {

        EventComponent toReturn = new ConcreteEvent();

        toReturn.setID(event.getId());
        toReturn.setName(event.getName());
        toReturn.setDescription(event.getDescription());
        toReturn.setLocation(event.getLocation());
        toReturn.setGeocodeIDs(event.getGeocodeIDs());
        toReturn.setBeginDate(event.getBeginDate());
        toReturn.setEndDate(event.getEndDate());
        toReturn.setLeaderboards(event.getLeaderboards());
        toReturn.setAvailable(event.isAvailable());
        toReturn.setBlocklyEvent(false);

        return toReturn;
    }
}
