package tech.geocodeapp.geocode.event.decorator;

import tech.geocodeapp.geocode.event.blockly.Block;
import tech.geocodeapp.geocode.event.model.UserEventStatus;

import java.util.*;

public class BlocklyEventDecorator extends EventDecorator{

    private Block[] blocks;
    private String[][] inputs;
    private String[] outputs;
    private String problemDescription;

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
    public Block[] getBlocks() {
        return blocks;
    }

    /**
     * Setter for the blocklyDetails variable
     * @param blocks the blockly details
     */
    @Override
    public void setBlocks(Block[] blocks) {
        this.blocks = blocks;
    }

    /**
     * Getter for the inputs variable
     * @return The inputs for each test case
     */
    @Override
    public String[][] getInputs() {
        return inputs;
    }

    /**
     * Setter for the inputs variable
     * @param inputs the inputs used
     */
    @Override
    public void setInputs(String[][] inputs) {
        this.inputs = inputs;
    }

    /**
     * Getter for the outputs variable
     */
    @Override
    public String[] getOutputs() {
        return outputs;
    }

    /**
     * Setter for the outputs variable
     * @param outputs the expected outputs of the event
     */
    @Override
    public void setOutputs(String[] outputs) {
        this.outputs = outputs;
    }

    @Override
    public void setProblemDescription(String problemDescription){
        this.problemDescription = problemDescription;
    }

    @Override
    public String getProblemDescription(){
        return this.problemDescription;
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
        int totalBlocks = this.blocks.length;
        int numberOfBlocks = totalBlocks/totalStages;
        int remainderBlocks = totalBlocks % totalStages;
        if(remainderBlocks != 0 && stageNumber <= remainderBlocks) {
            numberOfBlocks++;
        }

        var count = 0;

        String blockString = status.getDetails().get("blocks");
        List<String> foundBlocks = Arrays.asList(blockString.split("#"));
        String userBlocks = blockString;

        //TODO: multiple instances of each type of block
        while(count < numberOfBlocks) {
            var random = new Random().nextInt(totalBlocks);
            var currentType = this.blocks[random].getType();

            if(!foundBlocks.contains(currentType)) {
                foundBlocks.add(currentType);
                userBlocks += "#" + this.blocks[random];
                count++;
            }
        }

        Map<String, String> temp = new HashMap<>();
        temp.put("blocks", userBlocks);

        status.setDetails(temp);
    }
}
