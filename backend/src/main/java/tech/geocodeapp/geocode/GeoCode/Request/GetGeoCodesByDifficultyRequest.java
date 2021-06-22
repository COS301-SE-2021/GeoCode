package tech.geocodeapp.geocode.GeoCode.Request;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

import tech.geocodeapp.geocode.Collectable.Model.Difficulty;



/**
 * GetGeoCodesByDifficultyRequest used to specify the attributes needed to find all the GeoCodes
 * with a specific difficulty
 */
@Validated
public class GetGeoCodesByDifficultyRequest {

    /**
     * The level of difficulty to find a GeoCode in the real world
     */
    @JsonProperty( "difficulty" )
    private Difficulty difficulty = null;

    /**
     * Sets the difficulty attribute to the specified value
     *
     * @param difficulty the value the attribute should be set to
     *
     * @return the request after the difficulty has been changed
     */
    public GetGeoCodesByDifficultyRequest difficulty( Difficulty difficulty ) {

        this.difficulty = difficulty;
        return this;
    }

    /**
     * Gets the saved difficulty attribute
     *
     * @return the stored difficulty attribute
     */
    @Valid
    public Difficulty getDifficulty() {

        return difficulty;
    }

    /**
     * Sets the difficulty attribute to the specified value
     *
     * @param difficulty the value the attribute should be set to
     */
    public void setDifficulty( Difficulty difficulty ) {

        this.difficulty = difficulty;
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
        GetGeoCodesByDifficultyRequest getGeoCodesByDifficultyRequest = ( GetGeoCodesByDifficultyRequest ) obj;
        return Objects.equals( this.difficulty, getGeoCodesByDifficultyRequest.difficulty );
    }

    /**
     * Creates a hash code from the attributes in the class
     *
     * @return the created has code
     */
    @Override
    public int hashCode() {

        return Objects.hash( difficulty );
    }

    /**
     * Creates a string from all the attributes in the class
     *
     * @return the created string
     */
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append( "class GetGeoCodesByDifficultyRequest {\n" );

        sb.append( "    difficulty: " ).append( toIndentedString( difficulty ) ).append( "\n" );
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
