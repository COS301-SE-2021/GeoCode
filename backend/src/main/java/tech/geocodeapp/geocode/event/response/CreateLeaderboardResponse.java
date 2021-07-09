package tech.geocodeapp.geocode.event.response;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.*;

/**
 * CreateLeaderboardResponse
 */
@Validated
public class CreateLeaderboardResponse {

    @JsonProperty( "success" )
    private Boolean success = null;

    public CreateLeaderboardResponse success( Boolean success ) {

        this.success = success;
        return this;
    }

    /**
     * Get success
     *
     * @return success
     **/
    @Schema( required = true, description = "" )
    @NotNull

    public Boolean isSuccess() {

        return success;
    }

    public void setSuccess( Boolean success ) {

        this.success = success;
    }


    @Override
    public boolean equals( java.lang.Object o ) {

        if ( this == o ) {

            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {

            return false;
        }
        CreateLeaderboardResponse createLeaderboardResponse = ( CreateLeaderboardResponse ) o;
        return Objects.equals( this.success, createLeaderboardResponse.success );
    }

    @Override
    public int hashCode() {

        return Objects.hash( success );
    }

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