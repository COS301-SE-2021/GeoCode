package tech.geocodeapp.geocode.event.response;

import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.Valid;

import tech.geocodeapp.geocode.leaderboard.model.Leaderboard;

import java.util.Objects;
import java.util.List;

/**
 * GetLeaderBoardByTimeTrialResponse
 */
@Validated
public class GetLeaderBoardByTimeTrialResponse {

    @Valid
    @JsonProperty( "leaderboard" )
    private List< Leaderboard > leaderboard;

    public GetLeaderBoardByTimeTrialResponse leaderboard( List< Leaderboard > leaderboard ) {

        this.leaderboard = leaderboard;
        return this;
    }

    public GetLeaderBoardByTimeTrialResponse addLeaderboardItem( Leaderboard leaderboardItem ) {

        this.leaderboard.add( leaderboardItem );
        return this;
    }

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

        return Objects.equals( this.leaderboard, ( ( GetLeaderBoardByTimeTrialResponse ) o ).leaderboard );
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
