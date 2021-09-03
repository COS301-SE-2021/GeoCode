package tech.geocodeapp.geocode.event.decorator;

public class BlocklyEventDecorator extends EventDecorator{

    private String blocklyDetails;

    /**
     * @param decoratedType the EventComponent to decorate
     */
    public BlocklyEventDecorator(EventComponent decoratedType) {
        super(decoratedType);
    }
}
