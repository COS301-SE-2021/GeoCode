package tech.geocodeapp.geocode.event.manager;

import tech.geocodeapp.geocode.event.blockly.Block;
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
     * @param event The {@link EventComponent} to convert
     * @return the converted {@link Event}
     */
    public Event convertToEvent(EventComponent event){
        Event converted = new Event();
        converted.setId(event.getID());
        converted.setName(event.getName());
        converted.setDescription(event.getDescription());
        converted.setLocation(event.getLocation());
        converted.setGeocodeIDs(event.getGeocodeIDs());
        converted.setBeginDate(event.getBeginDate());
        converted.setEndDate(event.getEndDate());
        converted.setLeaderboards(event.getLeaderboards());
        converted.setAvailable(event.isAvailable());

        //check what properties apply and add them to a hashmap
        HashMap<String, String> properties = new HashMap<>();

        if(event.getTimeLimit() != null){
            properties.put("timeLimit", event.getTimeLimit().toString());
        }

        if(event.isBlocklyEvent()){
            /* blocks */
            Block[] blocks = event.getBlocks();
            String blocksString = blocks[0].getBlockString();
            int numTestCases = blocks.length;

            for(int i = 1; i < numTestCases; ++i){
                blocksString += "#" + blocks[i].getBlockString();
            }

            properties.put("blocks", blocksString);

            /* inputs */
            var inputs = event.getInputs();
            String inputsString = "";

            for(int i = 0; i < numTestCases; ++i){
                for(int j = 0; j < inputs[i].length-1; ++i){
                    inputsString += inputs[i][j] + ",";
                }

                inputsString += inputs[i][inputs[i].length-1] + "#";
            }

            inputsString = inputsString.substring(0, inputsString.length()-1);

            properties.put("inputs", inputsString);

            /* outputs */
            var outputs = event.getOutputs();
            String outputsString = outputs[0];

            for (int i = 1; i < numTestCases; ++i) {
                outputsString += "#" + outputs[i];
            }

            properties.put("outputs", outputsString);
        }

        converted.setProperties(properties);

        return converted;
    }
}
