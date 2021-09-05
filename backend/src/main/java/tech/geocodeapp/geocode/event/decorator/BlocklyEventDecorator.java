package tech.geocodeapp.geocode.event.decorator;

import tech.geocodeapp.geocode.event.model.UserEventStatus;

import java.util.*;

public class BlocklyEventDecorator extends EventDecorator{

    private String blocklyDetails;
    private List<String> inputs;
    private List<String> outputs;

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

    /**
     * Getter for the inputs variable
     */
    @Override
    public List<String> getInputs() {
        return inputs;
    }

    /**
     * Setter for the inputs variable
     * @param inputs the inputs used
     */
    @Override
    public void setInputs(List<String> inputs) {
        this.inputs = inputs;
    }

    /**
     * Getter for the outputs variable
     */
    @Override
    public List<String> getOutputs() {
        return outputs;
    }

    /**
     * Setter for the outputs variable
     * @param outputs the expected outputs of the event
     */
    @Override
    public void setOutputs(List<String> outputs) {
        this.outputs = outputs;
    }

    /**
     * Randomly selects what blockly blocks for an event to give a user and ensures they are not ones already given to them
     * @param stageNumber The index of the stage that was just completed
     * @param status The user's current status in an event
     */
    @Override
    public void handleStageCompletion(int stageNumber, UserEventStatus status) {

        //store number of stages and number of blocks
        int totalStages = this.getGeocodeIDs().size();
        var blocks = this.blocklyDetails.split("#");
        int totalBlocks = blocks.length;
        int numberOfBlocks = totalBlocks/totalStages;
        int remainderBlocks = totalBlocks % totalStages;
        if(remainderBlocks != 0 && stageNumber <= remainderBlocks) {
            numberOfBlocks++;
        }
        var count = 0;
        while(count < numberOfBlocks) {
            var random = new Random().nextInt(totalBlocks);
            List<String> foundBlocks = Arrays.asList(status.getDetails().get("blocks").split("#"));
            if(!foundBlocks.contains(blocks[random])) {
                String userBlocks = status.getDetails().get("blocks");
                userBlocks += "#" + blocks[random];
                Map<String, String> temp = status.getDetails();
                temp.replace("blocks", userBlocks);
                status.setDetails(temp);
                count++;
            }
        }
    }
}
