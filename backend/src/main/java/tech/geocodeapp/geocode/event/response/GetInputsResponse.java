package tech.geocodeapp.geocode.event.response;

import tech.geocodeapp.geocode.general.response.Response;

import java.util.List;

public class GetInputsResponse extends Response {
    /**
     * The input cases for the Blockly Event
     */
    private List<String> inputs;

    /**
     * Overloaded Constructor
     */
    public GetInputsResponse(boolean success, String message){
        super(success, message);
    }

    /**
     * Overloaded Constructor
     */
    public GetInputsResponse(boolean success, String message, List<String> inputs){
        super(success, message);

        this.inputs = inputs;
    }

    public List<String> getInputs() {
        return inputs;
    }

    public void setInputs(List<String> inputs) {
        this.inputs = inputs;
    }
}
