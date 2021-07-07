package tech.geocodeapp.geocode.event.request;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.*;

/**
 * CreateLeaderboardRequest
 */
@Validated
public class CreateLeaderboardRequest {

    @JsonProperty( "name" )
    private String name = null;

    public CreateLeaderboardRequest name( String name ) {

        this.name = name;
        return this;
    }

    /**
     * Get name
     *
     * @return name
     **/
    @Schema( required = true, description = "" )
    @NotNull

    public String getName() {

        return name;
    }

    public void setName( String name ) {

        this.name = name;
    }


    @Override
    public boolean equals( java.lang.Object o ) {

        if ( this == o ) {

            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {

            return false;
        }
        CreateLeaderboardRequest createLeaderboardRequest = ( CreateLeaderboardRequest ) o;
        return Objects.equals( this.name, createLeaderboardRequest.name );
    }

    @Override
    public int hashCode() {

        return Objects.hash( name );
    }

    @Override
    public String toString() {

        return "class CreateLeaderboardRequest {\n" +
                "    name: " + toIndentedString( name ) + "\n" +
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
