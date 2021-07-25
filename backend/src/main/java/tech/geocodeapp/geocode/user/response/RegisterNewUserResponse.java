package tech.geocodeapp.geocode.user.response;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import tech.geocodeapp.geocode.user.model.User;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * RegiserNewUserResponse
 */
public class RegisterNewUserResponse   {
    @JsonProperty("success")
    private Boolean success = null;

    @JsonProperty("message")
    private String message = null;

    public RegisterNewUserResponse(boolean success, String message){
        this.success = success;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
