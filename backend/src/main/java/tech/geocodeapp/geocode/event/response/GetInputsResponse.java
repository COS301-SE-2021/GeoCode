package tech.geocodeapp.geocode.event.response;

import tech.geocodeapp.geocode.general.response.Response;

public class GetInputsResponse extends Response {
    /**
     * The input cases for the Blockly Event
     */
    private String[][] inputs;

    /**
     * Overloaded Constructor
     */
    public GetInputsResponse(boolean success, String message){
        super(success, message);
    }

    /**
     * Overloaded Constructor
     */
    public GetInputsResponse(boolean success, String message, String[][] inputs){
        super(success, message);

        this.inputs = inputs;
    }

    public String[][] getInputs() {
        return inputs;
    }

    public void setInputs(String[][] inputs) {
        this.inputs = inputs;
    }
}
