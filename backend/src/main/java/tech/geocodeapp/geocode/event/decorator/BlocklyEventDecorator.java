package tech.geocodeapp.geocode.event.decorator;

import tech.geocodeapp.geocode.event.model.UserEventStatus;

import java.util.*;

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
            List<String> foundBlocks = Arrays.asList(status.getDetails().get("blocklyDetails").split("#"));
            if(!foundBlocks.contains(blocks[random])) {
                String userBlocks = status.getDetails().get("blocklyDetails");
                userBlocks += "#" + blocks[random];
                Map<String, String> temp = status.getDetails();
                temp.replace("blocklyDetails", userBlocks);
                status.setDetails(temp);
                count++;
            }
        }
    }
}
