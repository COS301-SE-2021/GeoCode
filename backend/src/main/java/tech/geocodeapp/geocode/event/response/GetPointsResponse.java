package tech.geocodeapp.geocode.event.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import tech.geocodeapp.geocode.leaderboard.model.Point;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * GetPointsResponse
 */
@Validated
public class GetPointsResponse {

    @JsonProperty( "points" )
    @Valid
    private List< Point > points = new ArrayList< Point >();

    public GetPointsResponse points( List< Point > points ) {

        this.points = points;
        return this;
    }

    public GetPointsResponse addPointsItem( Point pointsItem ) {

        this.points.add( pointsItem );
        return this;
    }

    /**
     * Get points
     *
     * @return points
     **/
    @Schema( required = true, description = "" )
    @NotNull
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
        GetPointsResponse getPointsResponse = ( GetPointsResponse ) o;
        return Objects.equals( this.points, getPointsResponse.points );
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
