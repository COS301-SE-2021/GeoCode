package tech.geocodeapp.geocode.event.blockly;

public class Block {
    /**
     * The type of Blockly block
     */
    private String type;

    /**
     * The max number of instances of this type of block
     */
    private int maxInstances;

    /**
     * Default Constructor - needed for objectMapper.readValue()
     */
    public Block(){

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
