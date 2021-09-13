package tech.geocodeapp.geocode.event.blockly;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Block {
    /**
     * The type of Blockly block
     */
    @JsonProperty("type")
    private String type;

    /**
     * The max number of instances of this type of block
     */
    @JsonProperty("maxInstances")
    private int maxInstances;

    public Block() {

    }

    /**
     * Overloaded Constructor
     * @param type The type of Blockly block
     * @param maxInstances The max number of instances of this type of block
     */
    public Block(String type, int maxInstances) {
        this.type = type;
        this.maxInstances = maxInstances;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMaxInstances() {
        return maxInstances;
    }

    public void setMaxInstances(int maxInstances) {
        this.maxInstances = maxInstances;
    }

    public String getBlockString(){
        return this.type + ":" + this.maxInstances;
    }
}
