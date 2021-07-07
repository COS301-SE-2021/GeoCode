package tech.geocodeapp.geocode.event.request;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * GetPointsByLeaderBoardRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-07-07T10:35:03.795Z[GMT]")


public class GetPointsByLeaderBoardRequest {

    @JsonProperty( "leaderboardID" )
    private UUID leaderboardID = null;

    public GetPointsByLeaderBoardRequest leaderboardID( UUID leaderboardID ) {

        this.leaderboardID = leaderboardID;
        return this;
    }

    /**
     * Get leaderboardID
     *
     * @return leaderboardID
     **/
    @Schema( required = true, description = "" )
    @NotNull

    @Valid
    public UUID getLeaderboardID() {

        return leaderboardID;
    }

    public void setLeaderboardID( UUID leaderboardID ) {

        this.leaderboardID = leaderboardID;
    }


    @Override
    public boolean equals( java.lang.Object o ) {

        if ( this == o ) {

            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {

            return false;
        }
        GetPointsByLeaderBoardRequest getPointsByLeaderBoardRequest = ( GetPointsByLeaderBoardRequest ) o;
        return Objects.equals( this.leaderboardID, getPointsByLeaderBoardRequest.leaderboardID );
    }

    @Override
    public int hashCode() {

        return Objects.hash( leaderboardID );
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append( "class GetPointsByLeaderBoardRequest {\n" );

        sb.append( "    leaderboardID: " ).append( toIndentedString( leaderboardID ) ).append( "\n" );
        sb.append( "}" );
        return sb.toString();
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
