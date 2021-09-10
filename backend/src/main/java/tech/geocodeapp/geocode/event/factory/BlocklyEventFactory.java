package tech.geocodeapp.geocode.event.factory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import tech.geocodeapp.geocode.event.blockly.Block;
import tech.geocodeapp.geocode.event.blockly.TestCase;
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
        final ObjectMapper objectMapper = new ObjectMapper();
        var properties = event.getProperties();

        try {
            BlocklyEventDecorator toReturn = new BlocklyEventDecorator(eventComponent);

            toReturn.setBlocks(objectMapper.readValue(properties.get("blocks"), Block[].class));
            toReturn.setBlocklyEvent(true);

            var testCases = objectMapper.readValue(properties.get("testCases"), TestCase[].class);
            var numTestCases = testCases.length;

            /* extract the inputs and outputs */
            var inputs = new String[numTestCases][];
            var outputs = new String[numTestCases];

            for(int i = 0; i < testCases.length; ++i){
                inputs[i] = testCases[i].getInputs();
                outputs[i] = testCases[i].getOutput();
            }

            toReturn.setInputs(inputs);
            toReturn.setOutputs(outputs);

            return toReturn;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
