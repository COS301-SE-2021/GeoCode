package tech.geocodeapp.geocode.GeoCode.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * GetHintsResponse used to access the attributes received to create the response
 * that contains the hints for a specific GeoCode
 */
@Validated
public class GetHintsResponse {

    /**
     * The hints from the specified GeoCode
     */
    @Valid
    @JsonProperty( "hints" )
    private Collection< String > hints = null;

    /**
     * Sets the hints attribute to the specified value
     *
     * @param hints the value the attribute should be set to
     *
     * @return the request after the hints has been changed
     */
    public GetHintsResponse hints( Collection< String > hints ) {

        this.hints = hints;
        return this;
    }

    /**
     * Sets a single hint inside of the hints attribute to the specified value
     *
     * @param hintsItem the value the attribute should be set to
     *
     * @return the stored hints attribute
     */
    public GetHintsResponse addHintsItem( String hintsItem ) {

        if ( this.hints == null ) {

            this.hints = new ArrayList<>();
        }

        this.hints.add( hintsItem );
        return this;
    }

    /**
     * Gets the saved hints attribute
     *
     * @return the stored hints attribute
     */
    public Collection< String > getHints() {

        return hints;
    }

    /**
     * Sets the hints attribute to the specified value
     *
     * @param hints the value the attribute should be set to
     */
    public void setHints( Collection< String > hints ) {

        this.hints = hints;
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

        return Objects.equals( this.hints, ( ( GetHintsResponse ) obj ).hints );
    }

    /**
     * Creates a hash code from the attributes in the class
     *
     * @return the created has code
     */
    @Override
    public int hashCode() {

        return Objects.hash( hints );
    }

    /**
     * Creates a string from all the attributes in the class
     *
     * @return the created string
     */
    @Override
    public String toString() {

        return "class GetHintsResponse {\n" +
                "    hints: " + toIndentedString( hints ) + "\n" +
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
