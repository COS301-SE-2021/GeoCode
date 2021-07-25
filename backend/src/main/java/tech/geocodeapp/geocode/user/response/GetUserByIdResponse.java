package tech.geocodeapp.geocode.user.response;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import tech.geocodeapp.geocode.user.model.User;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * GetUserByIdResponse
 */
public class GetUserByIdResponse   {
    @JsonProperty("success")
    private Boolean success = null;

    @JsonProperty("message")
    private String message = null;

    @JsonProperty("user")
    @Valid
    private User user = null;

    public GetUserByIdResponse(boolean success, String message, User user){
        this.success = success;
        this.message = message;
        this.user = user;
    }

    public GetUserByIdResponse user(User user) {
        this.user = user;
        return this;
    }

    /**
     * Get user
     * @return user
     **/
    @Schema(required = true, description = "")
    @NotNull
    @Valid
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GetUserByIdResponse getUserByIdResponse = (GetUserByIdResponse) o;
        return Objects.equals(this.user, getUserByIdResponse.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GetUserByIdResponse {\n");

        sb.append("    user: ").append(toIndentedString(user)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
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
