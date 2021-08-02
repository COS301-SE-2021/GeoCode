package tech.geocodeapp.geocode.event.response;

import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.*;
import javax.validation.Valid;

import java.util.Objects;

/**
 * CreateLeaderboardResponse object is to determine if the specified Leaderboard was created
 * successfully or not
 */
@Validated
public class CreateLeaderboardResponse {

    @JsonProperty( "success" )
    @NotNull( message = "CreateLeaderboardResponse success attribute cannot be null." )
    private Boolean success;

    /**
     * Overloaded Constructor
     *
     * @param success the status of the Leaderboard created
     */
    public CreateLeaderboardResponse( @Valid Boolean success ) {

        this.success = success;
    }

    /**
     * Sets the success attribute to the specified value
     *
     * @param success the value the attribute should be set to
     *
     * @return the request after the success has been changed
     */
    public CreateLeaderboardResponse success( @Valid Boolean success ) {

        this.success = success;
        return this;
    }

    /**
     * Gets the saved success attribute
     *
     * @return the stored success attribute
     */
    @Valid
    public Boolean isSuccess() {

        return success;
    }

    /**
     * Sets the success attribute to the specified value
     *
     * @param success the value the attribute should be set to
     */
    public void setSuccess( @Valid Boolean success ) {

        this.success = success;
    }

    /**
     * Determines if the specified object is the same as the current object
     *
     * @param obj the object we want to compare with the specific attributes of this class
     *
     * @return if the object is the same or not
     */
    @Override
    public boolean equals( java.lang.Object obj ) {

        if ( this == obj ) {

            return true;
        }
        if ( obj == null || getClass() != obj.getClass() ) {

            return false;
        }

        return Objects.equals( this.success, ( ( CreateLeaderboardResponse ) obj ).success );
    }

    /**
     * Creates a hash code from the attributes in the class
     *
     * @return the created has code
     */
    @Override
    public int hashCode() {

        return Objects.hash( success );
    }

    /**
     * Creates a string from all the attributes in the class
     *
     * @return the created string
     */
    @Override
    public String toString() {

        return "class CreateLeaderboardResponse {\n" +
                "    success: " + toIndentedString( success ) + "\n" +
                "}";
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString( java.lang.Object o ) {

        if ( o == null ) {

            return "null";
        }
        return o.toString().replace( "\n", "\n    " );
    }

}