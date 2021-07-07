package tech.geocodeapp.geocode.event.request;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * GetLeaderBoardByTimeTrialRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-07-07T10:35:03.795Z[GMT]")


public class GetLeaderBoardByTimeTrialRequest {

    @JsonProperty( "timeTrialID" )
    private UUID timeTrialID = null;

    public GetLeaderBoardByTimeTrialRequest timeTrialID( UUID timeTrialID ) {

        this.timeTrialID = timeTrialID;
        return this;
    }

    /**
     * Get timeTrialID
     *
     * @return timeTrialID
     **/
    @Schema( required = true, description = "" )
    @NotNull

    @Valid
    public UUID getTimeTrialID() {

        return timeTrialID;
    }

    public void setTimeTrialID( UUID timeTrialID ) {

        this.timeTrialID = timeTrialID;
    }


    @Override
    public boolean equals( java.lang.Object o ) {

        if ( this == o ) {

            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {

            return false;
        }
        GetLeaderBoardByTimeTrialRequest getLeaderBoardByTimeTrialRequest = ( GetLeaderBoardByTimeTrialRequest ) o;
        return Objects.equals( this.timeTrialID, getLeaderBoardByTimeTrialRequest.timeTrialID );
    }

    @Override
    public int hashCode() {

        return Objects.hash( timeTrialID );
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append( "class GetLeaderBoardByTimeTrialRequest {\n" );

        sb.append( "    timeTrialID: " ).append( toIndentedString( timeTrialID ) ).append( "\n" );
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
