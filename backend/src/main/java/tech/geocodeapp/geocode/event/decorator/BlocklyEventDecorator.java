package tech.geocodeapp.geocode.event.decorator;

public class BlocklyEventDecorator extends EventDecorator{

    private String blocklyDetails;

    /**
     * @param decoratedType the EventComponent to decorate
     */
    public BlocklyEventDecorator(EventComponent decoratedType) {
        super(decoratedType);
    }

    /**
     * Getter for the blocklyDetails variable
     */
    @Override
    public String getBlocklyDetails() {
        return blocklyDetails;
    }

    /**
     * Setter for the blocklyDetails variable
     * @param blocklyDetails the blockly details
     */
    @Override
    public void setBlocklyDetails(String blocklyDetails) {
        this.blocklyDetails = blocklyDetails;
    }
}
