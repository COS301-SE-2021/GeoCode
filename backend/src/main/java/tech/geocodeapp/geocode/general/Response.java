package tech.geocodeapp.geocode.general;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {
    @JsonProperty("success")
    private Boolean success = null;

    @JsonProperty("message")
    private String message = null;

    /**
     * Default constructor
     */
    public Response(){

    }

    /**
     * Set the success flag and message for the response
     */
    public Response(boolean success, String message){
        this.success = success;
        this.message = message;
    }

    public Boolean isSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
