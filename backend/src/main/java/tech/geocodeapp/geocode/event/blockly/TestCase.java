package tech.geocodeapp.geocode.event.blockly;

import com.fasterxml.jackson.core.JsonProcessingException;

public class TestCase {
    /**
     * The inputs for the test case
     */
    private String[] inputs;

    /**
     * The output for the test case
     */
    private String output;

    /**
     * Overloaded Constructor
     * @param inputs The inputs for the test case
     * @param output The output for the test case
     */
    public TestCase(String[] inputs, String output) throws Exception {
        this.inputs = inputs;
        this.output = output;
    }

    public String[] getInputs() {
        return inputs;
    }

    public void setInputs(String[] inputs) {
        this.inputs = inputs;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
}
