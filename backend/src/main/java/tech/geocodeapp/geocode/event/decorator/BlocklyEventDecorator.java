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
        /* Let other decorators manipulate the status */
        super.handleStageCompletion(stageNumber, status);

        /* store number of stages and number of blocks */
        int totalStages = this.getGeocodeIDs().size();
        int totalBlockTypes = this.blocks.length;

        /* start with an equal number of block types are given to the User at each stage */
        int numberOfBlockTypes = totalBlockTypes/totalStages;
        int remainderBlockTypes = totalBlockTypes % totalStages;

        /*
         * if the number of blocks does not perfectly divide into the number of stages then
         * the first remainderBlockTypes stages gets an extra block type
         *
         * eg:
         * if we have 10 block types and 4 stages (GeoCodes) in the Event then
         * the first 10 % 4 = 2 stages gets 10/4 + 1 = 2 + 1 = 3 block types
         * and the last 2 stages get 10/4 = 2 block types
         */
        if(remainderBlockTypes != 0 && stageNumber <= remainderBlockTypes) {
            numberOfBlockTypes++;
        }

        /* get which block types the User has found so far */
        String blockString = status.getDetails().get("blocks");
        List<String> foundBlockTypes = new ArrayList<>(Arrays.asList(blockString.split("#")));
        String userBlocks = blockString;

        /* allocate numberOfBlockTypes random block types to the User */
        var count = 0;

        while(count < numberOfBlockTypes) {
            /* get random type */
            var random = new Random().nextInt(totalBlockTypes);
            String currentType = this.blocks[random].getType();

            /* add if new block type */
            if(!foundBlockTypes.contains(currentType)) {
                foundBlockTypes.add(currentType);
                userBlocks += "#" + currentType;
                count++;
            }
        }

        /* remove first hashtag at the start */
        if(stageNumber == 1){
            userBlocks = userBlocks.substring(1);
        }

        /* update the hashmap */
        Map<String, String> temp = new HashMap<>();
        temp.put("blocks", userBlocks);

        status.setDetails(temp);
    }
}
