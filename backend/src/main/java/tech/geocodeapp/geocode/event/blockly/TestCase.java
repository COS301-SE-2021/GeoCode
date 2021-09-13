package tech.geocodeapp.geocode.event.blockly;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;

public class TestCase {
    /**
     * The inputs for the test case
     */

    @JsonProperty("inputs")
    private String[] inputs;

    /**
     * The output for the test case
     */
    @JsonProperty("output")
    private String output;

    public TestCase() {

    }

    /**
     * Overloaded Constructor
     * @param inputs The inputs for the test case
     * @param output The output for the test case
     */
    public TestCase(String[] inputs, String output) {
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
