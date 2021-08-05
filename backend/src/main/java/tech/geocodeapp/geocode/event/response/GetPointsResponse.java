package tech.geocodeapp.geocode.event.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

import tech.geocodeapp.geocode.leaderboard.model.Point;

import java.util.List;
import java.util.Objects;

/**
 * GetPointsResponse
 */
@Validated
public class GetPointsResponse {

    @Valid
    @JsonProperty( "points" )
    private List< Point > points;

    public GetPointsResponse points( List< Point > points ) {

        this.points = points;
        return this;
    }

    public GetPointsResponse addPointsItem( Point pointsItem ) {

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

        return Objects.equals( this.points, ( ( GetPointsResponse ) o ).points );
    }

    @Override
    public int hashCode() {

        return Objects.hash( points );
    }

    @Override
    public String toString() {

        return "class GetPointsResponse {\n" +
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
