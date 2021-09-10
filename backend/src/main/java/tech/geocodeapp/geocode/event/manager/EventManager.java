package tech.geocodeapp.geocode.event.manager;

import tech.geocodeapp.geocode.event.decorator.EventComponent;
import tech.geocodeapp.geocode.event.factory.AbstractEventFactory;
import tech.geocodeapp.geocode.event.factory.BasicEventFactory;
import tech.geocodeapp.geocode.event.factory.BlocklyEventFactory;
import tech.geocodeapp.geocode.event.factory.TimeTrialEventFactory;
import tech.geocodeapp.geocode.event.model.Event;

import java.util.HashMap;

public class EventManager {

    /**
     * Converts an {@link Event} to a decorated {@link EventComponent}
     * @param event The {@link Event} to convert
     * @return The {@link EventComponent} that has been created
     */
    public EventComponent buildEvent(Event event){
        AbstractEventFactory factory;
        EventComponent builtEvent;

        //use property String to build a ConcreteEvent using the BasicEventFactory
        factory = new BasicEventFactory();
        builtEvent = factory.decorateEvent(event, null);

        //check if no additional properties exist
        if(event.getProperties() == null || event.getProperties().isEmpty()) {
            return builtEvent;
        }

        //check if timeLimit property exists and decorate if it does
        if(event.getProperties().containsKey("timeLimit")) {
            factory = new TimeTrialEventFactory();
            builtEvent = factory.decorateEvent(event, builtEvent);
        }

        if(event.getProperties().containsKey("blocks")) {
            factory = new BlocklyEventFactory();
            builtEvent = factory.decorateEvent(event, builtEvent);
        }

        return builtEvent;
    }

    /**
     * Converts an {@link EventComponent} to an {@link Event} for use in other backend systems
     * @param eventComponent The {@link EventComponent} to convert
     * @return the converted {@link Event}
     */
    public Event convertToEvent(EventComponent eventComponent){
        Event converted = new Event();
        converted.setId(eventComponent.getID());
        converted.setName(eventComponent.getName());
        converted.setDescription(eventComponent.getDescription());
        converted.setLocation(eventComponent.getLocation());
        converted.setGeocodeIDs(eventComponent.getGeocodeIDs());
        converted.setBeginDate(eventComponent.getBeginDate());
        converted.setEndDate(eventComponent.getEndDate());
        converted.setLeaderboards(eventComponent.getLeaderboards());
        converted.setAvailable(eventComponent.isAvailable());

        //check what properties apply and add them to a hashmap
        HashMap<String, String> properties = new HashMap<>();

        if(eventComponent.getTimeLimit() != null){
            properties.put("timeLimit", eventComponent.getTimeLimit().toString());
        }

        if(eventComponent.isBlocklyEvent()){
            //TODO: see if this is needed
        }

        converted.setProperties(properties);

        return converted;
    }
}
