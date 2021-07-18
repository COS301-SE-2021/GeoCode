package tech.geocodeapp.geocode.event.response;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import tech.geocodeapp.geocode.leaderboard.model.Leaderboard;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * GetLeaderBoardByTimeTrialResponse
 */
@Validated
public class GetLeaderBoardByTimeTrialResponse {

    @JsonProperty( "leaderboard" )
    @Valid
    private List< Leaderboard > leaderboard = new ArrayList<>();

    public GetLeaderBoardByTimeTrialResponse leaderboard( List< Leaderboard > leaderboard ) {

        this.leaderboard = leaderboard;
        return this;
    }

    public GetLeaderBoardByTimeTrialResponse addLeaderboardItem( Leaderboard leaderboardItem ) {

        this.leaderboard.add( leaderboardItem );
        return this;
    }

    /**
     * Get leaderboard
     *
     * @return leaderboard
     **/
    @Schema( required = true, description = "" )
    @NotNull
    @Valid
    public List< Leaderboard > getLeaderboard() {

        return leaderboard;
    }

    public void setLeaderboard( List< Leaderboard > leaderboard ) {

        this.leaderboard = leaderboard;
    }


    @Override
    public boolean equals( java.lang.Object o ) {

        if ( this == o ) {

            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {

            return false;
        }
        GetLeaderBoardByTimeTrialResponse getLeaderBoardByTimeTrialResponse = ( GetLeaderBoardByTimeTrialResponse ) o;
        return Objects.equals( this.leaderboard, getLeaderBoardByTimeTrialResponse.leaderboard );
    }

    @Override
    public int hashCode() {

        return Objects.hash( leaderboard );
    }

    @Override
    public String toString() {

        return "class GetLeaderBoardByTimeTrialResponse {\n" +
                "    leaderboard: " + toIndentedString( leaderboard ) + "\n" +
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
