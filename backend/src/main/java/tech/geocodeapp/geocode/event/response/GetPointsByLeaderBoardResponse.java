package tech.geocodeapp.geocode.event.response;

import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.Valid;

import tech.geocodeapp.geocode.leaderboard.model.Point;

import java.util.Objects;
import java.util.ArrayList;
import java.util.List;

/**
 * GetPointsByLeaderBoardResponse
 */
@Validated
public class GetPointsByLeaderBoardResponse {

    @Valid
    @JsonProperty( "points" )
    private List< Point > points = new ArrayList<>();

    public GetPointsByLeaderBoardResponse points( List< Point > points ) {

        this.points = points;
        return this;
    }

    public GetPointsByLeaderBoardResponse addPointsItem( Point pointsItem ) {

        this.points.add( pointsItem );
        return this;
    }

    @Valid
    public List< Point > getPoints() {

        return points;
    }

    public void setPoints( List< Point > points ) {

        this.points = points;
    }


    @Override
    public boolean equals( java.lang.Object o ) {

        if ( this == o ) {

            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {

            return false;
        }

        return Objects.equals( this.points, ( ( GetPointsByLeaderBoardResponse ) o ).points );
    }

    @Override
    public int hashCode() {

        return Objects.hash( points );
    }

    @Override
    public String toString() {

        return "class GetPointsByLeaderBoardResponse {\n" +
                "    points: " + toIndentedString( points ) + "\n" +
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
