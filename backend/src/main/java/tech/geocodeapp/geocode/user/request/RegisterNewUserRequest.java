package tech.geocodeapp.geocode.user.request;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * RegisterNewUserRequest
 */
public class RegisterNewUserRequest   {
    @JsonProperty("userID")
    private UUID userID;

    @JsonProperty("username")
    private String username;

    public RegisterNewUserRequest(UUID userID, String username){
        this.userID = userID;
        this.username = username;
    }

    public RegisterNewUserRequest userID(UUID userID) {
        this.userID = userID;
        return this;
    }

    /**
     * Get userID
     * @return userID
     **/
    @Schema(required = true)
    @NotNull

    @Valid
    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RegisterNewUserRequest registerNewUserRequest = (RegisterNewUserRequest) o;
        return Objects.equals(this.userID, registerNewUserRequest.userID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID);
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
