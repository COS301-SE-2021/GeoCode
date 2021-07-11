package tech.geocodeapp.geocode.event.response;

import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.validation.Valid;

import tech.geocodeapp.geocode.leaderboard.model.Point;

import java.util.Objects;
import java.util.List;

/**
 * CreatePointResponse object of the created Points object with the specified values
 */
@Validated
public class CreatePointResponse {

    /**
     * The created Points with the specified values
     */
    @JsonProperty( "points" )
    @NotNull( message = "CreatePointResponse points attribute cannot be null." )
    private List< Point > points;

    /**
     * Overloaded Constructor
     *
     * @param points the points created for a certain Event
     */
    public CreatePointResponse( @Valid List< Point > points ) {

        this.points = points;
    }

    /**
     * Sets the points attribute to the specified value
     *
     * @param points the value the attribute should be set to
     *
     * @return the request after the points has been changed
     */
    public CreatePointResponse points( @Valid List< Point > points ) {

        this.points = points;
        return this;
    }

    /**
     * Sets a single point inside of the points attribute to the specified value
     *
     * @param pointsItem the value the attribute should be set to
     *
     * @return the stored points attribute
     */
    public CreatePointResponse addPointsItem( @Valid Point pointsItem ) {

        this.points.add( pointsItem );
        return this;
    }

    /**
     * Gets the saved points attribute
     *
     * @return the stored points attribute
     */
    @Valid
    public List< Point > getPoints() {

        return points;
    }

    /**
     * Sets the points attribute to the specified value
     *
     * @param points the value the attribute should be set to
     */
    public void setPoints( @Valid List< Point > points ) {

        this.points = points;
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

        return Objects.equals( this.points, ( ( CreatePointResponse ) obj ).points );
    }

    /**
     * Creates a hash code from the attributes in the class
     *
     * @return the created has code
     */
    @Override
    public int hashCode() {

        return Objects.hash( points );
    }

    /**
     * Creates a string from all the attributes in the class
     *
     * @return the created string
     */
    @Override
    public String toString() {

        return "class CreatePointResponse {\n" +
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
