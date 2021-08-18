package tech.geocodeapp.geocode.geocode.request;

import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.Valid;

import tech.geocodeapp.geocode.geocode.model.Difficulty;

import java.util.List;
import java.util.Objects;

/**
 * GetGeoCodesByDifficultyListRequest used to specify the attributes needed to find all the GeoCodes
 * with a specific difficulty
 */
@Validated
public class GetGeoCodesByDifficultyListRequest {

    /**
     * The level of difficulty to find a GeoCode in the real world
     */
    @Valid
    @JsonProperty( "difficulty" )
    private List< Difficulty > difficulty;

    /**
     * Default constructor
     */
    public GetGeoCodesByDifficultyListRequest() {

    }

    /**
     * Overloaded Constructor
     *
     * @param difficulty The level of difficulty to find a GeoCode in the real world
     */
    public GetGeoCodesByDifficultyListRequest( List< Difficulty > difficulty ) {

        this.difficulty = difficulty;
    }

    /**
     * Sets the difficulty attribute to the specified value
     *
     * @param difficulty the value the attribute should be set to
     *
     * @return the request after the difficulty has been changed
     */
    public GetGeoCodesByDifficultyListRequest difficulty( List< Difficulty > difficulty ) {

        this.difficulty = difficulty;
        return this;
    }

    /**
     * Sets a single difficulty inside the difficulty attribute to the specified value
     *
     * @param difficultyItem the value the attribute should be set to
     *
     * @return the stored difficulty attribute
     */
    public GetGeoCodesByDifficultyListRequest addDifficultyItem( Difficulty difficultyItem ) {

        this.difficulty.add( difficultyItem );
        return this;
    }

    /**
     * Gets the saved difficulty attribute
     *
     * @return the stored difficulty attribute
     */
    @Valid
    public List< Difficulty > getDifficulty() {

        return difficulty;
    }

    /**
     * Sets the difficulty attribute to the specified value
     *
     * @param difficulty the value the attribute should be set to
     */
    public void setDifficulty( List< Difficulty > difficulty ) {

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

        return Objects.equals( this.difficulty, ( ( GetGeoCodesByDifficultyListRequest ) obj ).difficulty );
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

        return "class GetGeoCodesByDifficultyListRequest {\n" +
                "    difficulty: " + toIndentedString( difficulty ) + "\n" +
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
