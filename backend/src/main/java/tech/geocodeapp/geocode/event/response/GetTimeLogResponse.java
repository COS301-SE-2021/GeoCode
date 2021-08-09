package tech.geocodeapp.geocode.event.response;

import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;

import tech.geocodeapp.geocode.event.model.TimeLog;

import java.util.Objects;

/**
 * GetTimeLogResponse object to hold the found TimeLog entry
 */
@Validated
public class GetTimeLogResponse {

    /**
     * Determines if an TimeLog entry was found or not
     */
    @JsonProperty( "found" )
    @NotNull( message = "GetTimeLogResponse found attribute cannot be null." )
    private Boolean found = null;

    /**
     * The found Timelog entry
     */
    @JsonProperty( "foundTimeLog" )
    private TimeLog foundTimeLog = null;

    /**
     * Default Constructor
     */
    public GetTimeLogResponse() {

    }

    /**
     *  Overloaded Constructor
     *
     * @param found Determines if an TimeLog entry was found or not
     */
    public GetTimeLogResponse( Boolean found ) {

        this.found = found;
    }

    /**
     *  Overloaded Constructor
     *
     * @param found Determines if an TimeLog entry was found or not
     * @param foundTimeLog The found Timelog entry
     */
    public GetTimeLogResponse( Boolean found, TimeLog foundTimeLog ) {

        this.found = found;
        this.foundTimeLog = foundTimeLog;
    }

    /**
     * Sets the found attribute to the specified value
     *
     * @param found the value the attribute should be set to
     *
     * @return the request after the found has been changed
     */
    public GetTimeLogResponse found( Boolean found ) {

        this.found = found;
        return this;
    }

    /**
     * Gets the saved found attribute
     *
     * @return the stored found attribute
     */
    public Boolean isFound() {

        return found;
    }

    /**
     * Sets the found attribute to the specified value
     *
     * @param found the value the attribute should be set to
     */
    public void setFound( Boolean found ) {

        this.found = found;
    }

    /**
     * Sets the foundTimeLog attribute to the specified value
     *
     * @param foundTimeLog the value the attribute should be set to
     *
     * @return the request after the foundTimeLog has been changed
     */
    public GetTimeLogResponse foundTimeLog( TimeLog foundTimeLog ) {

        this.foundTimeLog = foundTimeLog;
        return this;
    }


    /**
     * Gets the saved foundTimeLog attribute
     *
     * @return the stored foundTimeLog attribute
     */
    public TimeLog getFoundTimeLog() {

        return foundTimeLog;
    }

    /**
     * Sets the foundTimeLog attribute to the specified value
     *
     * @param foundTimeLog the value the attribute should be set to
     */
    public void setFoundTimeLog( TimeLog foundTimeLog ) {

        this.foundTimeLog = foundTimeLog;
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

        GetTimeLogResponse getTimeLogResponse = ( GetTimeLogResponse ) obj;
        return Objects.equals( this.found, getTimeLogResponse.found ) &&
                Objects.equals( this.foundTimeLog, getTimeLogResponse.foundTimeLog );
    }

    /**
     * Creates a hash code from the attributes in the class
     *
     * @return the created has code
     */
    @Override
    public int hashCode() {

        return Objects.hash( found, foundTimeLog );
    }

    /**
     * Creates a string from all the attributes in the class
     *
     * @return the created string
     */
    @Override
    public String toString() {

        return "class GetTimeLogResponse {\n" +
                "    found: " + toIndentedString( found ) + "\n" +
                "    foundTimeLog: " + toIndentedString( foundTimeLog ) + "\n" +
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
