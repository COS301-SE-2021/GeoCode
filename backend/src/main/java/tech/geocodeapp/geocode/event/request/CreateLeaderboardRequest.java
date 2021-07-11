package tech.geocodeapp.geocode.event.request;

import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.*;
import javax.validation.Valid;

import java.util.Objects;

/**
 * CreateLeaderboardRequest object to specify what Leaderboard to be created
 */
@Validated
public class CreateLeaderboardRequest {

    @JsonProperty( "name" )
    @NotEmpty( message = "CreateLeaderboardRequest name attribute cannot be empty." )
    private String name;

    /**
     * Overloaded Constructor
     *
     * @param name the name of the Leaderboard to create
     */
    public CreateLeaderboardRequest( @Valid String name ) {

        this.name = name;
    }

    /**
     * Sets the name attribute to the specified value
     *
     * @param name the value the attribute should be set to
     *
     * @return the request after the name has been changed
     */
    public CreateLeaderboardRequest name( @Valid String name ) {

        this.name = name;
        return this;
    }

    /**
     * Gets the saved name attribute
     *
     * @return the stored name attribute
     */
    @Valid
    public String getName() {

        return name;
    }

    /**
     * Sets the name attribute to the specified value
     *
     * @param name the value the attribute should be set to
     */
    public void setName( @Valid String name ) {

        this.name = name;
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

        return Objects.equals( this.name, ( ( CreateLeaderboardRequest ) obj ).name );
    }

    /**
     * Creates a hash code from the attributes in the class
     *
     * @return the created has code
     */
    @Override
    public int hashCode() {

        return Objects.hash( name );
    }

    /**
     * Creates a string from all the attributes in the class
     *
     * @return the created string
     */
    @Override
    public String toString() {

        return "class CreateLeaderboardRequest {\n" +
                "    name: " + toIndentedString( name ) + "\n" +
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
