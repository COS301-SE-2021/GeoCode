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

        //check if blocks can be evenly spread across stages
       if(totalBlocks % totalStages == 0) {
           var blocksPerStage = totalBlocks/totalStages;
           var count = 0;
           while(count < blocksPerStage) {
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
       }else{

       }
    }
}
