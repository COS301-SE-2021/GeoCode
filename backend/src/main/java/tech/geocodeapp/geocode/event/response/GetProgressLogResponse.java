package tech.geocodeapp.geocode.event.response;

import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;

import tech.geocodeapp.geocode.event.model.ProgressLog;

import java.util.Objects;

/**
 * GetTimeLogResponse object to hold the found TimeLog entry
 */
@Validated
public class GetProgressLogResponse {

    /**
     * Determines if an ProgressLog entry was found or not
     */
    @JsonProperty( "found" )
    @NotNull( message = "GetProgressLogResponse found attribute cannot be null." )
    private Boolean found = null;

    /**
     * The found Timelog entry
     */
    @JsonProperty( "foundProgressLog" )
    private ProgressLog foundLog = null;

    /**
     * Default Constructor
     */
    public GetProgressLogResponse() {

    }

    /**
     *  Overloaded Constructor
     *
     * @param found Determines if an TimeLog entry was found or not
     */
    public GetProgressLogResponse(Boolean found ) {

        this.found = found;
    }

    /**
     *  Overloaded Constructor
     *
     * @param found Determines if an ProgressLog entry was found or not
     * @param foundLog The found ProgressLog entry
     */
    public GetProgressLogResponse(Boolean found, ProgressLog foundLog ) {

        this.found = found;
        this.foundLog = foundLog;
    }

    /**
     * Sets the found attribute to the specified value
     *
     * @param found the value the attribute should be set to
     *
     * @return the request after the found has been changed
     */
    public GetProgressLogResponse found(Boolean found ) {

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
     * Sets the foundLog attribute to the specified value
     *
     * @param foundLog the value the attribute should be set to
     *
     * @return the request after the foundLog has been changed
     */
    public GetProgressLogResponse foundLog(ProgressLog foundLog ) {

        this.foundLog = foundLog;
        return this;
    }


    /**
     * Gets the saved foundLog attribute
     *
     * @return the stored foundLog attribute
     */
    public ProgressLog foundLog() {

        return foundLog;
    }

    /**
     * Sets the foundLog attribute to the specified value
     *
     * @param foundLog the value the attribute should be set to
     */
    public void setFoundLog( ProgressLog foundLog ) {

        this.foundLog = foundLog;
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

        GetProgressLogResponse getTimeLogResponse = (GetProgressLogResponse) obj;
        return Objects.equals( this.found, getTimeLogResponse.found ) &&
                Objects.equals( this.foundLog, getTimeLogResponse.foundLog );
    }

    /**
     * Creates a hash code from the attributes in the class
     *
     * @return the created has code
     */
    @Override
    public int hashCode() {

        return Objects.hash( found, foundLog );
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
                "    foundLog: " + toIndentedString( foundLog ) + "\n" +
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
